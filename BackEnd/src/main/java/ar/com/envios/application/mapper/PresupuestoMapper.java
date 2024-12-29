package ar.com.envios.application.mapper;

import ar.com.envios.application.dto.PresupuestoResponse;
import ar.com.envios.application.dto.UsuarioRequest;
import ar.com.envios.domain.model.Extra;
import ar.com.envios.domain.model.Presupuesto;
import ar.com.envios.domain.model.Usuario;
import ar.com.envios.infrastructure.entity.PresupuestoEntity;

import java.util.stream.Collectors;

public class PresupuestoMapper {

    public static PresupuestoResponse toResponse(Presupuesto presupuesto) {
        return new PresupuestoResponse(
                presupuesto.getOrigen(),
                presupuesto.getDestino(),
                presupuesto.getVolumenCarga(),
                presupuesto.getVehiculo().getNombre(),
                presupuesto.calcularTotal(),
                presupuesto.getExtras().stream().map(Extra::getNombre).collect(Collectors.toList())
        );
    }

    public static Usuario toUsuario(UsuarioRequest request) {
        return new Usuario(
                null, // El id puede ser null si no se requiere al crear un nuevo usuario
                request.getNombre(),
                request.getEmail(),
                request.getTipoUsuario(),
                request.getPassword(),
                true
        );
    }

    public static PresupuestoEntity toEntity(Presupuesto presupuesto) {
        PresupuestoEntity entity = new PresupuestoEntity(
                presupuesto.getOrigen(),
                presupuesto.getDestino(),
                presupuesto.getVolumenCarga(),
                presupuesto.getPesoCarga(),
                VehiculoMapper.toEntity(presupuesto.getVehiculo()),
                presupuesto.getUsuariosInvolucrados().stream()
                        .map(UsuarioMapper::toEntity)
                        .collect(Collectors.toList())
        );
        entity.setPresupuestoTotal(presupuesto.calcularTotal().doubleValue());
        return entity;
    }

}

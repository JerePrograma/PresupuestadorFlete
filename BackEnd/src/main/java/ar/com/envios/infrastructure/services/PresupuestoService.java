package ar.com.envios.infrastructure.services;

import ar.com.envios.application.dto.PresupuestoRequest;
import ar.com.envios.application.dto.PresupuestoResponse;
import ar.com.envios.application.usecase.GenerarPresupuestoUseCase;
import ar.com.envios.domain.model.Presupuesto;
import ar.com.envios.domain.model.Usuario;
import ar.com.envios.application.mapper.PresupuestoMapper;
import ar.com.envios.application.mapper.UsuarioMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PresupuestoService {
    private final GenerarPresupuestoUseCase generarPresupuestoUseCase;

    public PresupuestoService(@Qualifier("generarPresupuestoUseCase") GenerarPresupuestoUseCase generarPresupuestoUseCase) {
        this.generarPresupuestoUseCase = generarPresupuestoUseCase;
    }

    public PresupuestoResponse crearPresupuesto(PresupuestoRequest request) {
        List<Usuario> usuariosInvolucrados = (request.getUsuariosInvolucrados() != null)
                ? request.getUsuariosInvolucrados().stream()
                .map(UsuarioMapper::toUsuario)
                .collect(Collectors.toList())
                : List.of();

        Presupuesto presupuesto = generarPresupuestoUseCase.ejecutar(
                request.getOrigen(),
                request.getDestino(),
                request.getVolumenCarga(),
                request.getPesoCarga(),
                request.getNombreTipoVehiculo(),
                usuariosInvolucrados
        );

        return PresupuestoMapper.toResponse(presupuesto);
    }
}

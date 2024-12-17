package ar.com.envios.application.mapper;

import ar.com.envios.application.dto.PresupuestoResponse;
import ar.com.envios.application.dto.UsuarioRequest;
import ar.com.envios.domain.enumeraciones.TipoUsuario;
import ar.com.envios.domain.model.Extra;
import ar.com.envios.domain.model.Presupuesto;
import ar.com.envios.domain.model.Usuario;

import java.util.stream.Collectors;

public class PresupuestoMapper {

    public static PresupuestoResponse toResponse(Presupuesto presupuesto) {
        return new PresupuestoResponse(
                presupuesto.getOrigen(),
                presupuesto.getDestino(),
                presupuesto.getVolumenCarga(),
                presupuesto.getTipoVehiculo().nombre(),
                presupuesto.calcularTotal(),
                presupuesto.getExtras().stream().map(Extra::nombre).collect(Collectors.toList())
        );
    }

    public static Usuario toUsuario(UsuarioRequest request) {
        return new Usuario(
                null, // El id puede ser null si no se requiere al crear un nuevo usuario
                request.getNombre(),
                request.getEmail(),
                request.getPassword(),
                TipoUsuario.valueOf(request.getTipoUsuario().toUpperCase()) // Asegúrate de manejar el enum correctamente
        );
    }
}

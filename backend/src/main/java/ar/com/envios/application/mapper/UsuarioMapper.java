/***********************************************
 * ar.com.envios.application.mapper.UsuarioMapper
 ***********************************************/
package ar.com.envios.application.mapper;

import ar.com.envios.application.dto.UsuarioRequest;
import ar.com.envios.application.dto.UsuarioResponse;
import ar.com.envios.domain.enumeraciones.TipoUsuario;
import ar.com.envios.domain.model.Usuario;
import ar.com.envios.infrastructure.entity.UsuarioEntity;

public class UsuarioMapper {

    /**
     * DTO (request) -> Dominio
     */
    public static Usuario toDomain(UsuarioRequest request) {
        return new Usuario(
                request.getId(),
                request.getNombre(),
                request.getEmail(),
                request.getTipoUsuario(),  // Enum directamente
                request.getPassword(),
                request.isDisponible()     // <-- mapeo del boolean
        );
    }

    /**
     * Dominio -> DTO (response)
     */
    public static UsuarioResponse toResponse(Usuario domain) {
        return new UsuarioResponse(
                domain.getId(),
                domain.getNombre(),
                domain.getEmail(),
                // Convertimos la enum a String
                domain.getTipoUsuario() != null ? domain.getTipoUsuario().name() : null,
                domain.isDisponible(),  // <-- mapeo del boolean
                null
        );
    }

    /**
     * Dominio -> Entidad
     */
    public static UsuarioEntity toEntity(Usuario domain) {
        return new UsuarioEntity(
                domain.getId(),
                domain.getNombre(),
                domain.getEmail(),
                domain.getPassword(),
                domain.getTipoUsuario(),
                domain.isDisponible()   // <-- mapeo del boolean
        );
    }

    /**
     * Entidad -> Dominio
     */
    public static Usuario toDomain(UsuarioEntity entity) {
        return new Usuario(
                entity.getId(),
                entity.getNombre(),
                entity.getEmail(),
                entity.getTipoUsuario(),
                entity.getPassword(),
                entity.isDisponible()  // <-- mapeo del boolean
        );
    }
}

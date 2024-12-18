package ar.com.envios.application.mapper;

import ar.com.envios.application.dto.UsuarioRequest;
import ar.com.envios.application.dto.UsuarioResponse;
import ar.com.envios.domain.enumeraciones.TipoUsuario;
import ar.com.envios.domain.model.Usuario;
import ar.com.envios.infrastructure.entity.UsuarioEntity;

public class UsuarioMapper {

    public static Usuario toUsuario(UsuarioRequest request) {
        return new Usuario(
                null, // El ID se genera en la base de datos
                request.getNombre(),
                request.getEmail(),
                request.getPassword(), // La contraseña aún no está encriptada
                TipoUsuario.valueOf(request.getTipoUsuario().toUpperCase())
        );
    }

    public static UsuarioResponse toResponse(Usuario usuario) {
        return new UsuarioResponse(
                usuario.getId(),
                usuario.getNombre(),
                usuario.getEmail(),
                usuario.getTipoUsuario().name()
        );
    }

    public static UsuarioEntity toEntity(Usuario usuario) {
        return new UsuarioEntity(
                usuario.getId(),
                usuario.getNombre(),
                usuario.getEmail(),
                usuario.getPassword(),
                usuario.getTipoUsuario()
        );
    }

    public static Usuario toDomain(UsuarioEntity entity) {
        return new Usuario(
                entity.getId(),
                entity.getNombre(),
                entity.getEmail(),
                entity.getPassword(), // La contraseña debería ser encriptada en la capa de servicio
                entity.getTipoUsuario()
        );
    }

}

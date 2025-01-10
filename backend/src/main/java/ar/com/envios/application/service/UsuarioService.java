package ar.com.envios.application.service;

import ar.com.envios.application.dto.UsuarioResponse;
import ar.com.envios.application.mapper.UsuarioMapper;
import ar.com.envios.infrastructure.adapter.out.persistence.JpaUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import ar.com.envios.domain.model.Usuario;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    private final JpaUsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UsuarioService(JpaUsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<UsuarioResponse> obtenerUsuariosPorRoles(List<String> roles) {
        // si no te importa la disponibilidad:
        return usuarioRepository.buscarPorTipoUsuario(roles)
                .stream()
                .map(UsuarioMapper::toResponse)
                .collect(Collectors.toList());
    }

    public List<UsuarioResponse> obtenerUsuariosPorRolesDisponibles(List<String> roles) {
        // si quieres filtrar solo disponibles:
        return usuarioRepository.buscarPorTiposYDisponibles(roles, true)
                .stream()
                .map(UsuarioMapper::toResponse)
                .collect(Collectors.toList());
    }

}

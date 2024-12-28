package ar.com.envios.application.service;

import ar.com.envios.application.dto.UsuarioRequest;
import ar.com.envios.application.dto.UsuarioResponse;
import ar.com.envios.application.mapper.UsuarioMapper;
import ar.com.envios.domain.repository.IUsuarioRepository;
import ar.com.envios.domain.service.UsuarioDomainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import ar.com.envios.domain.model.Usuario;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    private final IUsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UsuarioService(IUsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UsuarioResponse crearUsuario(UsuarioRequest request) {
        var usuario = UsuarioMapper.toDomain(request);
        usuario.setPassword(passwordEncoder.encode(request.getPassword()));
        usuarioRepository.guardar(usuario);
        return UsuarioMapper.toResponse(usuario);
    }

    public List<UsuarioResponse> obtenerUsuariosPorRoles(List<String> roles) {
        return usuarioRepository.buscarPorTipoUsuario(roles)
                .stream()
                .map(UsuarioMapper::toResponse)
                .collect(Collectors.toList());
    }

    public Usuario obtenerUsuarioPorId(Long usuarioId) {
        return usuarioRepository.buscarPorId(usuarioId)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado con id: " + usuarioId));
    }

}

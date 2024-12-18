package ar.com.envios.application.service;

import ar.com.envios.application.dto.UsuarioRequest;
import ar.com.envios.application.dto.UsuarioResponse;
import ar.com.envios.application.mapper.UsuarioMapper;
import ar.com.envios.domain.enumeraciones.TipoUsuario;
import ar.com.envios.domain.repository.IUsuarioRepository;
import ar.com.envios.infrastructure.adapter.out.persistence.JpaUsuarioRepository;
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
public class UsuarioService implements UserDetailsService {

    private final IUsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UsuarioService(IUsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UsuarioResponse crearUsuario(UsuarioRequest request) {
        Usuario usuario = new Usuario();
        usuario.setNombre(request.getNombre());
        usuario.setEmail(request.getEmail());
        usuario.setPassword(passwordEncoder.encode(request.getPassword())); // Codifica la contraseña
        usuario.setTipoUsuario(TipoUsuario.valueOf(request.getTipoUsuario()));
        usuarioRepository.guardar(usuario);
        return new UsuarioResponse("Usuario creado correctamente");
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.buscarPorEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + email));

        // Configurar roles correctamente
        return User.builder()
                .username(usuario.getEmail())
                .password(usuario.getPassword())
                .roles(usuario.getTipoUsuario().name()) // Asigna el rol desde el campo tipo_usuario
                .build();
    }

    public List<UsuarioResponse> obtenerUsuariosPorRoles(List<String> roles) {
        return usuarioRepository.buscarPorTipoUsuario(roles).stream()
                .map(UsuarioMapper::toResponse)
                .collect(Collectors.toList());
    }
}

package ar.com.envios.application.service;

import ar.com.envios.application.dto.UsuarioRequest;
import ar.com.envios.application.dto.UsuarioResponse;
import ar.com.envios.application.mapper.UsuarioMapper;
import ar.com.envios.domain.enumeraciones.TipoUsuario;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import ar.com.envios.domain.model.Usuario;
import ar.com.envios.domain.repository.IUsuarioRepository;

import org.springframework.security.crypto.password.PasswordEncoder;

@Service
public class UsuarioService implements UserDetailsService {

    private final IUsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioService(IUsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder; // Inyectar el PasswordEncoder
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.buscarPorEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + email));

        return User.builder()
                .username(usuario.getEmail())
                .password(usuario.getPassword()) // Contraseña con BCrypt
                .roles(usuario.getTipoUsuario().name())
                .build();
    }

    public UsuarioResponse crearUsuario(UsuarioRequest request) {
        // Mapear la solicitud a la entidad de dominio Usuario
        Usuario usuario = new Usuario(
                null, // ID nulo porque se generará automáticamente en la base de datos
                request.getNombre(),
                request.getEmail(),
                passwordEncoder.encode(request.getPassword()), // Encriptar la contraseña
                TipoUsuario.valueOf(request.getTipoUsuario().toUpperCase()) // Convertir a ENUM
        );

        // Guardar el usuario en el repositorio
        usuarioRepository.guardar(usuario);

        // Mapear la entidad de dominio a la respuesta
        return UsuarioMapper.toResponse(usuario);
    }
}

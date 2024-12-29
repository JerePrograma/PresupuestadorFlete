/***********************************************
 * ar.com.envios.application.usecase.UsuarioUseCase
 ***********************************************/
package ar.com.envios.application.usecase;

import ar.com.envios.application.dto.UsuarioRequest;
import ar.com.envios.application.dto.UsuarioResponse;
import ar.com.envios.application.mapper.UsuarioMapper;
import ar.com.envios.domain.model.Usuario;
import ar.com.envios.domain.repository.IUsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioUseCase {

    private final IUsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioUseCase(IUsuarioRepository usuarioRepository,
                          PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UsuarioResponse crearUsuario(UsuarioRequest request) {
        request.setDisponible(true);
        // 1. Convertimos request a dominio
        Usuario usuario = UsuarioMapper.toDomain(request);
        // 2. Hasheamos password
        usuario.setPassword(passwordEncoder.encode(request.getPassword()));
        // 3. Guardamos
        usuarioRepository.guardar(usuario);
        // 4. Convertimos a Response
        return UsuarioMapper.toResponse(usuario);
    }

    public List<UsuarioResponse> listarUsuarios() {
        return usuarioRepository.listarTodos()
                .stream()
                .map(UsuarioMapper::toResponse)
                .collect(Collectors.toList());
    }

    public UsuarioResponse obtenerUsuarioResponsePorId(Long usuarioId) {
        // Usa Optional -> orElseThrow
        Usuario usuario = usuarioRepository.buscarPorId(usuarioId)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado con id: " + usuarioId));
        return UsuarioMapper.toResponse(usuario);
    }

    public List<UsuarioResponse> obtenerUsuariosPorRoles(List<String> roles) {
        return usuarioRepository.buscarPorTipoUsuario(roles)
                .stream()
                .map(UsuarioMapper::toResponse)
                .collect(Collectors.toList());
    }
}

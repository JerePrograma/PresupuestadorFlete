package ar.com.envios.application.usecase;

import ar.com.envios.application.dto.UsuarioRequest;
import ar.com.envios.application.dto.UsuarioResponse;
import ar.com.envios.application.mapper.UsuarioMapper;
import ar.com.envios.domain.model.Usuario;
import ar.com.envios.domain.repository.IUsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioUseCase {

    private final IUsuarioRepository usuarioRepository;

    public UsuarioUseCase(IUsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public UsuarioResponse crearUsuario(UsuarioRequest request) {
        Usuario usuario = UsuarioMapper.toUsuario(request);
        usuarioRepository.guardar(usuario);
        return UsuarioMapper.toResponse(usuario);
    }

    public List<UsuarioResponse> listarUsuarios() {
        return usuarioRepository.listarTodos()
                .stream()
                .map(UsuarioMapper::toResponse)
                .collect(Collectors.toList());
    }
}

package ar.com.envios.domain.service;

import ar.com.envios.domain.model.Usuario;
import ar.com.envios.domain.repository.IUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioDomainServiceImpl implements UsuarioDomainService {
    private final IUsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioDomainServiceImpl(IUsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public Usuario obtenerUsuarioPorId(Long usuarioId) {
        return usuarioRepository.buscarPorId(usuarioId)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado con id: " + usuarioId));
    }
}


package ar.com.envios.domain.service;

import ar.com.envios.domain.model.Usuario;

public interface UsuarioDomainService {
    Usuario obtenerUsuarioPorId(Long usuarioId);
}

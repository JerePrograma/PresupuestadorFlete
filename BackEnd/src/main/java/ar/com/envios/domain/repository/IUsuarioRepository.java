package ar.com.envios.domain.repository;

import ar.com.envios.domain.model.Usuario;

import java.util.List;
import java.util.Optional;

public interface IUsuarioRepository {
    void guardar(Usuario usuario);
    Optional<Usuario> buscarPorEmail(String email);
    List<Usuario> listarTodos();
}

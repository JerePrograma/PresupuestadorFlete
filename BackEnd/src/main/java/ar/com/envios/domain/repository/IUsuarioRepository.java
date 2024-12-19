package ar.com.envios.domain.repository;

import ar.com.envios.domain.model.Usuario;
import java.util.List;
import java.util.Optional;

public interface IUsuarioRepository {
    void guardar(Usuario usuario);
    Optional<Usuario> buscarPorEmail(String email);
    List<Usuario> listarTodos();
    List<Usuario> buscarPorTipoUsuario(List<String> tipos);

    // Nuevo método para buscar por ID
    Optional<Usuario> buscarPorId(Long id);
}

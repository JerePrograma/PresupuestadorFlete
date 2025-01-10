/***********************************************
 * ar.com.envios.domain.repository.IUsuarioRepository
 ***********************************************/
package ar.com.envios.domain.repository;

import ar.com.envios.domain.enumeraciones.TipoUsuario;
import ar.com.envios.domain.model.Usuario;
import ar.com.envios.infrastructure.entity.UsuarioEntity;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.Optional;

public interface IUsuarioRepository {

    void guardar(Usuario usuario);

    Optional<Usuario> buscarPorId(Long id);

    Optional<Usuario> buscarPorEmail(String email);

    List<Usuario> listarTodos();

    List<Usuario> buscarPorTipoUsuario(List<String> tipos);

    UserDetails findByNombre(String nombre);

    List<UsuarioEntity> findAllByTipoUsuarioInAndDisponible(List<TipoUsuario> tipoUsuarios, boolean disponible);

}

/***********************************************
 * ar.com.envios.infrastructure.adapter.out.persistence.JpaUsuarioRepository
 ***********************************************/
package ar.com.envios.infrastructure.adapter.out.persistence;

import ar.com.envios.domain.enumeraciones.TipoUsuario;
import ar.com.envios.domain.model.Usuario;
import ar.com.envios.domain.repository.IUsuarioRepository;
import ar.com.envios.infrastructure.entity.UsuarioEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class JpaUsuarioRepository implements IUsuarioRepository {

    private final SpringDataUsuarioRepository jpaRepository;

    public JpaUsuarioRepository(SpringDataUsuarioRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public void guardar(Usuario usuario) {
        // map domain -> entity
        UsuarioEntity entity = new UsuarioEntity(
                usuario.getId(),
                usuario.getNombre(),
                usuario.getEmail(),
                usuario.getPassword(),
                usuario.getTipoUsuario(),
                usuario.isDisponible()
        );
        jpaRepository.save(entity);
    }

    @Override
    public Optional<Usuario> buscarPorId(Long id) {
        return jpaRepository.findById(id).map(this::mapToDomain);
    }

    @Override
    public Optional<Usuario> buscarPorEmail(String email) {
        return jpaRepository.findByEmail(email) // Optional<UsuarioEntity>
                .map(entity -> {
                    // Mapeo a objeto de dominio
                    return new Usuario(
                            entity.getId(),
                            entity.getNombre(),
                            entity.getEmail(),
                            entity.getTipoUsuario(),
                            entity.getPassword(),
                            entity.isDisponible()
                    );
                });
    }


    @Override
    public List<Usuario> listarTodos() {
        return jpaRepository.findAll()
                .stream()
                .map(this::mapToDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Usuario> buscarPorTipoUsuario(List<String> tipos) {
        return List.of();
    }


    @Override
    public UserDetails findByNombre(String nombre) {
        // No implementado. Podrías hacer un findByNombre en jpaRepository
        return null;
    }


    // ---------------------------------------------
    // Método helper para mapear Entity -> Domain
    // ---------------------------------------------
    private Usuario mapToDomain(UsuarioEntity entity) {
        return new Usuario(
                entity.getId(),
                entity.getNombre(),
                entity.getEmail(),
                entity.getTipoUsuario(),  // <-- Usa la enum
                entity.getPassword(),
                entity.isDisponible()
        );
    }

    public List<Usuario> buscarPorTiposYDisponibles(List<String> tipos, boolean disponible) {
        List<TipoUsuario> tipoEnumList = tipos.stream()
                .map(TipoUsuario::valueOf)
                .toList();

        List<UsuarioEntity> entities = jpaRepository.findAllByTipoUsuarioInAndDisponible(tipoEnumList, disponible);

        return entities.stream()
                .map(e -> new Usuario(
                        e.getId(),
                        e.getNombre(),
                        e.getEmail(),
                        e.getTipoUsuario(),
                        e.getPassword(),
                        e.isDisponible() // o getDisponible()
                ))
                .toList();
    }

    @Override
    public List<UsuarioEntity> findAllByTipoUsuarioInAndDisponible(List<TipoUsuario> tipoUsuarios, boolean disponible) {
        return List.of();
    }
}

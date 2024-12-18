package ar.com.envios.infrastructure.adapter.out.persistence;

import ar.com.envios.domain.enumeraciones.TipoUsuario;
import ar.com.envios.infrastructure.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SpringDataUsuarioRepository extends JpaRepository<UsuarioEntity, Long> {
    Optional<UsuarioEntity> findByEmail(String email);

    List<UsuarioEntity> findByTipoUsuario(TipoUsuario tipoUsuario);

    List<UsuarioEntity> findByTipoUsuarioIn(List<TipoUsuario> roles);

}

// SpringDataExtrasRepository.java
package ar.com.envios.infrastructure.adapter.out.persistence;

import ar.com.envios.infrastructure.entity.ExtraEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface SpringDataExtrasRepository extends JpaRepository<ExtraEntity, Long> {
    Optional<ExtraEntity> findByNombre(String nombre);
}

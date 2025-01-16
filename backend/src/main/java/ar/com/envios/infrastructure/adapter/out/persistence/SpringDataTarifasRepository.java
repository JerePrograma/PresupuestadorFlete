// SpringDataTarifasRepository.java
package ar.com.envios.infrastructure.adapter.out.persistence;

import ar.com.envios.infrastructure.entity.VehiculoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface SpringDataTarifasRepository extends JpaRepository<VehiculoEntity, Long> {
    Optional<VehiculoEntity> findByNombre(String nombre);
}

// SpringDataTarifasRepository.java
package ar.com.envios.infrastructure.adapter.out.persistence;

import ar.com.envios.infrastructure.entity.TipoVehiculoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface SpringDataTarifasRepository extends JpaRepository<TipoVehiculoEntity, Long> {
    Optional<TipoVehiculoEntity> findByNombre(String nombre);
}

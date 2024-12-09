package ar.com.envios.domain.repository;

import ar.com.envios.infrastructure.entity.TipoVehiculoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ITipoVehiculoRepository extends JpaRepository<TipoVehiculoEntity, Long> {
    Optional<TipoVehiculoEntity> findByNombre(String nombre);
}

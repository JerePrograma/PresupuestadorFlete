package ar.com.envios.infrastructure.adapter.out.persistence;

import ar.com.envios.domain.model.Vehiculo;
import ar.com.envios.infrastructure.entity.VehiculoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpringDataVehiculoRepository extends JpaRepository<VehiculoEntity, Long> {
    List<VehiculoEntity> findAll();
}


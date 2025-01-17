package ar.com.envios.infrastructure.adapter.out.persistence;

import ar.com.envios.infrastructure.entity.PresupuestoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpringDataPresupuestoRepository extends JpaRepository<PresupuestoEntity, Long> {
    // Aqui puedes agregar metodos personalizados si los necesitas.
}

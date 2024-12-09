package ar.com.envios.infrastructure.adapter.out.persistence;

import ar.com.envios.domain.model.TipoVehiculo;
import ar.com.envios.domain.repository.ITarifasRepository;
import ar.com.envios.infrastructure.entity.TipoVehiculoEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Este repositorio JPA interactúa con la base de datos y luego mapea las entidades a objetos de dominio.
 */
@Repository
public class JpaTarifasRepository implements ITarifasRepository {

    private final SpringDataTarifasRepository springDataTarifasRepository;

    public JpaTarifasRepository(SpringDataTarifasRepository springDataTarifasRepository) {
        this.springDataTarifasRepository = springDataTarifasRepository;
    }

    @Override
    public List<TipoVehiculo> findAll() {
        return springDataTarifasRepository.findAll().stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<TipoVehiculo> findByNombre(String nombre) {
        return springDataTarifasRepository.findByNombre(nombre)
                .map(this::toDomain);
    }

    private TipoVehiculo toDomain(TipoVehiculoEntity entity) {
        return new TipoVehiculo(entity.getNombre(), entity.getCostoBasePorKm(), entity.getCapacidadMaxVolumen());
    }
}

package ar.com.envios.infrastructure.adapter.out.persistence;

import ar.com.envios.domain.model.Vehiculo;
import ar.com.envios.domain.repository.IVehiculoRepository;
import ar.com.envios.infrastructure.entity.VehiculoEntity;
import ar.com.envios.application.mapper.VehiculoMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class JpaVehiculoRepository implements IVehiculoRepository {

    private final SpringDataVehiculoRepository springDataVehiculoRepository;

    public JpaVehiculoRepository(SpringDataVehiculoRepository springDataVehiculoRepository) {
        this.springDataVehiculoRepository = springDataVehiculoRepository;
    }

    @Override
    public void guardar(Vehiculo vehiculo) {
        VehiculoEntity entity = convertirAEntidad(vehiculo);
        springDataVehiculoRepository.save(entity);
    }

    @Override
    public List<Vehiculo> obtenerTodos() {
        return springDataVehiculoRepository.findAll()
                .stream()
                .map(this::convertirADominio)
                .collect(Collectors.toList());
    }

    private Vehiculo convertirADominio(VehiculoEntity entity) {
        return new Vehiculo(
                entity.getNombre(),
                entity.getCapacidadMaxVolumen(),
                entity.getCapacidadMaxPeso(),
                entity.getConsumoPorKm()
        );
    }

    private VehiculoEntity convertirAEntidad(Vehiculo vehiculo) {
        VehiculoEntity entity = new VehiculoEntity();
        entity.setNombre(vehiculo.getNombre());
        entity.setCapacidadMaxVolumen(vehiculo.getCapacidadMaxVolumen());
        entity.setCapacidadMaxPeso(vehiculo.getCapacidadMaxVolumen());
        entity.setConsumoPorKm(vehiculo.getConsumoPorKm());
        return entity;
    }
}

package ar.com.envios.domain.service;
import ar.com.envios.domain.model.Vehiculo;
import ar.com.envios.infrastructure.adapter.out.persistence.JpaVehiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ar.com.envios.domain.repository.IVehiculoRepository;
import java.util.List;


public class VehiculoService {

    private final IVehiculoRepository vehiculoRepository;

    public VehiculoService(IVehiculoRepository vehiculoRepository) {
        this.vehiculoRepository = vehiculoRepository;
    }

    public List<Vehiculo> obtenerTodos() {
        return vehiculoRepository.obtenerTodos();
    }

    public void crearVehiculo(Vehiculo vehiculo) {
        vehiculoRepository.guardar(vehiculo);
    }
}
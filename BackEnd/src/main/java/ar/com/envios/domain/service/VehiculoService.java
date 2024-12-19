package ar.com.envios.domain.service;
import
import ar.com.envios.domain.model.Vehiculo;
import ar.com.envios.infrastructure.adapter.out.persistence.JpaVehiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ar.com.envios.domain.repository.IVehiculoRepository;
import java.util.List;

@Service
public class VehiculoService {

    @Autowired
    private JpaVehiculoRepository jpaVehiculoRepository;

    public List<Vehiculo> obtenerTodos() {
        return jpaVehiculoRepository.listarTodos();
    }

    public void crearVehiculo(Vehiculo vehiculo) {
        jpaVehiculoRepository.guardar(vehiculo);
    }
}

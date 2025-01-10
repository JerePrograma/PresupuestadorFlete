package ar.com.envios.application.service;

import ar.com.envios.application.dto.VehiculoResponse;
import ar.com.envios.domain.model.Vehiculo;
import ar.com.envios.infrastructure.adapter.out.persistence.JpaVehiculoRepository;
import ar.com.envios.infrastructure.adapter.out.persistence.SpringDataVehiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VehiculoService {

    private JpaVehiculoRepository vehiculoRepository;

    public VehiculoService(JpaVehiculoRepository vehiculoRepository) {
        this.vehiculoRepository = vehiculoRepository;
    }

    public List<VehiculoResponse> obtenerTodos() {
        return vehiculoRepository.obtenerTodos().stream()
                .map(vehiculo -> new VehiculoResponse(
                        vehiculo.getNombre(),
                        vehiculo.getCapacidadMaxVolumen(),
                        vehiculo.getCapacidadMaxPeso(),
                        vehiculo.getConsumoPorKm()
                ))
                .collect(Collectors.toList());
    }

    public void crearVehiculo(Vehiculo vehiculo) {
        vehiculoRepository.guardar(vehiculo);
    }
}


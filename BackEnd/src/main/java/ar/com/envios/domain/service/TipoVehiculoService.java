package ar.com.envios.domain.service;

import ar.com.envios.domain.model.TipoVehiculo;
import ar.com.envios.domain.repository.ITipoVehiculoRepository;
import ar.com.envios.infrastructure.entity.TipoVehiculoEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TipoVehiculoService {

    @Autowired
    private ITipoVehiculoRepository tipoVehiculoRepository;

    public List<TipoVehiculo> obtenerTodos() {
        List<TipoVehiculoEntity> entidades = tipoVehiculoRepository.findAll();
        return entidades.stream()
                .map(entidad -> new TipoVehiculo(
                        entidad.getNombre(),
                        entidad.getCapacidadMaxVolumen(),
                        entidad.getCapacidadMaxPeso(),
                        entidad.getConsumoPorKm()
                ))
                .collect(Collectors.toList());
    }

    // Otros métodos para agregar, actualizar y eliminar tipos de vehículos
}

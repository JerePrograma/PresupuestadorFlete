package ar.com.envios.application.usecase;

import ar.com.envios.application.dto.VehiculoRequest;
import ar.com.envios.application.dto.VehiculoResponse;
import ar.com.envios.domain.model.Vehiculo;
import ar.com.envios.domain.repository.IVehiculoRepository;
import ar.com.envios.application.mapper.VehiculoMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VehiculoUseCase {

    private final IVehiculoRepository vehiculoRepository;

    public VehiculoUseCase(IVehiculoRepository vehiculoRepository) {
        this.vehiculoRepository = vehiculoRepository;
    }

    public VehiculoResponse crearVehiculo(VehiculoRequest request) {
        Vehiculo vehiculo = VehiculoMapper.toDomain(request);
        vehiculoRepository.guardar(vehiculo);
        return VehiculoMapper.toResponse(vehiculo);
    }

    public List<VehiculoResponse> listarVehiculos() {
        return vehiculoRepository.listarTodos()
                .stream()
                .map(VehiculoMapper::toResponse)
                .collect(Collectors.toList());
    }
}

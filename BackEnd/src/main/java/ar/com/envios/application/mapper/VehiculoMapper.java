package ar.com.envios.application.mapper;

import ar.com.envios.application.dto.VehiculoRequest;
import ar.com.envios.application.dto.VehiculoResponse;
import ar.com.envios.domain.model.Vehiculo;
import ar.com.envios.infrastructure.entity.VehiculoEntity;

public class VehiculoMapper {

    public static VehiculoEntity toEntity(Vehiculo vehiculo) {
        return new VehiculoEntity(
                vehiculo.getNombre(),
                vehiculo.getCapacidadMaxVolumen(),
                vehiculo.getCapacidadMaxPeso(),
                vehiculo.getConsumoPorKm()
        );
    }

    public static Vehiculo toDomain(VehiculoEntity vehiculoEntity) {
        return new Vehiculo(
                vehiculoEntity.getNombre(),
                vehiculoEntity.getCapacidadMaxVolumen(),
                vehiculoEntity.getCapacidadMaxPeso(),
                vehiculoEntity.getConsumoPorKm()
        );
    }

    // Nuevo método: Convierte de Vehiculo a VehiculoResponse
    public static VehiculoResponse toResponse(Vehiculo vehiculo) {
        return new VehiculoResponse(
                vehiculo.getNombre(),
                vehiculo.getCapacidadMaxVolumen(),
                vehiculo.getCapacidadMaxPeso(),
                vehiculo.getConsumoPorKm()
        );
    }

    // Opcional: si quieres convertir VehiculoRequest a un Vehiculo de dominio
    public static Vehiculo toDomain(VehiculoRequest request) {
        return new Vehiculo(
                request.getNombre(),
                request.getCapacidadMaxVolumen(),
                request.getCapacidadMaxPeso(),
                request.getConsumoPorKm()
        );
    }
}

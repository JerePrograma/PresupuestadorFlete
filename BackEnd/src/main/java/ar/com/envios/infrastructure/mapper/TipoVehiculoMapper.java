package ar.com.envios.infrastructure.mapper;

import ar.com.envios.domain.model.TipoVehiculo;
import ar.com.envios.infrastructure.entity.TipoVehiculoEntity;

public class TipoVehiculoMapper {

    public static TipoVehiculoEntity toEntity(TipoVehiculo tipoVehiculo) {
        return new TipoVehiculoEntity(
                tipoVehiculo.nombre(),
                tipoVehiculo.capacidadMaxVolumen(),
                tipoVehiculo.capacidadMaxPeso(),
                tipoVehiculo.consumoPorKm()
        );
    }

    public static TipoVehiculo toDomain(TipoVehiculoEntity tipoVehiculoEntity) {
        return new TipoVehiculo(
                tipoVehiculoEntity.getNombre(),
                tipoVehiculoEntity.getCapacidadMaxVolumen(),
                tipoVehiculoEntity.getCapacidadMaxPeso(),
                tipoVehiculoEntity.getConsumoPorKm()
        );
    }
}

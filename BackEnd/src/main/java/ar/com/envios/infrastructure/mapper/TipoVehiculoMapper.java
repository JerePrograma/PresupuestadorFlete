package ar.com.envios.infrastructure.mapper;

import ar.com.envios.domain.model.TipoVehiculo;
import ar.com.envios.infrastructure.entity.TipoVehiculoEntity;

public class TipoVehiculoMapper {

    public static TipoVehiculo toDomain(TipoVehiculoEntity entity) {
        return new TipoVehiculo(
                entity.getNombre(),
                entity.getCostoBasePorKm(),
                entity.getCapacidadMaxVolumen()
        );
    }

    public static TipoVehiculoEntity toEntity(TipoVehiculo domain) {
        TipoVehiculoEntity entity = new TipoVehiculoEntity();
        entity.setNombre(domain.nombre());
        entity.setCostoBasePorKm(domain.costoBasePorKm());
        entity.setCapacidadMaxVolumen(domain.capacidadMaxVolumen());
        return entity;
    }
}

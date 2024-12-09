package ar.com.envios.domain.repository;

import ar.com.envios.domain.model.TipoVehiculo;

import java.util.List;
import java.util.Optional;

/**
 * Repositorio que provee acceso a información de tipos de vehículos
 * (y sus costos asociados).
 */
public interface ITarifasRepository {

    /**
     * Obtiene la lista completa de tipos de vehículo disponibles.
     * @return Lista de TipoVehiculo
     */
    List<TipoVehiculo> findAll();

    /**
     * Busca un tipo de vehículo por su nombre (camioneta, camión grande, etc.)
     * @param nombre Nombre del tipo de vehículo
     * @return Un Optional con el TipoVehiculo si se encuentra, vacío si no existe.
     */
    Optional<TipoVehiculo> findByNombre(String nombre);
}

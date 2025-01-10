package ar.com.envios.domain.repository;

import ar.com.envios.domain.model.Vehiculo;

import java.util.List;
import java.util.Optional;

/**
 * Repositorio que provee acceso a informacion de tipos de vehiculos
 * (y sus costos asociados).
 */
public interface ITarifasRepository {

    /**
     * Obtiene la lista completa de tipos de vehiculo disponibles.
     * @return Lista de TipoVehiculo
     */
    List<Vehiculo> findAll();

    /**
     * Busca un tipo de vehiculo por su nombre (camioneta, camion grande, etc.)
     * @param nombre Nombre del tipo de vehiculo
     * @return Un Optional con el TipoVehiculo si se encuentra, vacio si no existe.
     */
    Optional<Vehiculo> findByNombre(String nombre);
}

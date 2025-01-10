package ar.com.envios.domain.repository;

import ar.com.envios.domain.model.Extra;

import java.util.List;
import java.util.Optional;

/**
 * Repositorio que provee acceso a la lista de extras que se pueden aplicar
 * a un presupuesto.
 */
public interface IExtrasRepository {

    /**
     * Obtiene todos los extras disponibles.
     * @return Lista de Extra
     */
    List<Extra> findAll();

    /**
     * Busca un extra por su nombre (ej: "Seguro adicional", "Embalaje especial").
     * @param nombre Nombre del extra
     * @return Un Optional con el Extra si se encuentra, vacio si no existe.
     */
    Optional<Extra> findByNombre(String nombre);
}

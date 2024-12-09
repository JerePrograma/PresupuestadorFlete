package ar.com.envios.domain.model;

import java.math.BigDecimal;

/**
 * @param capacidadMaxVolumen o capacidad máxima en toneladas, etc.
 */
public record TipoVehiculo(String nombre, BigDecimal costoBasePorKm, double capacidadMaxVolumen) {

    /**
     * Podrías agregar métodos que validen si un cierto volumen de carga
     * es soportado por este tipo de vehículo.
     */
    public boolean soportaVolumen(double volumen) {
        return volumen <= this.capacidadMaxVolumen;
    }
}

package ar.com.envios.application.dto;

import java.math.BigDecimal;

public class VehiculoRequest {
    private String nombre;
    private double capacidadMaxVolumen;
    private double capacidadMaxPeso;
    private BigDecimal consumoPorKm;

    // Getters y setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getCapacidadMaxVolumen() {
        return capacidadMaxVolumen;
    }

    public void setCapacidadMaxVolumen(double capacidadMaxVolumen) {
        this.capacidadMaxVolumen = capacidadMaxVolumen;
    }

    public double getCapacidadMaxPeso() {
        return capacidadMaxPeso;
    }

    public void setCapacidadMaxPeso(double capacidadMaxPeso) {
        this.capacidadMaxPeso = capacidadMaxPeso;
    }

    public BigDecimal getConsumoPorKm() {
        return consumoPorKm;
    }

    public void setConsumoPorKm(BigDecimal consumoPorKm) {
        this.consumoPorKm = consumoPorKm;
    }
}

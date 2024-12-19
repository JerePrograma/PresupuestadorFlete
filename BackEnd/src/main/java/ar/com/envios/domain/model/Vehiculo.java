package ar.com.envios.domain.model;

import java.math.BigDecimal;

public class Vehiculo {
    private String nombre;
    private double capacidadMaxVolumen;
    private double capacidadMaxPeso;
    private BigDecimal consumoPorKm;

    public Vehiculo(String nombre, double capacidadMaxVolumen, double capacidadMaxPeso, BigDecimal consumoPorKm) {
        this.nombre = nombre;
        this.capacidadMaxVolumen = capacidadMaxVolumen;
        this.capacidadMaxPeso = capacidadMaxPeso;
        this.consumoPorKm = consumoPorKm;
    }

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

    public boolean soportaVolumen(double volumen) {
        return volumen <= this.capacidadMaxVolumen;
    }

    public boolean soportaPeso(double peso) {
        return peso <= this.capacidadMaxPeso;
    }
}

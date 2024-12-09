package ar.com.envios.infrastructure.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "tipo_vehiculo")
public class TipoVehiculoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", nullable = false, unique = true)
    private String nombre;

    @Column(name = "costo_base_por_km", nullable = false)
    private BigDecimal costoBasePorKm;

    @Column(name = "capacidad_max_volumen", nullable = false)
    private double capacidadMaxVolumen;

    public TipoVehiculoEntity() {
        // Constructor por defecto para JPA
    }

    public TipoVehiculoEntity(String nombre, BigDecimal costoBasePorKm, double capacidadMaxVolumen) {
        this.nombre = nombre;
        this.costoBasePorKm = costoBasePorKm;
        this.capacidadMaxVolumen = capacidadMaxVolumen;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public BigDecimal getCostoBasePorKm() {
        return costoBasePorKm;
    }

    public void setCostoBasePorKm(BigDecimal costoBasePorKm) {
        this.costoBasePorKm = costoBasePorKm;
    }

    public double getCapacidadMaxVolumen() {
        return capacidadMaxVolumen;
    }

    public void setCapacidadMaxVolumen(double capacidadMaxVolumen) {
        this.capacidadMaxVolumen = capacidadMaxVolumen;
    }
}

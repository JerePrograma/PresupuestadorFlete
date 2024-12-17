package ar.com.envios.infrastructure.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "vehiculos")
public class VehiculoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    @Column(name = "capacidad_max_volumen")
    private double capacidadMaxVolumen;

    @Column(name = "capacidad_max_peso")
    private double capacidadMaxPeso;

    @Column(name = "consumo_por_km")
    private BigDecimal consumoPorKm;

    // Constructor completo
    public VehiculoEntity(String nombre, double capacidadMaxVolumen, double capacidadMaxPeso, BigDecimal consumoPorKm) {
        this.nombre = nombre;
        this.capacidadMaxVolumen = capacidadMaxVolumen;
        this.capacidadMaxPeso = capacidadMaxPeso;
        this.consumoPorKm = consumoPorKm;
    }

    public VehiculoEntity() {
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

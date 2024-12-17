package ar.com.envios.infrastructure.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "tipo_vehiculo")
public class TipoVehiculoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", nullable = false, unique = true)
    private String nombre;

    @Column(name = "capacidad_max_volumen", nullable = false)
    private double capacidadMaxVolumen;

    @Column(name = "capacidad_max_peso", nullable = false)
    private double capacidadMaxPeso;

    @Column(name = "consumo_por_km", nullable = false)
    private BigDecimal consumoPorKm;

    public TipoVehiculoEntity() {
        // Constructor por defecto para JPA
    }

    public TipoVehiculoEntity(String nombre, double capacidadMaxVolumen, double capacidadMaxPeso, BigDecimal consumoPorKm) {
        this.nombre = nombre;
        this.capacidadMaxVolumen = capacidadMaxVolumen;
        this.capacidadMaxPeso = capacidadMaxPeso;
        this.consumoPorKm = consumoPorKm;
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

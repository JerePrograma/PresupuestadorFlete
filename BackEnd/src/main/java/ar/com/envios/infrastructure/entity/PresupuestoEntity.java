package ar.com.envios.infrastructure.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "presupuesto")
public class PresupuestoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "origen", nullable = false)
    private String origen;

    @Column(name = "destino", nullable = false)
    private String destino;

    @Column(name = "volumen_carga", nullable = false)
    private double volumenCarga;

    @Column(name = "peso_carga", nullable = false)
    private double pesoCarga;

    @Column(name = "consumo_por_km", nullable = false)
    private BigDecimal consumoPorKm;

    @Column(name = "costo_base", nullable = false)
    private BigDecimal costoBase;

    // Relación con tipoVehiculo (Many-to-One)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tipo_vehiculo_id", nullable = false)
    private TipoVehiculoEntity tipoVehiculo;

    // Relación con extras (Many-to-Many)
    @ManyToMany
    @JoinTable(
            name = "presupuesto_extra",
            joinColumns = @JoinColumn(name = "presupuesto_id"),
            inverseJoinColumns = @JoinColumn(name = "extra_id")
    )
    private List<ExtraEntity> extras = new ArrayList<>();

    public PresupuestoEntity() {
    }

    public PresupuestoEntity(String origen, String destino, double volumenCarga, double pesoCarga,
                             BigDecimal consumoPorKm, BigDecimal costoBase, TipoVehiculoEntity tipoVehiculo) {
        this.origen = origen;
        this.destino = destino;
        this.volumenCarga = volumenCarga;
        this.pesoCarga = pesoCarga;
        this.consumoPorKm = consumoPorKm != null ? consumoPorKm : BigDecimal.ZERO;
        this.costoBase = costoBase != null ? costoBase : BigDecimal.ZERO;
        this.tipoVehiculo = tipoVehiculo;
    }

    public void addExtra(ExtraEntity extra) {
        this.extras.add(extra);
    }

    // Getters y setters actualizados
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public double getVolumenCarga() {
        return volumenCarga;
    }

    public void setVolumenCarga(double volumenCarga) {
        this.volumenCarga = volumenCarga;
    }

    public double getPesoCarga() {
        return pesoCarga;
    }

    public void setPesoCarga(double pesoCarga) {
        this.pesoCarga = pesoCarga;
    }

    public BigDecimal getConsumoPorKm() {
        return consumoPorKm;
    }

    public void setConsumoPorKm(BigDecimal consumoPorKm) {
        this.consumoPorKm = consumoPorKm;
    }

    public BigDecimal getCostoBase() {
        return costoBase;
    }

    public void setCostoBase(BigDecimal costoBase) {
        this.costoBase = costoBase;
    }

    public TipoVehiculoEntity getTipoVehiculo() {
        return tipoVehiculo;
    }

    public void setTipoVehiculo(TipoVehiculoEntity tipoVehiculo) {
        this.tipoVehiculo = tipoVehiculo;
    }

    public List<ExtraEntity> getExtras() {
        return extras;
    }

    public void setExtras(List<ExtraEntity> extras) {
        this.extras = extras;
    }
}

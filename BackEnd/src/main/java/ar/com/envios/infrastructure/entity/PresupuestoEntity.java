package ar.com.envios.infrastructure.entity;

import jakarta.persistence.*;

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

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<UsuarioEntity> usuariosInvolucrados;

    @Column(name = "presupuesto_total", nullable = false)
    private double presupuestoTotal;

    // Relación con tipoVehiculo (Many-to-One)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tipo_vehiculo_id", nullable = false)
    private VehiculoEntity tipoVehiculo;

    // Relación con extras (Many-to-Many)
    @ManyToMany
    @JoinTable(
            name = "presupuesto_extra",
            joinColumns = @JoinColumn(name = "presupuesto_id"),
            inverseJoinColumns = @JoinColumn(name = "extra_id")
    )
    private List<ExtraEntity> extras = new ArrayList<>();

    public PresupuestoEntity(String origen, String destino, double volumenCarga, double pesoCarga, VehiculoEntity tipoVehiculo, String string) {
    }

    public PresupuestoEntity(String origen, String destino, double volumenCarga, double pesoCarga,
                             VehiculoEntity tipoVehiculo, List<UsuarioEntity> usuariosInvolucrados) {
        this.origen = origen;
        this.destino = destino;
        this.volumenCarga = volumenCarga;
        this.pesoCarga = pesoCarga;
        this.tipoVehiculo = tipoVehiculo;
        this.usuariosInvolucrados = usuariosInvolucrados;
    }

    public PresupuestoEntity() {

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

    public VehiculoEntity getTipoVehiculo() {
        return tipoVehiculo;
    }

    public void setTipoVehiculo(VehiculoEntity tipoVehiculo) {
        this.tipoVehiculo = tipoVehiculo;
    }

    public List<ExtraEntity> getExtras() {
        return extras;
    }

    public void setExtras(List<ExtraEntity> extras) {
        this.extras = extras;
    }

    public double getPresupuestoTotal() {
        return presupuestoTotal;
    }

    public void setPresupuestoTotal(double presupuestoTotal) {
        this.presupuestoTotal = presupuestoTotal;
    }

    public List<UsuarioEntity> getUsuariosInvolucrados() {
        return usuariosInvolucrados;
    }

    public void setUsuariosInvolucrados(List<UsuarioEntity> usuariosInvolucrados) {
        this.usuariosInvolucrados = usuariosInvolucrados;
    }
}

package ar.com.envios.application.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import java.util.List;

public class PresupuestoRequest {
    @NotBlank(message = "El origen es obligatorio")
    private String origen;

    @NotBlank(message = "El destino es obligatorio")
    private String destino;

    @Positive(message = "El volumen debe ser un numero positivo")
    private double volumenCarga;

    @Positive(message = "El peso debe ser un numero positivo")
    private double pesoCarga;

    @NotBlank(message = "El tipo de vehiculo es obligatorio")
    private String nombreTipoVehiculo;

    // Ahora solo IDs de usuarios
    private List<Long> usuariosInvolucrados;

    public PresupuestoRequest() {
    }

    public PresupuestoRequest(String origen, String destino, double volumenCarga, double pesoCarga, String nombreTipoVehiculo, List<Long> usuariosInvolucrados) {
        this.origen = origen;
        this.destino = destino;
        this.volumenCarga = volumenCarga;
        this.pesoCarga = pesoCarga;
        this.nombreTipoVehiculo = nombreTipoVehiculo;
        this.usuariosInvolucrados = usuariosInvolucrados;
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

    public String getNombreTipoVehiculo() {
        return nombreTipoVehiculo;
    }

    public void setNombreTipoVehiculo(String nombreTipoVehiculo) {
        this.nombreTipoVehiculo = nombreTipoVehiculo;
    }

    public List<Long> getUsuariosInvolucrados() {
        return usuariosInvolucrados;
    }

    public void setUsuariosInvolucrados(List<Long> usuariosInvolucrados) {
        this.usuariosInvolucrados = usuariosInvolucrados;
    }
}

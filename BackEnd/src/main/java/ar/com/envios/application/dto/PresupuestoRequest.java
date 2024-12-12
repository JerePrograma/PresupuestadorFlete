package ar.com.envios.application.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

public class PresupuestoRequest {
    private String origen;
    private String destino;
    private double volumenCarga;
    private double pesoCarga;
    private BigDecimal consumoPorKm;
    private String nombreTipoVehiculo;

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

    public String getNombreTipoVehiculo() {
        return nombreTipoVehiculo;
    }

    public void setNombreTipoVehiculo(String nombreTipoVehiculo) {
        this.nombreTipoVehiculo = nombreTipoVehiculo;
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
}

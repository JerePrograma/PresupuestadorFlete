package ar.com.envios.application.dto;

import ar.com.envios.domain.exception.BussinesException;

import java.math.BigDecimal;
import java.util.List;

public class PresupuestoResponse {
    private String origen;
    private String destino;
    private double volumenCarga;
    private String tipoVehiculo;
    private BigDecimal costoTotal;
    private List<String> extras;
    private String mensajeError; // Nuevo campo para mensajes de error

    public PresupuestoResponse() {
    }

    // Constructor completo
    public PresupuestoResponse(String origen, String destino, double volumenCarga, String tipoVehiculo,
                               BigDecimal costoTotal, List<String> extras) {
        this.origen = origen;
        this.destino = destino;
        this.volumenCarga = volumenCarga;
        this.tipoVehiculo = tipoVehiculo;
        this.costoTotal = costoTotal;
        this.extras = extras;
        this.mensajeError = null;
    }

    // Constructor para mensajes de error
    public PresupuestoResponse(String mensajeError) {
        this.mensajeError = mensajeError;
    }

    // Constructor para mensajes de error
    public PresupuestoResponse(String mensajeError, BussinesException e) {
        this.mensajeError = mensajeError;
    }

    // Getters y setters
    public String getMensajeError() {
        return mensajeError;
    }

    public void setMensajeError(String mensajeError) {
        this.mensajeError = mensajeError;
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

    public String getTipoVehiculo() {
        return tipoVehiculo;
    }

    public void setTipoVehiculo(String tipoVehiculo) {
        this.tipoVehiculo = tipoVehiculo;
    }

    public BigDecimal getCostoTotal() {
        return costoTotal;
    }

    public void setCostoTotal(BigDecimal costoTotal) {
        this.costoTotal = costoTotal;
    }

    public List<String> getExtras() {
        return extras;
    }

    public void setExtras(List<String> extras) {
        this.extras = extras;
    }
}

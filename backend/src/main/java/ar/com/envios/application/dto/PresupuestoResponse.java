package ar.com.envios.application.dto;

import java.math.BigDecimal;
import java.util.List;

public class PresupuestoResponse {

    private String origen;
    private String destino;
    private double volumenCarga;
    private String nombreTipoVehiculo;
    private BigDecimal costoTotal;
    private List<String> detalleCostos;

    // Constructor
    public PresupuestoResponse(String origen, String destino, double volumenCarga,
                               String nombreTipoVehiculo, BigDecimal costoTotal, List<String> detalleCostos) {
        this.origen = origen;
        this.destino = destino;
        this.volumenCarga = volumenCarga;
        this.nombreTipoVehiculo = nombreTipoVehiculo;
        this.costoTotal = costoTotal;
        this.detalleCostos = detalleCostos;
    }


    public PresupuestoResponse(String mensajeError) {
    }


    public PresupuestoResponse() {
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

    public String getNombreTipoVehiculo() {
        return nombreTipoVehiculo;
    }

    public void setNombreTipoVehiculo(String nombreTipoVehiculo) {
        this.nombreTipoVehiculo = nombreTipoVehiculo;
    }

    public BigDecimal getCostoTotal() {
        return costoTotal;
    }

    public void setCostoTotal(BigDecimal costoTotal) {
        this.costoTotal = costoTotal;
    }

    public List<String> getDetalleCostos() {
        return detalleCostos;
    }

    public void setDetalleCostos(List<String> detalleCostos) {
        this.detalleCostos = detalleCostos;
    }
}

package ar.com.envios.domain.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Presupuesto {

    private final String origen;
    private final String destino;
    private final double volumenCarga;
    private final double pesoCarga;
    private final TipoVehiculo tipoVehiculo;
    private final BigDecimal consumoPorKm;
    private final List<Extra> extras;

    private BigDecimal costoBase = BigDecimal.ZERO;

    public Presupuesto(String origen, String destino, double volumenCarga, double pesoCarga,
                       TipoVehiculo tipoVehiculo, BigDecimal consumoPorKm) {
        this.origen = origen;
        this.destino = destino;
        this.volumenCarga = volumenCarga;
        this.pesoCarga = pesoCarga;
        this.tipoVehiculo = tipoVehiculo;
        this.consumoPorKm = consumoPorKm;
        this.extras = new ArrayList<>();
    }

    public void agregarExtra(Extra extra) {
        this.extras.add(extra);
    }

    public BigDecimal calcularTotal() {
        BigDecimal total = costoBase;
        for (Extra extra : extras) {
            total = total.add(extra.costo());
        }
        return total;
    }

    // Getters y setters actualizados
    public String getOrigen() {
        return origen;
    }

    public String getDestino() {
        return destino;
    }

    public double getVolumenCarga() {
        return volumenCarga;
    }

    public double getPesoCarga() {
        return pesoCarga;
    }

    public TipoVehiculo getTipoVehiculo() {
        return tipoVehiculo;
    }

    public BigDecimal getConsumoPorKm() {
        return consumoPorKm;
    }

    public List<Extra> getExtras() {
        return extras;
    }

    public BigDecimal getCostoBase() {
        return costoBase;
    }

    public void setCostoBase(BigDecimal costoBase) {
        this.costoBase = costoBase;
    }
}

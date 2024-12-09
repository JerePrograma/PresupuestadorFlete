package ar.com.envios.domain.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Presupuesto {

    private final String origen;
    private final String destino;
    // Por ejemplo, volumen o peso de la carga, si es pertinente:
    private final double volumenCarga; // o pesoCarga, depende de la lógica
    private final TipoVehiculo tipoVehiculo;
    private final List<Extra> extras;

    // Se calcula durante la lógica. Podríamos almacenar un resultado final
    // o calcularlo a demanda con un método getTotal().

    private BigDecimal costoBase = BigDecimal.valueOf(100);

    public Presupuesto(String origen, String destino, double volumenCarga, TipoVehiculo tipoVehiculo) {
        this.origen = origen;
        this.destino = destino;
        this.volumenCarga = volumenCarga;
        this.tipoVehiculo = tipoVehiculo;
        this.extras = new ArrayList<>();
        this.costoBase = BigDecimal.ZERO;
    }

    public void agregarExtra(Extra extra) {
        this.extras.add(extra);
    }

    /**
     * Calcula el costo total: costo base + extras.
     * Este método es un ejemplo. La lógica real puede ser más compleja.
     */
    public BigDecimal calcularTotal() {
        BigDecimal total = costoBase;
        for (Extra extra : extras) {
            total = total.add(extra.costo());
        }
        return total;
    }

    public String getOrigen() {
        return origen;
    }

    public String getDestino() {
        return destino;
    }

    public double getVolumenCarga() {
        return volumenCarga;
    }

    public TipoVehiculo getTipoVehiculo() {
        return tipoVehiculo;
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

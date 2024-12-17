package ar.com.envios.domain.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Presupuesto {
    private final String origen;
    private final String destino;
    private final double volumenCarga;
    private final double pesoCarga;
    private final Vehiculo vehiculo;
    private final List<Usuario> usuariosInvolucrados;
    private final List<Extra> extras;

    public Presupuesto(String origen, String destino, double volumenCarga, double pesoCarga,
                       Vehiculo vehiculo, List<Usuario> usuariosInvolucrados) {
        this.origen = origen;
        this.destino = destino;
        this.volumenCarga = volumenCarga;
        this.pesoCarga = pesoCarga;
        this.vehiculo = vehiculo;
        this.usuariosInvolucrados = usuariosInvolucrados;
        this.extras = new ArrayList<>();
    }

    public void agregarExtra(Extra extra) {
        this.extras.add(extra);
    }

    public BigDecimal calcularTotal() {
        BigDecimal total = new BigDecimal("0");
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

    public Vehiculo getTipoVehiculo() {
        return vehiculo;
    }

    public List<Extra> getExtras() {
        return extras;
    }

    public List<Usuario> getUsuariosInvolucrados() {
        return usuariosInvolucrados;
    }
}

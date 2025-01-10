package ar.com.envios.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
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
            total = total.add(extra.getCosto());
        }
        return total;
    }
}

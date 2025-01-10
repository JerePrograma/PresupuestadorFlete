package ar.com.envios.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Vehiculo {
    private String nombre;
    private double capacidadMaxVolumen;
    private double capacidadMaxPeso;
    private BigDecimal consumoPorKm;

    public boolean soportaVolumen(double volumen) {
        return volumen <= this.capacidadMaxVolumen;
    }

    public boolean soportaPeso(double peso) {
        return peso <= this.capacidadMaxPeso;
    }
}

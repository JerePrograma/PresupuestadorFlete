package ar.com.envios.domain.model;

import java.math.BigDecimal;

public class Extra {
    private String nombre;
    private BigDecimal costo;

    public Extra(String nombre, BigDecimal costo) {
        this.nombre = nombre;
        this.costo = costo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public BigDecimal getCosto() {
        return costo;
    }

    public void setCosto(BigDecimal costo) {
        this.costo = costo;
    }
}

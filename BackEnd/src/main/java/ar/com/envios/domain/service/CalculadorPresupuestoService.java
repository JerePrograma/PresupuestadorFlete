package ar.com.envios.domain.service;

import ar.com.envios.domain.model.Presupuesto;
import ar.com.envios.domain.model.TipoVehiculo;

import java.math.BigDecimal;

/**
 * Servicio de dominio para calcular el costo total de un presupuesto.
 * Este servicio no conoce detalles de persistencia ni de frameworks.
 * Su responsabilidad es aplicar las reglas de negocio asociadas al cálculo.
 */
public class CalculadorPresupuestoService {

    /**
     * Calcula el costo total del presupuesto.
     * Ejemplo de lógica:
     * - Costo base = distancia * costoBasePorKm del tipo de vehículo.
     * - A ese costo base se le suman los extras que ya estén en el Presupuesto.
     *
     * @param presupuesto   El presupuesto a calcular.
     * @param distanciaKm   La distancia aproximada del traslado en kilómetros.
     * @return El total calculado.
     */
    public BigDecimal calcular(Presupuesto presupuesto, double distanciaKm) {
        TipoVehiculo tipoVehiculo = presupuesto.getTipoVehiculo();

        // Validar que el vehículo soporte el volumen:
        if(!tipoVehiculo.soportaVolumen(presupuesto.getVolumenCarga())) {
            throw new IllegalArgumentException("El vehículo no soporta el volumen de la carga.");
        }

        // Costo base = distancia * costoBasePorKm del vehículo
        BigDecimal costoBase = tipoVehiculo.costoBasePorKm().multiply(BigDecimal.valueOf(distanciaKm));
        presupuesto.setCostoBase(costoBase);

        // Cálculo total = costoBase + extras
        return presupuesto.calcularTotal();
    }
}


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
     * @param presupuesto El presupuesto a calcular.
     * @return El total calculado.
     */
    public BigDecimal calcular(Presupuesto presupuesto/*, boolean incluyePeajes*/) {
        TipoVehiculo tipoVehiculo = presupuesto.getTipoVehiculo();

        if (!tipoVehiculo.soportaVolumen(presupuesto.getVolumenCarga())) {
            throw new IllegalArgumentException("El vehículo no soporta el volumen de la carga.");
        }

        if (!tipoVehiculo.soportaPeso(presupuesto.getPesoCarga())) {
            throw new IllegalArgumentException("El vehículo no soporta el peso de la carga.");
        }


        // Obtener distancia real entre origen y destino
        double distanciaKm = obtenerDistanciaReal(presupuesto.getOrigen(), presupuesto.getDestino());

        // Obtener precio actual del combustible
        BigDecimal precioCombustible = obtenerPrecioCombustibleActual();

        BigDecimal costoCombustible = tipoVehiculo.consumoPorKm()
                .multiply(precioCombustible)
                .multiply(BigDecimal.valueOf(distanciaKm));
//        BigDecimal costoPeajes = incluyePeajes ? calcularCostoPeajes(distanciaKm) : BigDecimal.ZERO;

        // Costo total
        BigDecimal costoTotal = costoCombustible/*.add(costoPeajes)*/;

        presupuesto.setCostoBase(costoTotal);
        return costoTotal;
    }

    public double obtenerDistanciaReal(String origen, String destino) {
        return 10;
    }

    public BigDecimal obtenerPrecioCombustibleActual() {
        return new BigDecimal(10);
    }

}


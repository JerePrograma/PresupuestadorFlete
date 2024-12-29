package ar.com.envios.domain.service;

import ar.com.envios.domain.model.Presupuesto;
import ar.com.envios.domain.model.Vehiculo;
import ar.com.envios.domain.model.Extra;

import java.math.BigDecimal;

/**
 * Servicio de dominio para calcular el costo total de un presupuesto.
 * Este servicio no conoce detalles de persistencia ni de frameworks.
 * Su responsabilidad es aplicar las reglas de negocio asociadas al cálculo.
 */
public class CalculadorPresupuestoService {

    private final IDistanceCalculator distanceCalculator;

    public CalculadorPresupuestoService(IDistanceCalculator distanceCalculator) {
        this.distanceCalculator = distanceCalculator;
    }

    public double obtenerDistanciaReal(String origen, String destino) {
        return distanceCalculator.calculateDistance(origen, destino);
    }
    /**
     * Calcula el costo total del presupuesto.
     * Ejemplo de lógica:
     * - Costo base = distancia * costoBasePorKm del tipo de vehículo.
     * - A ese costo base se le suman los extras que ya estén en el Presupuesto.
     *
     * @param presupuesto El presupuesto a calcular.
     * @return El total calculado.
     */
    public BigDecimal calcular(Presupuesto presupuesto) {
        Vehiculo vehiculo = presupuesto.getVehiculo();

        if (!vehiculo.soportaVolumen(presupuesto.getVolumenCarga())) {
            throw new IllegalArgumentException("El vehículo no soporta el volumen de la carga.");
        }

        if (!vehiculo.soportaPeso(presupuesto.getPesoCarga())) {
            throw new IllegalArgumentException("El vehículo no soporta el peso de la carga.");
        }

        // Obtener distancia real entre origen y destino
        double distanciaKm = obtenerDistanciaReal(presupuesto.getOrigen(), presupuesto.getDestino());

        // Obtener precio actual del combustible
        BigDecimal precioCombustible = obtenerPrecioCombustibleActual();

        // Calcular costo base
        BigDecimal costoBase = vehiculo.getConsumoPorKm()
                .multiply(precioCombustible)
                .multiply(BigDecimal.valueOf(distanciaKm));

        // Sumar costos de los extras
        BigDecimal costoExtras = BigDecimal.ZERO;
        for (Extra extra : presupuesto.getExtras()) {
            costoExtras = costoExtras.add(extra.getCosto());
        }

        // Retornar el costo total (costo base + extras)
        return costoBase.add(costoExtras);
    }

    public BigDecimal obtenerPrecioCombustibleActual() {
        // Implementación ficticia para consulta de precio del combustible
        return BigDecimal.valueOf(1.25); // Precio de ejemplo por litro
    }

}


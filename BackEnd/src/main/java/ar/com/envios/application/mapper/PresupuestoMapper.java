package ar.com.envios.application.mapper;

import ar.com.envios.application.dto.PresupuestoRequest;
import ar.com.envios.application.dto.PresupuestoResponse;
import ar.com.envios.domain.model.Extra;
import ar.com.envios.domain.model.Presupuesto;

import java.util.stream.Collectors;

public class PresupuestoMapper {

    /**
     * Convierte un Presupuesto de dominio a un DTO de respuesta.
     *
     * @param presupuesto El objeto del dominio
     * @return El DTO PresupuestoResponse
     */
    public static PresupuestoResponse toResponse(Presupuesto presupuesto) {
        return new PresupuestoResponse(
                presupuesto.getOrigen(),
                presupuesto.getDestino(),
                presupuesto.getVolumenCarga(),
                presupuesto.getTipoVehiculo().nombre(),
                presupuesto.getCostoBase(),
                presupuesto.calcularTotal(),
                presupuesto.getExtras().stream().map(Extra::nombre).collect(Collectors.toList())
        );
    }

    /**
     * Opcional: si quieres mapear un PresupuestoRequest a un objeto del dominio,
     * aunque en el caso actual lo hacemos en el caso de uso. Sin embargo,
     * podrías necesitarlo para aislar esa lógica.
     */
    public static Presupuesto fromRequest(PresupuestoRequest request) {
        // Aquí no tenemos todavía un TipoVehiculo en el request,
        // el caso de uso lo obtiene del repositorio.
        // Por ahora, devolvemos null o no implementamos este método.
        // Podríamos hacer una versión parcial o requerir el TipoVehiculo como parámetro adicional.

        return null;
    }
}

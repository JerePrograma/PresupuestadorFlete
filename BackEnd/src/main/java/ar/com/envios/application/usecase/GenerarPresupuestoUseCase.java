package ar.com.envios.application.usecase;

import ar.com.envios.domain.model.Presupuesto;
import ar.com.envios.domain.model.TipoVehiculo;
import ar.com.envios.domain.repository.ITarifasRepository;
import ar.com.envios.domain.repository.ITipoVehiculoRepository;
import ar.com.envios.domain.service.CalculadorPresupuestoService;
import ar.com.envios.infrastructure.adapter.out.persistence.IPresupuestoRepository;
import ar.com.envios.infrastructure.entity.PresupuestoEntity;
import ar.com.envios.infrastructure.entity.TipoVehiculoEntity;
import ar.com.envios.infrastructure.mapper.TipoVehiculoMapper;

import java.math.BigDecimal;
import java.util.Optional;

/**
 * Caso de uso para generar un nuevo presupuesto basado en datos de entrada.
 */
public class GenerarPresupuestoUseCase {

    private final ITarifasRepository tarifasRepository;
    private final CalculadorPresupuestoService calculadorPresupuestoService;
    private final IPresupuestoRepository presupuestoRepository;
    private final ITipoVehiculoRepository tipoVehiculoRepository;

    public GenerarPresupuestoUseCase(ITarifasRepository tarifasRepository,
                                     CalculadorPresupuestoService calculadorPresupuestoService,
                                     IPresupuestoRepository presupuestoRepository,
                                     ITipoVehiculoRepository tipoVehiculoRepository) {
        this.tarifasRepository = tarifasRepository;
        this.calculadorPresupuestoService = calculadorPresupuestoService;
        this.presupuestoRepository = presupuestoRepository;
        this.tipoVehiculoRepository = tipoVehiculoRepository;
    }

    /**
     * Genera un presupuesto, calculando su costo total.
     *
     * @param origen             Ubicación de origen
     * @param destino            Ubicación de destino
     * @param volumenCarga       Volumen o peso de la carga a trasladar
     * @param nombreTipoVehiculo Nombre del tipo de vehículo (ej: "Camioneta")
     * @param distanciaKm        Distancia aproximada en km
     * @return Un objeto Presupuesto con su costo total calculado
     * @throws IllegalArgumentException Si no se encuentra el tipo de vehículo o falla alguna validación.
     */
    public Presupuesto ejecutar(String origen, String destino, double volumenCarga,
                                String nombreTipoVehiculo, double distanciaKm) {

        // Buscar el tipo de vehículo en el repositorio de dominio
        Optional<TipoVehiculo> tipoVehiculoOpt = tarifasRepository.findByNombre(nombreTipoVehiculo);
        if (tipoVehiculoOpt.isEmpty()) {
            throw new IllegalArgumentException("No se encontró el tipo de vehículo: " + nombreTipoVehiculo);
        }

        TipoVehiculo tipoVehiculo = tipoVehiculoOpt.get();

        // Crear el presupuesto de dominio
        Presupuesto presupuesto = new Presupuesto(origen, destino, volumenCarga, tipoVehiculo);

        // Calcular el costo total usando el servicio de dominio
        BigDecimal total = calculadorPresupuestoService.calcular(presupuesto, distanciaKm);
        presupuesto.setCostoBase(tipoVehiculo.costoBasePorKm().multiply(BigDecimal.valueOf(distanciaKm)));

        // Convertir a TipoVehiculoEntity
        TipoVehiculoEntity tipoVehiculoEntity = tipoVehiculoRepository
                .findByNombre(tipoVehiculo.nombre())
                .orElseGet(() -> {
                    // Si no existe, persiste el TipoVehiculoEntity
                    TipoVehiculoEntity nuevoTipoVehiculoEntity = TipoVehiculoMapper.toEntity(tipoVehiculo);
                    return tipoVehiculoRepository.save(nuevoTipoVehiculoEntity);
                });

        // Crear la entidad Presupuesto
        PresupuestoEntity entity = new PresupuestoEntity(
                presupuesto.getOrigen(),
                presupuesto.getDestino(),
                presupuesto.getVolumenCarga(),
                presupuesto.getCostoBase(),
                tipoVehiculoEntity
        );

        // Guardar PresupuestoEntity
        PresupuestoEntity saved = presupuestoRepository.save(entity);

        return presupuesto;
    }

}

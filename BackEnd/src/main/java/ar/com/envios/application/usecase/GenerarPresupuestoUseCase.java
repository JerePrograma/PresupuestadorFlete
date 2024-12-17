package ar.com.envios.application.usecase;

import ar.com.envios.domain.model.Presupuesto;
import ar.com.envios.domain.model.TipoVehiculo;
import ar.com.envios.domain.repository.ITarifasRepository;
import ar.com.envios.domain.repository.ITipoVehiculoRepository;
import ar.com.envios.domain.service.CalculadorPresupuestoService;
import ar.com.envios.domain.repository.IPresupuestoRepository;
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
     * @return Un objeto Presupuesto con su costo total calculado
     * @throws IllegalArgumentException Si no se encuentra el tipo de vehículo o falla alguna validación.
     */
    public Presupuesto ejecutar(String origen, String destino, double volumenCarga, double pesoCarga, BigDecimal consumoPorKm, String nombreTipoVehiculo) {
        // Buscar el tipo de vehículo
        Optional<TipoVehiculo> tipoVehiculoOpt = tarifasRepository.findByNombre(nombreTipoVehiculo);
        if (tipoVehiculoOpt.isEmpty()) {
            throw new IllegalArgumentException("No se encontró el tipo de vehículo: " + nombreTipoVehiculo);
        }

        TipoVehiculo tipoVehiculo = tipoVehiculoOpt.get();

        // Validar volumen y peso
        if (!tipoVehiculo.soportaVolumen(volumenCarga)) {
            throw new IllegalArgumentException("El vehículo no soporta el volumen de la carga.");
        }
        if (!tipoVehiculo.soportaPeso(pesoCarga)) {
            throw new IllegalArgumentException("El vehículo no soporta el peso de la carga.");
        }

        // Crear el presupuesto
        Presupuesto presupuesto = new Presupuesto(origen, destino, volumenCarga, pesoCarga, tipoVehiculo, consumoPorKm);

        // Calcular costos
        BigDecimal costoBase = calculadorPresupuestoService.calcular(presupuesto);
        presupuesto.setCostoBase(costoBase);

        // Guardar el presupuesto
        PresupuestoEntity entity = new PresupuestoEntity(
                presupuesto.getOrigen(),
                presupuesto.getDestino(),
                presupuesto.getVolumenCarga(),
                presupuesto.getPesoCarga(),
                presupuesto.getConsumoPorKm(),
                presupuesto.getCostoBase(),
                convertirTipoVehiculo(tipoVehiculo)
        );

        presupuestoRepository.guardar(entity);

        return presupuesto;
    }

    private TipoVehiculoEntity convertirTipoVehiculo(TipoVehiculo tipoVehiculo) {
        // Utilizar el mapper para convertir el objeto de dominio a la entidad
        return TipoVehiculoMapper.toEntity(tipoVehiculo);
    }

}

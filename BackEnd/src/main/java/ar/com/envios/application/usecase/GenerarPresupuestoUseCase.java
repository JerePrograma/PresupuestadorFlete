package ar.com.envios.application.usecase;

import ar.com.envios.domain.model.Presupuesto;
import ar.com.envios.domain.model.Vehiculo;
import ar.com.envios.domain.model.Usuario;
import ar.com.envios.domain.repository.ITarifasRepository;
import ar.com.envios.domain.repository.IPresupuestoRepository;
import ar.com.envios.infrastructure.entity.PresupuestoEntity;
import ar.com.envios.infrastructure.entity.VehiculoEntity;
import ar.com.envios.application.mapper.VehiculoMapper;
import ar.com.envios.application.mapper.UsuarioMapper;

import java.util.List;
import java.util.Optional;

/*
 * Caso de uso para generar un nuevo presupuesto basado en datos de entrada.
 */
import org.springframework.stereotype.Component;

@Component
public class GenerarPresupuestoUseCase {
    private final ITarifasRepository tarifasRepository;
    private final IPresupuestoRepository presupuestoRepository;

    public GenerarPresupuestoUseCase(ITarifasRepository tarifasRepository,
                                     IPresupuestoRepository presupuestoRepository) {
        this.tarifasRepository = tarifasRepository;
        this.presupuestoRepository = presupuestoRepository;
    }

    /**
     * Genera un presupuesto, calculando su costo total.
     *
     * @param origen             Ubicacion de origen
     * @param destino            Ubicacion de destino
     * @param volumenCarga       Volumen o peso de la carga a trasladar
     * @param nombreTipoVehiculo Nombre del tipo de vehiculo (ej: "Camioneta")
     * @return Un objeto Presupuesto con su costo total calculado
     * @throws IllegalArgumentException Si no se encuentra el tipo de vehiculo o falla alguna validacion.
     */
    public Presupuesto ejecutar(String origen, String destino, double volumenCarga, double pesoCarga,
                                String nombreTipoVehiculo, List<Usuario> usuariosInvolucrados) {
        Optional<Vehiculo> tipoVehiculoOpt = tarifasRepository.findByNombre(nombreTipoVehiculo);
        if (tipoVehiculoOpt.isEmpty()) {
            throw new IllegalArgumentException("No se encontro el tipo de vehiculo: " + nombreTipoVehiculo);
        }

        Vehiculo vehiculo = tipoVehiculoOpt.get();

        if (!vehiculo.soportaVolumen(volumenCarga)) {
            throw new IllegalArgumentException("El vehiculo no soporta el volumen de la carga.");
        }
        if (!vehiculo.soportaPeso(pesoCarga)) {
            throw new IllegalArgumentException("El vehiculo no soporta el peso de la carga.");
        }

        // Solo crear y retornar el presupuesto de dominio
        return new Presupuesto(origen, destino, volumenCarga, pesoCarga, vehiculo, usuariosInvolucrados);
    }


    private VehiculoEntity convertirTipoVehiculo(Vehiculo vehiculo) {
        // Utilizar el mapper para convertir el objeto de dominio a la entidad
        return VehiculoMapper.toEntity(vehiculo);
    }

}

package ar.com.envios.application.usecase;

import ar.com.envios.domain.model.TipoVehiculo;
import ar.com.envios.domain.model.Presupuesto;
import ar.com.envios.domain.repository.ITarifasRepository;
import ar.com.envios.domain.repository.ITipoVehiculoRepository;
import ar.com.envios.domain.service.CalculadorPresupuestoService;
import ar.com.envios.domain.repository.IPresupuestoRepository;
import ar.com.envios.infrastructure.entity.PresupuestoEntity;
import ar.com.envios.infrastructure.entity.TipoVehiculoEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.util.Optional;

class GenerarPresupuestoUseCaseTest {

    @Test
    void testGenerarPresupuesto_ConDatosValidos() {
        // Datos de entrada
        String origen = "Buenos Aires";
        String destino = "La Plata";
        double volumenCarga = 500.0; // soportado por la camioneta
        double pesoCarga = 100.0; // Peso dentro del límite
        String nombreTipoVehiculo = "Camioneta";
        BigDecimal consumoPorKm = new BigDecimal("1.0");

        // Mock del repositorio
        ITarifasRepository mockITarifasRepository = Mockito.mock(ITarifasRepository.class);
        IPresupuestoRepository mockIPresupuestoRepository = Mockito.mock(IPresupuestoRepository.class);
        ITipoVehiculoRepository mockITipoVehiculoRepository = Mockito.mock(ITipoVehiculoRepository.class);

        // Definimos el TipoVehiculo simulado
        TipoVehiculo camioneta = new TipoVehiculo(
                "Camioneta",
                1000.0, // Capacidad máxima de volumen
                1000.0, // Capacidad máxima de peso
                new BigDecimal("2.00") // Consumo por km
        );

        // Configuramos el mock para retornar la camioneta al buscar por nombre
        Mockito.when(mockITarifasRepository.findByNombre(nombreTipoVehiculo))
                .thenReturn(Optional.of(camioneta));

        // Creamos el servicio de dominio sin mocks (es puro)
        GenerarPresupuestoUseCase generarPresupuestoUseCase = getGenerarPresupuestoUseCase(mockITarifasRepository, mockIPresupuestoRepository, mockITipoVehiculoRepository);

        // Ejecutamos el caso de uso
        Presupuesto presupuesto = generarPresupuestoUseCase.ejecutar(
                origen, destino, volumenCarga, pesoCarga, consumoPorKm, nombreTipoVehiculo
        );

        // Validamos resultados
        BigDecimal expectedTotal = new BigDecimal("200.00"); // Costo base esperado
        Assertions.assertEquals(expectedTotal, presupuesto.calcularTotal(), "El total del presupuesto debe ser 200.00");
        Assertions.assertEquals(origen, presupuesto.getOrigen());
        Assertions.assertEquals(destino, presupuesto.getDestino());
        Assertions.assertEquals(volumenCarga, presupuesto.getVolumenCarga());
        Assertions.assertEquals(nombreTipoVehiculo, presupuesto.getTipoVehiculo().nombre());
    }

    private static GenerarPresupuestoUseCase getGenerarPresupuestoUseCase(ITarifasRepository mockITarifasRepository, IPresupuestoRepository mockIPresupuestoRepository, ITipoVehiculoRepository mockITipoVehiculoRepository) {
        CalculadorPresupuestoService calculadorPresupuestoService = new CalculadorPresupuestoService();

        // Instanciamos el caso de uso con los mocks y servicios
        return new GenerarPresupuestoUseCase(
                mockITarifasRepository,
                calculadorPresupuestoService,
                mockIPresupuestoRepository,
                mockITipoVehiculoRepository
        );
    }

    @Test
    void testGenerarPresupuesto_SinTipoVehiculo() {
        // Si el tipo de vehículo no existe, esperamos una excepción.
        ITarifasRepository mockITarifasRepository = Mockito.mock(ITarifasRepository.class);
        IPresupuestoRepository mockIPresupuestoRepository = Mockito.mock(IPresupuestoRepository.class);
        ITipoVehiculoRepository mockITipoVehiculoRepository = Mockito.mock(ITipoVehiculoRepository.class);
        Mockito.when(mockITarifasRepository.findByNombre("Inexistente"))
                .thenReturn(Optional.empty());

        GenerarPresupuestoUseCase useCase = getGenerarPresupuestoUseCase(mockITarifasRepository, mockIPresupuestoRepository, mockITipoVehiculoRepository);

        Assertions.assertThrows(IllegalArgumentException.class, () ->
                useCase.ejecutar("Origen", "Destino", 100.0, 50.0, new BigDecimal("0"), "Inexistente")
        );
    }

    @Test
    void testGuardarPresupuesto() {
        // Mock de IPresupuestoRepository
        IPresupuestoRepository mockPresupuestoRepository = Mockito.mock(IPresupuestoRepository.class);

        // Datos para el test
        TipoVehiculoEntity tipoVehiculo = new TipoVehiculoEntity(
                "Auto",
                1000.0, // Capacidad máxima de volumen
                500.0,  // Capacidad máxima de peso
                new BigDecimal("2.00") // Consumo por km
        );

        PresupuestoEntity entity = new PresupuestoEntity(
                "Origen",
                "Destino",
                20.0, // Volumen de carga
                50.0, // Peso de carga
                new BigDecimal("2.00"), // Consumo por km
                BigDecimal.valueOf(1000.0), // Costo base
                tipoVehiculo
        );

        // Configuramos el comportamiento del mock
        Mockito.doNothing().when(mockPresupuestoRepository).guardar(entity);

        // Ejecutamos el método guardar
        mockPresupuestoRepository.guardar(entity);

        // Validamos que el método guardar fue llamado correctamente
        Mockito.verify(mockPresupuestoRepository, Mockito.times(1)).guardar(entity);
    }

}

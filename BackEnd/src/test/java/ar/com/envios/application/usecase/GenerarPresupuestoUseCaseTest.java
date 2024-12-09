package ar.com.envios.application.usecase;

import ar.com.envios.domain.model.TipoVehiculo;
import ar.com.envios.domain.model.Presupuesto;
import ar.com.envios.domain.repository.ITarifasRepository;
import ar.com.envios.domain.repository.ITipoVehiculoRepository;
import ar.com.envios.domain.service.CalculadorPresupuestoService;
import ar.com.envios.infrastructure.adapter.out.persistence.IPresupuestoRepository;
import ar.com.envios.infrastructure.entity.PresupuestoEntity;
import ar.com.envios.infrastructure.entity.TipoVehiculoEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class GenerarPresupuestoUseCaseTest {

    @Test
    void testGenerarPresupuesto_ConDatosValidos() {
        // Datos de entrada
        String origen = "Buenos Aires";
        String destino = "La Plata";
        double volumenCarga = 500.0; // soportado por la camioneta
        String nombreTipoVehiculo = "Camioneta";
        double distanciaKm = 100.0;

        // Mock del repositorio
        ITarifasRepository mockITarifasRepository = Mockito.mock(ITarifasRepository.class);
        IPresupuestoRepository mockIPresupuestoRepository = Mockito.mock(IPresupuestoRepository.class);
        ITipoVehiculoRepository mockITipoVehiculoRepository = Mockito.mock(ITipoVehiculoRepository.class);

        // Definimos el TipoVehiculo simulado
        TipoVehiculo camioneta = new TipoVehiculo(
                "Camioneta",
                BigDecimal.valueOf(2.00),
                1000.0
        );

        // Configuramos el mock para retornar la camioneta al buscar por nombre
        Mockito.when(mockITarifasRepository.findByNombre(nombreTipoVehiculo))
                .thenReturn(Optional.of(camioneta));

        // Creamos el servicio de dominio sin mocks (es puro)
        CalculadorPresupuestoService calculadorPresupuestoService = new CalculadorPresupuestoService();

        // Instanciamos el caso de uso con los mocks y servicios
        GenerarPresupuestoUseCase generarPresupuestoUseCase = new GenerarPresupuestoUseCase(mockITarifasRepository, calculadorPresupuestoService, mockIPresupuestoRepository, mockITipoVehiculoRepository);

        // Ejecutamos el caso de uso
        Presupuesto presupuesto = generarPresupuestoUseCase.ejecutar(
                origen, destino, volumenCarga, nombreTipoVehiculo, distanciaKm
        );

        // Validamos resultados
        // Costo base esperado: 2.00 * 100km = 200.00
        // No hay extras, por lo que el total también debería ser 200.00
        BigDecimal expectedTotal = new BigDecimal("200.00");
        Assertions.assertEquals(expectedTotal, presupuesto.calcularTotal(), "El total del presupuesto debe ser 200.00");
        Assertions.assertEquals(origen, presupuesto.getOrigen());
        Assertions.assertEquals(destino, presupuesto.getDestino());
        Assertions.assertEquals(volumenCarga, presupuesto.getVolumenCarga());
        Assertions.assertEquals(nombreTipoVehiculo, presupuesto.getTipoVehiculo().nombre());
    }

    @Test
    void testGenerarPresupuesto_SinTipoVehiculo() {
        // Si el tipo de vehículo no existe, esperamos una excepción.
        ITarifasRepository mockITarifasRepository = Mockito.mock(ITarifasRepository.class);
        IPresupuestoRepository mockIPresupuestoRepository = Mockito.mock(IPresupuestoRepository.class);
        ITipoVehiculoRepository mockITipoVehiculoRepository = Mockito.mock(ITipoVehiculoRepository.class);
        Mockito.when(mockITarifasRepository.findByNombre("Inexistente"))
                .thenReturn(Optional.empty());

        CalculadorPresupuestoService calculadorPresupuestoService = new CalculadorPresupuestoService();
        GenerarPresupuestoUseCase useCase = new GenerarPresupuestoUseCase(mockITarifasRepository, calculadorPresupuestoService, mockIPresupuestoRepository, mockITipoVehiculoRepository);

        Assertions.assertThrows(IllegalArgumentException.class, () ->
                useCase.ejecutar("Origen", "Destino", 100.0, "Inexistente", 50.0)
        );
    }

    @Test
    public void testGuardarPresupuesto() {
        IPresupuestoRepository presupuestoRepository = Mockito.mock(IPresupuestoRepository.class);
        TipoVehiculoEntity tipoVehiculo = new TipoVehiculoEntity("Auto", new BigDecimal(100), 100);
        PresupuestoEntity entity = new PresupuestoEntity(
                "Origen",
                "Destino",
                20.0,
                BigDecimal.valueOf(1000.0), // Aquí prueba con un valor explícito
                tipoVehiculo
        );
        PresupuestoEntity saved = presupuestoRepository.save(entity);
        System.out.println("Presupuesto guardado: " + saved);
        assertNotNull(saved.getCostoBase());
    }

}

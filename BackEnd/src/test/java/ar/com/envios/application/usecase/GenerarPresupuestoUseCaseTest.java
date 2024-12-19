package ar.com.envios.application.usecase;

import ar.com.envios.domain.enumeraciones.TipoUsuario;
import ar.com.envios.domain.model.Vehiculo;
import ar.com.envios.domain.model.Presupuesto;
import ar.com.envios.domain.model.Usuario;
import ar.com.envios.domain.repository.ITarifasRepository;
import ar.com.envios.domain.repository.IPresupuestoRepository;
import ar.com.envios.infrastructure.entity.PresupuestoEntity;
import ar.com.envios.infrastructure.entity.VehiculoEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

class GenerarPresupuestoUseCaseTest {

    @Test
    void testGenerarPresupuesto_ConDatosValidos() {
        // Datos de entrada
        String origen = "Buenos Aires";
        String destino = "La Plata";
        double volumenCarga = 500.0;
        double pesoCarga = 100.0;
        String nombreTipoVehiculo = "Camioneta";
        List<Usuario> usuariosInvolucrados = List.of(new Usuario(1L, "Chofer", "chofer@mail.com","asd", TipoUsuario.CHOFER));

        // Mock del repositorio
        ITarifasRepository mockITarifasRepository = Mockito.mock(ITarifasRepository.class);
        IPresupuestoRepository mockIPresupuestoRepository = Mockito.mock(IPresupuestoRepository.class);

        // Mock del TipoVehiculo
        Vehiculo camioneta = new Vehiculo("Camioneta", 1000.0, 1000.0, new BigDecimal("2.00"));
        Mockito.when(mockITarifasRepository.findByNombre(nombreTipoVehiculo)).thenReturn(Optional.of(camioneta));

        // Caso de uso
        GenerarPresupuestoUseCase useCase = getGenerarPresupuestoUseCase(mockITarifasRepository, mockIPresupuestoRepository);

        // Ejecución
        Presupuesto presupuesto = useCase.ejecutar(origen, destino, volumenCarga, pesoCarga, nombreTipoVehiculo, usuariosInvolucrados);

        // Validaciones
        Assertions.assertEquals(origen, presupuesto.getOrigen());
        Assertions.assertEquals(destino, presupuesto.getDestino());
        Assertions.assertEquals(volumenCarga, presupuesto.getVolumenCarga());
        Assertions.assertEquals(pesoCarga, presupuesto.getPesoCarga());
        Assertions.assertEquals(nombreTipoVehiculo, presupuesto.getTipoVehiculo().getNombre());
        Assertions.assertEquals(usuariosInvolucrados, presupuesto.getUsuariosInvolucrados());
    }


    private static GenerarPresupuestoUseCase getGenerarPresupuestoUseCase(ITarifasRepository mockITarifasRepository, IPresupuestoRepository mockIPresupuestoRepository) {

        // Instanciamos el caso de uso con los mocks y servicios
        return new GenerarPresupuestoUseCase(
                mockITarifasRepository,
                mockIPresupuestoRepository
        );
    }

    @Test
    void testGenerarPresupuesto_TipoVehiculoNoEncontrado() {
        // Configuración del test: repositorios mockeados
        ITarifasRepository mockITarifasRepository = Mockito.mock(ITarifasRepository.class);
        IPresupuestoRepository mockIPresupuestoRepository = Mockito.mock(IPresupuestoRepository.class);

        // Simular que no se encuentra el tipo de vehículo
        String tipoVehiculoInexistente = "Inexistente";
        Mockito.when(mockITarifasRepository.findByNombre(tipoVehiculoInexistente))
                .thenReturn(Optional.empty());

        // Instanciar el caso de uso
        GenerarPresupuestoUseCase useCase = getGenerarPresupuestoUseCase(
                mockITarifasRepository,
                mockIPresupuestoRepository
        );

        // Ejecución y validación
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () ->
                useCase.ejecutar("Buenos Aires", "La Plata", 100.0, 50.0, tipoVehiculoInexistente, List.of())
        );

        // Verificar mensaje de error
        Assertions.assertEquals("No se encontró el tipo de vehículo: " + tipoVehiculoInexistente, exception.getMessage());
    }


//    @Test
//    void testGuardarPresupuesto() {
//        // Mock de IPresupuestoRepository
//        IPresupuestoRepository mockPresupuestoRepository = Mockito.mock(IPresupuestoRepository.class);
//
//        // Datos para el test
//        PresupuestoEntity entity = getPresupuestoEntity();
//
//        // Configuramos el comportamiento del mock
//        Mockito.doNothing().when(mockPresupuestoRepository).guardar(entity);
//
//        // Ejecutamos el método guardar
//        mockPresupuestoRepository.guardar(entity);
//
//        // Validamos que el método guardar fue llamado correctamente
//        Mockito.verify(mockPresupuestoRepository, Mockito.times(1)).guardar(entity);
//    }
//
//    private static PresupuestoEntity getPresupuestoEntity() {
//        VehiculoEntity tipoVehiculo = new VehiculoEntity(
//                "Auto",
//                1000.0, // Capacidad máxima de volumen
//                500.0,  // Capacidad máxima de peso
//                new BigDecimal("2.00") // Consumo por km
//        );
//
//        String tipoPersonalInvolucrado = "";
//        return new PresupuestoEntity(
//                "Origen",
//                "Destino",
//                20.0, // Volumen de carga
//                50.0, // Peso de carga
//                tipoVehiculo,
//                tipoPersonalInvolucrado
//        );
//    }

}

package ar.com.envios.infrastructure.adapter.in.web;

import ar.com.envios.application.dto.PresupuestoRequest;
import ar.com.envios.application.dto.PresupuestoResponse;
import ar.com.envios.application.mapper.PresupuestoMapper;
import ar.com.envios.application.usecase.GenerarPresupuestoUseCase;
import ar.com.envios.domain.model.Presupuesto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/presupuestos")
@CrossOrigin(origins = "http://localhost:8100") // Habilita CORS para este endpoint
public class PresupuestoController {

    private final GenerarPresupuestoUseCase generarPresupuestoUseCase;

    public PresupuestoController(GenerarPresupuestoUseCase generarPresupuestoUseCase) {
        this.generarPresupuestoUseCase = generarPresupuestoUseCase;
    }

    @PostMapping("/crear")
    public ResponseEntity<PresupuestoResponse> crearPresupuesto(@RequestBody PresupuestoRequest request) {
        try {
            // Validación básica de entrada
            if (request.getConsumoPorKm() == null || request.getNombreTipoVehiculo() == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(new PresupuestoResponse("Faltan datos requeridos"));
            }

            // Ejecutar el caso de uso
            Presupuesto presupuesto = generarPresupuestoUseCase.ejecutar(
                    request.getOrigen(),
                    request.getDestino(),
                    request.getVolumenCarga(),
                    request.getPesoCarga(),
                    request.getConsumoPorKm(),
                    request.getNombreTipoVehiculo()
            );

            // Mapear el resultado a una respuesta
            PresupuestoResponse response = PresupuestoMapper.toResponse(presupuesto);

            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (IllegalArgumentException e) {
            // Si hay un error en los datos o validaciones
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new PresupuestoResponse("Error en los datos: " + e.getMessage()));
        } catch (Exception e) {
            // Cualquier otro error interno
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new PresupuestoResponse("Ocurrió un error interno al crear el presupuesto"));
        }
    }
}

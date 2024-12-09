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
@CrossOrigin(origins = "http://localhost:5173") // Habilita CORS para este endpoint
public class PresupuestoController {

    private final GenerarPresupuestoUseCase generarPresupuestoUseCase;

    public PresupuestoController(GenerarPresupuestoUseCase generarPresupuestoUseCase) {
        this.generarPresupuestoUseCase = generarPresupuestoUseCase;
    }

    @PostMapping("/crear")
    public ResponseEntity<PresupuestoResponse> crearPresupuesto(@RequestBody PresupuestoRequest request) {
        try {
            Presupuesto presupuesto = generarPresupuestoUseCase.ejecutar(
                    request.getOrigen(),
                    request.getDestino(),
                    request.getVolumenCarga(),
                    request.getNombreTipoVehiculo(),
                    request.getDistanciaKm()
            );

            PresupuestoResponse response = PresupuestoMapper.toResponse(presupuesto);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (IllegalArgumentException e) {
            // Si no se encuentra el vehículo o hay un error en los datos, se retorna Bad Request
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } catch (Exception e) {
            // Cualquier otro error interno se retorna como Internal Server Error
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}

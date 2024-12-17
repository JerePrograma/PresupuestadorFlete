package ar.com.envios.infrastructure.adapter.in.web;

import ar.com.envios.application.dto.PresupuestoRequest;
import ar.com.envios.application.dto.PresupuestoResponse;
import ar.com.envios.application.usecase.GenerarPresupuestoUseCase;
import ar.com.envios.infrastructure.services.PresupuestoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping("api/presupuestos")
@CrossOrigin(origins = "http://localhost:8100") // Habilita CORS para este endpoint
public class PresupuestoController {

    private final PresupuestoService presupuestoService;

    public PresupuestoController(PresupuestoService presupuestoService) {
        this.presupuestoService = presupuestoService;
    }

    @PostMapping("/crear")
    public ResponseEntity<PresupuestoResponse> crearPresupuesto(@Valid @RequestBody PresupuestoRequest request, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            String errores = bindingResult.getAllErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.joining(", "));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new PresupuestoResponse("Errores de validación: " + errores));
        }

        try {
            PresupuestoResponse response = presupuestoService.crearPresupuesto(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new PresupuestoResponse("Error en los datos: " + e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new PresupuestoResponse("Ocurrió un error interno al crear el presupuesto"));
        }
    }
}


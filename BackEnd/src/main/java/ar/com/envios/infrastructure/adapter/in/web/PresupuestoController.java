package ar.com.envios.infrastructure.adapter.in.web;

import ar.com.envios.application.dto.PresupuestoRequest;
import ar.com.envios.application.dto.PresupuestoResponse;
import ar.com.envios.application.dto.UsuarioResponse;
import ar.com.envios.application.dto.VehiculoResponse;
import ar.com.envios.application.service.PresupuestoService;
import ar.com.envios.application.service.UsuarioService;
import ar.com.envios.application.service.VehiculoService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/presupuestos")
@SecurityRequirement(name = "bearer-key") // Aplica el esquema de seguridad al controlador
public class PresupuestoController {

    private final PresupuestoService presupuestoService;
    private final UsuarioService usuarioService;
    private final VehiculoService vehiculoService;

    public PresupuestoController(PresupuestoService presupuestoService,
                                 UsuarioService usuarioService,
                                 VehiculoService vehiculoService) {
        this.presupuestoService = presupuestoService;
        this.usuarioService = usuarioService;
        this.vehiculoService = vehiculoService;
    }

    @PostMapping("/crear")
    public ResponseEntity<PresupuestoResponse> crearPresupuesto(@Valid @RequestBody PresupuestoRequest request,
                                                                BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            String errores = bindingResult.getAllErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.joining(", "));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new PresupuestoResponse("Errores de validacion: " + errores));
        }

        try {
            PresupuestoResponse response = presupuestoService.crearPresupuesto(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new PresupuestoResponse("Error en los datos: " + e.getMessage()));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new PresupuestoResponse("Error interno: " + e.getMessage()));
        }
    }

    @GetMapping("/usuarios-disponibles")
    public ResponseEntity<List<UsuarioResponse>> listarUsuariosDisponibles() {
        List<UsuarioResponse> usuarios = usuarioService.obtenerUsuariosPorRolesDisponibles(List.of("CHOFER", "AYUDANTE"));
        return ResponseEntity.ok(usuarios);
    }


    @GetMapping("/vehiculos-disponibles")
    public ResponseEntity<List<VehiculoResponse>> listarVehiculosDisponibles() {
        try {
            List<VehiculoResponse> vehiculos = vehiculoService.obtenerTodos().stream()
                    .map(vehiculo -> new VehiculoResponse(
                            vehiculo.getNombre(),
                            vehiculo.getCapacidadMaxVolumen(),
                            vehiculo.getCapacidadMaxPeso(),
                            vehiculo.getConsumoPorKm()
                    ))
                    .collect(Collectors.toList());
            return ResponseEntity.ok(vehiculos);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(List.of());
        }
    }
}

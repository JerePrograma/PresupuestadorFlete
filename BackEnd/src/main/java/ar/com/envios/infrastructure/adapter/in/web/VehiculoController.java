package ar.com.envios.infrastructure.adapter.in.web;

import ar.com.envios.application.dto.VehiculoRequest;
import ar.com.envios.application.dto.VehiculoResponse;
import ar.com.envios.application.usecase.VehiculoUseCase;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vehiculos")
public class VehiculoController {

    private final VehiculoUseCase vehiculoUseCase;

    public VehiculoController(VehiculoUseCase vehiculoUseCase) {
        this.vehiculoUseCase = vehiculoUseCase;
    }

    @PostMapping("/crear")
    public ResponseEntity<VehiculoResponse> crearVehiculo(@Valid @RequestBody VehiculoRequest request) {
        VehiculoResponse vehiculo = vehiculoUseCase.crearVehiculo(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(vehiculo);
    }

    @GetMapping("/listar")
    public ResponseEntity<List<VehiculoResponse>> listarVehiculos() {
        List<VehiculoResponse> vehiculos = vehiculoUseCase.listarVehiculos();
        return ResponseEntity.ok(vehiculos);
    }
}

package ar.com.envios.infrastructure.adapter.in.web;

import ar.com.envios.application.dto.PresupuestoResponse;
import ar.com.envios.application.dto.UsuarioRequest;
import ar.com.envios.application.dto.UsuarioResponse;
import ar.com.envios.application.service.UsuarioService;
import ar.com.envios.application.usecase.UsuarioUseCase;
import ar.com.envios.domain.enumeraciones.TipoUsuario;
import ar.com.envios.domain.model.Usuario;
import ar.com.envios.domain.repository.IUsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final IUsuarioRepository usuarioRepository;
    private final UsuarioService usuarioService;

    public UsuarioController(IUsuarioRepository usuarioRepository, UsuarioService usuarioService) {
        this.usuarioRepository = usuarioRepository;
        this.usuarioService = usuarioService;
    }

    @PostMapping("/crear")
    public ResponseEntity<UsuarioResponse> crearUsuario(@RequestBody UsuarioRequest request, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            String errores = bindingResult.getAllErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.joining(", "));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new UsuarioResponse("Errores de validación: " + errores));
        }
        try {
            UsuarioResponse response = usuarioService.crearUsuario(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new UsuarioResponse("Error en los datos: " + e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new UsuarioResponse("Ocurrió un error interno al crear el presupuesto"));
        }
    }

}

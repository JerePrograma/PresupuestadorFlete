package ar.com.envios.infrastructure.adapter.in.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ar.com.envios.application.dto.UsuarioRequest;
import ar.com.envios.application.dto.UsuarioResponse;
import ar.com.envios.application.service.UsuarioService;
import ar.com.envios.domain.repository.IUsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final IUsuarioRepository usuarioRepository;
    private final UsuarioService usuarioService;
    private static final Logger logger = LoggerFactory.getLogger(UsuarioController.class);

    public UsuarioController(IUsuarioRepository usuarioRepository, UsuarioService usuarioService) {
        this.usuarioRepository = usuarioRepository;
        this.usuarioService = usuarioService;
    }


    @PostMapping("/crear")
    public ResponseEntity<UsuarioResponse> crearUsuario(@Valid @RequestBody UsuarioRequest request, BindingResult bindingResult) {
        request.setTipoUsuario(request.getTipoUsuario().toUpperCase());
        logger.info("Request recibido: nombre={}, email={}, tipoUsuario={}, password={}",
                request.getNombre(), request.getEmail(), request.getTipoUsuario(), request.getPassword());

        if (bindingResult.hasErrors()) {
            String errores = bindingResult.getAllErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.joining(", "));
            logger.error("Errores de validación: {}", errores);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new UsuarioResponse("Errores de validación: " + errores));
        }
        try {
            UsuarioResponse response = usuarioService.crearUsuario(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (IllegalArgumentException e) {
            logger.error("Error en los datos: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new UsuarioResponse("Error en los datos: " + e.getMessage()));
        } catch (Exception e) {
            logger.error("Error interno al crear el usuario: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new UsuarioResponse("Ocurrió un error interno al crear el usuario"));
        }
    }
}

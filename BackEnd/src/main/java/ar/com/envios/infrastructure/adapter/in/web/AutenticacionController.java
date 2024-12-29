/********************************************************
 * infrastructure/adapter/in/web/AutenticacionController.java
 ********************************************************/
package ar.com.envios.infrastructure.adapter.in.web;

import ar.com.envios.application.dto.LoginRequest;
import ar.com.envios.application.dto.LoginResponse;
import ar.com.envios.infrastructure.security.SecurityUser;
import ar.com.envios.infrastructure.service.TokenService;
import ar.com.envios.domain.model.Usuario;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/login")
public class AutenticacionController {

    private final AuthenticationManager manager;
    private final TokenService tokenService;

    public AutenticacionController(AuthenticationManager manager, TokenService tokenService) {
        this.manager = manager;
        this.tokenService = tokenService;
    }

    @PostMapping
    public ResponseEntity<?> realizarLogin(@RequestBody @Valid LoginRequest request) {
        var authToken = new UsernamePasswordAuthenticationToken(
                request.getEmail(),
                request.getPassword()
        );

        // Se autentica
        var authentication = manager.authenticate(authToken);

        // principal = SecurityUser
        SecurityUser securityUser = (SecurityUser) authentication.getPrincipal();
        Usuario domainUser = securityUser.getDomainUser();

        // Generamos token con datos de la entidad dominio
        String JWTtoken = tokenService.generateToken(domainUser);
        System.out.println(JWTtoken);
        return ResponseEntity.ok(new LoginResponse(JWTtoken));
    }
}

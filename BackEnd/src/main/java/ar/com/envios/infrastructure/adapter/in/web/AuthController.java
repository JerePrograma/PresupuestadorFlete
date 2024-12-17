package ar.com.envios.infrastructure.adapter.in.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import ar.com.envios.application.dto.LoginRequest;
import ar.com.envios.application.dto.LoginResponse;
import ar.com.envios.infrastructure.security.JwtUtil;

@RestController
@RequestMapping("/api")
public class AuthController {

    private AuthenticationManager authenticationManager;

    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

        String jwt = jwtUtil.generateToken(authentication);
        return new LoginResponse(jwt, "Login exitoso");
    }
}

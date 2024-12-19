package ar.com.envios.configuration;

import ar.com.envios.application.service.UsuarioService;
import ar.com.envios.infrastructure.configuration.SecurityConfig;
import ar.com.envios.infrastructure.security.JwtUtil;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

@Configuration
public class TestSecurityConfig extends SecurityConfig {

    public TestSecurityConfig(UsuarioService usuarioService, JwtUtil jwtUtil) {
        super(jwtUtil, usuarioService);
    }

    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable(); // Deshabilitar CSRF solo para los tests
    }
}

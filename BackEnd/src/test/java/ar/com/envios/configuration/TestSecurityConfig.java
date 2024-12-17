package ar.com.envios.configuration;

import ar.com.envios.infrastructure.configuration.SecurityConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

@Configuration
public class TestSecurityConfig extends SecurityConfig {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable(); // Deshabilitar CSRF solo para los tests
    }
}

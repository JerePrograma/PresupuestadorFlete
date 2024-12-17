package ar.com.envios.infrastructure.configuration;

import ar.com.envios.application.service.UsuarioService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authManager(HttpSecurity http, UsuarioService usuarioService) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(usuarioService)
                .passwordEncoder(passwordEncoder())
                .and()
                .build();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .cors() // Habilitar CORS
                .and()
                .csrf().disable() // Deshabilitar CSRF
                .authorizeRequests(auth -> auth
                        .requestMatchers("/api/usuarios/**", "/api/vehiculos/**").permitAll() // Permite el acceso sin autenticación
                        .anyRequest().authenticated()
                )
                .formLogin().disable() // Deshabilita el formulario de login predeterminado
                .httpBasic(); // Opcional: Permite autenticación básica HTTP
        return http.build();
    }

    protected void configure(HttpSecurity http) throws Exception {

    }
}

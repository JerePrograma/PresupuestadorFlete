package ar.com.envios.infrastructure.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
public class WebConfig {

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        // Solo los orígenes que realmente necesitas
        configuration.setAllowedOrigins(List.of(
                "http://localhost:3000",
                "http://localhost:8100",
                "http://jereprograma.com",
                "https://jereprograma.com",
		"https://jereprograma.com/login"
        ));
        configuration.setAllowedMethods(List.of("GET", "POST", "OPTIONS", "PUT", "DELETE"));
        configuration.setAllowedHeaders(List.of("Content-Type", "Authorization"));
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        // Aplica a todas las rutas
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}

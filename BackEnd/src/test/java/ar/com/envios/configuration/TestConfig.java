package ar.com.envios.configuration;

import ar.com.envios.application.usecase.GenerarPresupuestoUseCase;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestConfig {

    @Bean
    public GenerarPresupuestoUseCase generarPresupuestoUseCase() {
        return Mockito.mock(GenerarPresupuestoUseCase.class);
    }
}
package ar.com.envios.infrastructure.configuration;

import ar.com.envios.application.usecase.GenerarPresupuestoUseCase;
import ar.com.envios.domain.enumeraciones.TipoUsuario;
import ar.com.envios.domain.model.Usuario;
import ar.com.envios.domain.repository.IUsuarioRepository;
import ar.com.envios.domain.service.CalculadorPresupuestoService;
import ar.com.envios.domain.repository.ITarifasRepository;
import ar.com.envios.domain.repository.IPresupuestoRepository;
import ar.com.envios.domain.service.IDistanceCalculator;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ApplicationConfig {

    private final ITarifasRepository tarifasRepository;

    public ApplicationConfig(ITarifasRepository tarifasRepository) {
        this.tarifasRepository = tarifasRepository;
    }

    @Bean
    public CalculadorPresupuestoService calculadorPresupuestoService(IDistanceCalculator distanceCalculator) {
        return new CalculadorPresupuestoService(distanceCalculator);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public GenerarPresupuestoUseCase generarPresupuestoUseCase(IPresupuestoRepository presupuestoRepository) {
        return new GenerarPresupuestoUseCase(tarifasRepository, presupuestoRepository);
    }
}

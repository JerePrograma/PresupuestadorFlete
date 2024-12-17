package ar.com.envios.infrastructure.configuration;

import ar.com.envios.application.usecase.GenerarPresupuestoUseCase;
import ar.com.envios.domain.repository.IUsuarioRepository;
import ar.com.envios.domain.service.CalculadorPresupuestoService;
import ar.com.envios.domain.repository.ITarifasRepository;
import ar.com.envios.domain.repository.IPresupuestoRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ApplicationConfig {

    private final ITarifasRepository tarifasRepository;
    private final IUsuarioRepository usuarioRepository;

    public ApplicationConfig(ITarifasRepository tarifasRepository, IUsuarioRepository usuarioRepository) {
        this.tarifasRepository = tarifasRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @Bean
    public CalculadorPresupuestoService calculadorPresupuestoService() {
        return new CalculadorPresupuestoService();
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public GenerarPresupuestoUseCase generarPresupuestoUseCase(IPresupuestoRepository presupuestoRepository) {
        return new GenerarPresupuestoUseCase(tarifasRepository,
                presupuestoRepository);
    }
}

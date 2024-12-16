package ar.com.envios.infrastructure.configuration;

import ar.com.envios.application.usecase.GenerarPresupuestoUseCase;
import ar.com.envios.domain.repository.ITipoVehiculoRepository;
import ar.com.envios.domain.service.CalculadorPresupuestoService;
import ar.com.envios.domain.repository.ITarifasRepository;
import ar.com.envios.domain.repository.IPresupuestoRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ApplicationConfig {

    private final ITarifasRepository tarifasRepository;

    public ApplicationConfig(ITarifasRepository tarifasRepository) {
        this.tarifasRepository = tarifasRepository;
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
    public GenerarPresupuestoUseCase generarPresupuestoUseCase(CalculadorPresupuestoService calculadorPresupuestoService,
                                                               IPresupuestoRepository presupuestoRepository,
                                                               ITipoVehiculoRepository tipoVehiculoRepository) {
        return new GenerarPresupuestoUseCase(tarifasRepository,
                calculadorPresupuestoService,
                presupuestoRepository,
                tipoVehiculoRepository);
    }
}

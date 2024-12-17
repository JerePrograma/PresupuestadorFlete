package ar.com.envios.infrastructure.adapter.in.web;

import ar.com.envios.application.dto.PresupuestoRequest;
import ar.com.envios.application.usecase.GenerarPresupuestoUseCase;
import ar.com.envios.configuration.TestConfig;
import ar.com.envios.configuration.TestSecurityConfig;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PresupuestoController.class)
@Import({TestConfig.class, TestSecurityConfig.class})
class PresupuestoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private GenerarPresupuestoUseCase generarPresupuestoUseCase;

    @Test
    void testCrearPresupuesto() throws Exception {
        PresupuestoRequest request = new PresupuestoRequest();
        request.setOrigen("Buenos Aires");
        request.setDestino("La Plata");
        request.setVolumenCarga(500.0);
        request.setPesoCarga(100.0);
        request.setNombreTipoVehiculo("Camioneta");

        Mockito.when(generarPresupuestoUseCase.ejecutar(
                Mockito.anyString(),
                Mockito.anyString(),
                Mockito.anyDouble(),
                Mockito.anyDouble(),
                Mockito.anyString(),
                Mockito.anyList()
        )).thenReturn(null);

        mockMvc.perform(post("/api/presupuestos/crear")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated());
    }
}

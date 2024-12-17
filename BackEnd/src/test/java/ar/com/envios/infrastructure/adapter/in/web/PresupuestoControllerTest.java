package ar.com.envios.infrastructure.adapter.in.web;

import ar.com.envios.application.dto.PresupuestoRequest;
import ar.com.envios.application.usecase.GenerarPresupuestoUseCase;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PresupuestoController.class)
class PresupuestoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GenerarPresupuestoUseCase generarPresupuestoUseCase;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testCrearPresupuesto() throws Exception {
        PresupuestoRequest request = new PresupuestoRequest();
        request.setOrigen("Buenos Aires");
        request.setDestino("La Plata");
        request.setVolumenCarga(500.0);
        request.setPesoCarga(100.0);
        request.setConsumoPorKm(new BigDecimal("2.00"));
        request.setNombreTipoVehiculo("Camioneta");

        Mockito.when(generarPresupuestoUseCase.ejecutar(
                Mockito.anyString(),
                Mockito.anyString(),
                Mockito.anyDouble(),
                Mockito.anyDouble(),
                Mockito.eq(new BigDecimal("2.00")),
                Mockito.anyString()
        )).thenReturn(null);

        mockMvc.perform(post("/api/presupuestos/crear")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated());
    }

    @Test
    void testCrearPresupuestoConError() throws Exception {
        PresupuestoRequest request = new PresupuestoRequest();
        request.setOrigen("Buenos Aires");
        request.setDestino("La Plata");
        request.setVolumenCarga(500.0);
        request.setPesoCarga(100.0);
        request.setConsumoPorKm(null);
        request.setNombreTipoVehiculo("Camioneta");

        Mockito.when(generarPresupuestoUseCase.ejecutar(
                Mockito.anyString(),
                Mockito.anyString(),
                Mockito.anyDouble(),
                Mockito.anyDouble(),
                Mockito.isNull(),
                Mockito.anyString()
        )).thenThrow(new IllegalArgumentException("Faltan datos requeridos"));

        mockMvc.perform(post("/api/presupuestos/crear")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }
}

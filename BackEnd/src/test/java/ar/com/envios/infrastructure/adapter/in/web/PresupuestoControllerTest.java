package ar.com.envios.infrastructure.adapter.in.web;

import ar.com.envios.application.dto.PresupuestoRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// Suponemos que ya hay configurado el contexto, repositorios, etc.
// y que GenerarPresupuestoUseCase depende de repos mockeados aquí.
@SpringBootTest
@org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
class PresupuestoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper; // Para serializar/deserializar JSON

    @Test
    void testCrearPresupuesto() throws Exception {
        PresupuestoRequest request = new PresupuestoRequest();
        request.setOrigen("Buenos Aires");
        request.setDestino("La Plata");
        request.setVolumenCarga(500.0);
        request.setNombreTipoVehiculo("Camioneta");
        request.setDistanciaKm(100.0);

        mockMvc.perform(post("/presupuestos")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated());

        // Podrías agregar más validaciones, por ejemplo, parsear la respuesta
        // y verificar ciertos campos.
    }
}

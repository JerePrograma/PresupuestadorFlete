package ar.com.envios.infrastructure.adapter.in.web;

import ar.com.envios.application.dto.PresupuestoRequest;
import ar.com.envios.application.dto.PresupuestoResponse;
import ar.com.envios.application.service.PresupuestoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ExtendWith(MockitoExtension.class)
class PresupuestoControllerTest {

    @Mock
    private PresupuestoService presupuestoService;

    @InjectMocks
    private PresupuestoController presupuestoController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(presupuestoController).build();
    }

    @Test
    void testCrearPresupuesto() throws Exception {
        // Configuración del Request y Response
        PresupuestoRequest request = new PresupuestoRequest(
                "Buenos Aires",
                "La Plata",
                500.0,
                1000.0,
                "Camioneta",
                List.of()
        );

        PresupuestoResponse response = new PresupuestoResponse(
                "Buenos Aires",
                "La Plata",
                500.0,
                "Camioneta",
                new BigDecimal("2000.00"),
                List.of("Costo Total")
        );

        // Mock exacto con cualquier request
        Mockito.when(presupuestoService.crearPresupuesto(Mockito.any(PresupuestoRequest.class)))
                .thenReturn(response);

        // Ejecución y validación del MockMvc
        mockMvc.perform(post("/api/presupuestos/crear")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                            {
                                "origen": "Buenos Aires",
                                "destino": "La Plata",
                                "volumenCarga": 500.0,
                                "pesoCarga": 1000.0,
                                "nombreTipoVehiculo": "Camioneta",
                                "usuariosInvolucrados": []
                            }
                            """))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.origen").value("Buenos Aires"))
                .andExpect(jsonPath("$.destino").value("La Plata"))
                .andExpect(jsonPath("$.volumenCarga").value(500.0))
                .andExpect(jsonPath("$.nombreTipoVehiculo").value("Camioneta"))
                .andExpect(jsonPath("$.costoTotal").value(2000.00))
                .andExpect(jsonPath("$.detalleCostos[0]").value("Costo Total"));

    }

}

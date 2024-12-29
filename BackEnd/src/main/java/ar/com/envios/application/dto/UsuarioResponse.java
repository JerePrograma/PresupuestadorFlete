package ar.com.envios.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioResponse {
    private Long id;
    private String nombre;
    private String email;
    private String tipoUsuario;
    private boolean disponible; // <-- nuevo campo

    private String mensajeError;

    // Constructor para mensajes de error
    public UsuarioResponse(String mensajeError) {
        this.mensajeError = mensajeError;
    }
}

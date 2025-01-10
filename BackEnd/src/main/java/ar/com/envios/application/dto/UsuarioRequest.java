package ar.com.envios.application.dto;

import ar.com.envios.domain.enumeraciones.TipoUsuario;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioRequest {
    private Long id;

    @NotBlank(message = "El nombre no puede estar vacio")
    private String nombre;

    @Email(message = "Debe proporcionar un email valido")
    @NotBlank(message = "El email no puede estar vacio")
    private String email;

    @NotBlank(message = "La contraseña no puede estar vacia")
    @Size(min = 6, message = "La contraseña debe tener al menos 6 caracteres")
    private String password;

    @NotNull(message = "El tipo de usuario no puede ser nulo")
    private TipoUsuario tipoUsuario;

    private boolean disponible; // <-- nuevo campo
}

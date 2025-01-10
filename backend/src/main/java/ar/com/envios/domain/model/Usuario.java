/***********************************************
 * ar.com.envios.domain.model.Usuario
 ***********************************************/
package ar.com.envios.domain.model;

import ar.com.envios.domain.enumeraciones.TipoUsuario;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {
    private Long id;
    private String nombre;
    private String email;
    private TipoUsuario tipoUsuario;
    private String password;
    private boolean disponible; // <-- nuevo campo
}


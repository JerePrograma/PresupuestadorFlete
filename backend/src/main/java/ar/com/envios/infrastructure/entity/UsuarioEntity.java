/***********************************************
 * ar.com.envios.infrastructure.entity.UsuarioEntity
 ***********************************************/
package ar.com.envios.infrastructure.entity;

import ar.com.envios.domain.enumeraciones.TipoUsuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "usuarios")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    @Column(unique = true, nullable = false)
    private String email;

    private String password;

    @Enumerated(EnumType.STRING)
    private TipoUsuario tipoUsuario;

    @Column(name="disponible", nullable=false)
    private boolean disponible;

}

package ar.com.envios.infrastructure.configuration;

import ar.com.envios.domain.enumeraciones.TipoUsuario;
import ar.com.envios.domain.model.Usuario;
import ar.com.envios.domain.repository.IUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class SuperAdminInitializer {

    private final IUsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public SuperAdminInitializer(IUsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }
// CREAR UN SUPERADMIN AL LANZAR EL PROGRAMA
    @EventListener(ApplicationReadyEvent.class)
    public void initializeAdminUser() {
        usuarioRepository.buscarPorEmail("superadmin@mail.com").ifPresentOrElse(
                admin -> System.out.println("Usuario admin ya existe: " + admin.getEmail()),
                () -> {
                    Usuario superAdmin = new Usuario();
                    superAdmin.setEmail("superadmin@mail.com");
                    superAdmin.setPassword(passwordEncoder.encode("superpassword"));
                    superAdmin.setTipoUsuario(TipoUsuario.SUPERADMIN);
                    usuarioRepository.guardar(superAdmin);
                    System.out.println("Usuario admin creado: superadmin@mail.com");
                }
        );
    }
}

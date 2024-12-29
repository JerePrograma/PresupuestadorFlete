/*******************************************************
 * infrastructure/security/AutenticacionService.java
 *******************************************************/
package ar.com.envios.infrastructure.security;

import ar.com.envios.domain.model.Usuario;
import ar.com.envios.domain.repository.IUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AutenticacionService implements org.springframework.security.core.userdetails.UserDetailsService {

    @Autowired
    private IUsuarioRepository repository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // Buscamos en BD
        Usuario usuario = repository.buscarPorEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + email));

        // Retornamos un "SecurityUser" que implementa UserDetails
        return new SecurityUser(usuario);
    }
}

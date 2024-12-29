/********************************************************
 * infrastructure/security/SecurityUser.java
 ********************************************************/
package ar.com.envios.infrastructure.security;

import ar.com.envios.domain.model.Usuario;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class SecurityUser implements UserDetails {

    private final Usuario domainUser;

    public SecurityUser(Usuario domainUser) {
        this.domainUser = domainUser;
    }

    /**
     * Permite acceder al Usuario de dominio
     * una vez que Spring Security autentica.
     */
    public Usuario getDomainUser() {
        return domainUser;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Asignamos un "ROLE_" + tipoUsuario (p. ej. ROLE_CHOFER)
        return List.of(new SimpleGrantedAuthority("ROLE_" + domainUser.getTipoUsuario().name()));
    }

    @Override
    public String getPassword() {
        return domainUser.getPassword();
    }

    @Override
    public String getUsername() {
        return domainUser.getEmail();
    }

    /**
     * Métodos extra: supón que todos están habilitados/no expirados,
     * o ajusta según tu lógica
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        // Podrías usar domainUser.isDisponible() si quieres
        return true;
    }
}

package ar.com.envios.domain.model;

import ar.com.envios.domain.enumeraciones.TipoUsuario;

public class Usuario {
    private Long id;
    private String nombre;
    private String email;
    private String password; // Nueva propiedad
    private TipoUsuario tipoUsuario;

    public Usuario(Long id, String nombre, String email, String password, TipoUsuario tipoUsuario) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.password = password;
        this.tipoUsuario = tipoUsuario;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(TipoUsuario tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }
}

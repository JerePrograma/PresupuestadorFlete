package ar.com.envios.application.dto;

public class UsuarioResponse {
    private Long id;
    private String nombre;
    private String email;
    private String tipoUsuario;
    private String mensajeError;

    public UsuarioResponse(Long id, String nombre, String email, String tipoUsuario) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.tipoUsuario = tipoUsuario;
    }
    // Constructor para mensajes de error
    public UsuarioResponse(String mensajeError) {
        this.mensajeError = mensajeError;
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

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public String getMensajeError() {
        return mensajeError;
    }

    public void setMensajeError(String mensajeError) {
        this.mensajeError = mensajeError;
    }
}

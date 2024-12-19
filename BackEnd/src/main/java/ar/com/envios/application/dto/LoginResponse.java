package ar.com.envios.application.dto;

public class LoginResponse {
    private String token;
    private String mensaje;

    public LoginResponse(String token, String mensaje) {
        this.token = token;
        this.mensaje = mensaje;
    }

    public String getToken() {
        return token;
    }

    public String getMensaje() {
        return mensaje;
    }
}

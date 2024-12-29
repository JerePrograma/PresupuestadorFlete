package ar.com.envios.application.dto;

public class LoginResponse {
    private String token;

    public LoginResponse(String token) {
        this.token = token;
    }

    // Es INDISPENSABLE tener este getter
    public String getToken() {
        return token;
    }
}

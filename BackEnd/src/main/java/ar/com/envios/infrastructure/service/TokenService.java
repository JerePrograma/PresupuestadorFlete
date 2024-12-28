package ar.com.envios.infrastructure.service;

import ar.com.envios.domain.model.Usuario;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${jwt.secret}")
    private String secret;

    public String generateToken(Usuario usuario) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer("PresupuestadorFlete")
                    .withSubject(usuario.getEmail())
                    .withClaim("id", usuario.getId())
                    .withExpiresAt(generarFechaExpiracion())
                    .sign(algorithm);
        } catch (JWTCreationException exception) {
            throw new RuntimeException("Error al generar el token", exception);
        }
    }

    public String getSubject(String token) {
        if (token == null) {
            throw new RuntimeException("El token es nulo");
        }
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret); // Clave secreta y algoritmo deben coincidir
            DecodedJWT verifier = JWT.require(algorithm)
                    .withIssuer("PresupuestadorFlete") // Emisor debe coincidir
                    .build()
                    .verify(token);
            System.out.println("Token válido: " + token);
            return verifier.getSubject(); // Extrae el subject del token
        } catch (TokenExpiredException exception) {
            throw new RuntimeException("El token ha expirado", exception);
        } catch (JWTVerificationException exception) {
            throw new RuntimeException("Token inválido o no verificable", exception);
        }
    }

    private Instant generarFechaExpiracion() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}

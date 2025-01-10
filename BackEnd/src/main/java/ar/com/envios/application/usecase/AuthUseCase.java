package ar.com.envios.application.usecase;

import ar.com.envios.application.dto.LoginRequest;
import ar.com.envios.application.dto.LoginResponse;
import ar.com.envios.domain.model.Usuario;
import ar.com.envios.domain.repository.IUsuarioRepository;
import ar.com.envios.infrastructure.service.TokenService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class AuthUseCase {

    private final AuthenticationManager authenticationManager;
    private final IUsuarioRepository usuarioRepository;
    private final TokenService tokenService;

    public AuthUseCase(AuthenticationManager authenticationManager,
                       IUsuarioRepository usuarioRepository,
                       TokenService tokenService) {
        this.authenticationManager = authenticationManager;
        this.usuarioRepository = usuarioRepository;
        this.tokenService = tokenService;
    }

    public LoginResponse login(LoginRequest request) {
        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );

        // Obten UserDetails del contexto
        UserDetails userDetails = (UserDetails) auth.getPrincipal();

        // Busca el Usuario en la base de datos
        Usuario usuario = usuarioRepository.buscarPorEmail(userDetails.getUsername())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado tras autenticacion"));

        // Genera el token
        String token = tokenService.generateToken(usuario);

        return new LoginResponse(usuario.getEmail());
    }

}

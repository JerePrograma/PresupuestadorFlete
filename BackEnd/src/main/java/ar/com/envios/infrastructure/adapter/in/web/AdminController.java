package ar.com.envios.infrastructure.adapter.in.web;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/admin")
@SecurityRequirement(name = "bearer-key") // Aplica el esquema de seguridad al controlador
public class AdminController {

    @GetMapping("/superadmin-info")
    public ResponseEntity<String> getSuperAdminInfo() {
        return ResponseEntity.ok("Acceso permitido para SUPERADMIN");
    }
}

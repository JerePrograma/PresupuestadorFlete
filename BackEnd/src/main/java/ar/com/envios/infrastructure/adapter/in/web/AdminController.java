package ar.com.envios.infrastructure.adapter.in.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @GetMapping("/superadmin-info")
    public ResponseEntity<String> getSuperAdminInfo() {
        return ResponseEntity.ok("Acceso permitido para SUPERADMIN");
    }
}

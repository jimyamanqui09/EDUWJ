package pe.edu.upeu.bomerp.auth.seguridad.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upeu.bomerp.auth.seguridad.dto.AuthRequest;
import pe.edu.upeu.bomerp.auth.seguridad.dto.AuthResponse;
import pe.edu.upeu.bomerp.auth.seguridad.service.AuthService;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@Tag(name = "Seguridad - Autenticación")
public class AuthController {

    private final AuthService authService;

    @Operation(summary = "Inicia sesión del usuario")
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody AuthRequest request) {
        AuthResponse response = authService.login(request);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Verifica credenciales de usuario")
    @GetMapping("/verificar/{username}/{password}")
    public ResponseEntity<AuthResponse> verificar(@PathVariable String username, @PathVariable String password) {
        AuthResponse response = authService.verificarCredenciales(username, password);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Verifica si existe un usuario")
    @GetMapping("/exists/{username}")
    public ResponseEntity<Boolean> existe(@PathVariable String username) {
        boolean exists = authService.existsByUsername(username);
        return ResponseEntity.ok(exists);
    }

    @Operation(summary = "Verifica rol de usuario")
    @GetMapping("/rol/{username}/{rol}")
    public ResponseEntity<Boolean> tieneRol(@PathVariable String username, @PathVariable String rol) {
        boolean hasRole = authService.hasRole(username, rol);
        return ResponseEntity.ok(hasRole);
    }
}
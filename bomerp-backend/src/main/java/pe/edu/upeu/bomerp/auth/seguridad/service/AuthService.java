package pe.edu.upeu.bomerp.auth.seguridad.service;

import pe.edu.upeu.bomerp.auth.seguridad.dto.AuthRequest;
import pe.edu.upeu.bomerp.auth.seguridad.dto.AuthResponse;
import java.util.Optional;

public interface AuthService {
    AuthResponse login(AuthRequest request);
    AuthResponse verificarCredenciales(String username, String password);
    boolean existsByUsername(String username);
    boolean hasRole(String username, String rol);
}
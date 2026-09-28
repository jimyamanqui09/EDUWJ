package pe.edu.upeu.bomerp.auth.seguridad.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.upeu.bomerp.auth.seguridad.dto.AuthRequest;
import pe.edu.upeu.bomerp.auth.seguridad.dto.AuthResponse;
import pe.edu.upeu.bomerp.auth.seguridad.entity.Usuario;
import pe.edu.upeu.bomerp.auth.seguridad.repository.UsuarioRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthServiceImpl implements AuthService {

    private final UsuarioRepository usuarioRepository;

    @Override
    public AuthResponse login(AuthRequest request) {
        AuthResponse response = new AuthResponse();

        Usuario usuario = usuarioRepository.findByUsername(request.getUsername());
        if (usuario == null) {
            response.setMensaje("Usuario no encontrado");
            response.setRol("NONE");
            return response;
        }

        if (!"ACTIVO".equals(usuario.getEstado())) {
            response.setMensaje("Usuario bloqueado o inactivo");
            response.setRol("NONE");
            return response;
        }

        // Validación simple de contraseña
        if (!usuario.getPasswordHash().equals(request.getPassword())) {
            response.setMensaje("Contraseña incorrecta");
            response.setRol("NONE");
            return response;
        }

        response.setId(usuario.getId());
        response.setUsername(usuario.getUsername());
        response.setRol(usuario.getRol());
        response.setToken("jwt-token-" + usuario.getId());
        response.setMensaje("Login exitoso");

        return response;
    }

    @Override
    public AuthResponse verificarCredenciales(String username, String password) {
        AuthRequest req = new AuthRequest();
        req.setUsername(username);
        req.setPassword(password);
        return login(req);
    }

    @Override
    public boolean existsByUsername(String username) {
        return usuarioRepository.existsByUsername(username);
    }

    @Override
    public boolean hasRole(String username, String rol) {
        Usuario usuario = usuarioRepository.findByUsername(username);
        if (usuario == null) {
            return false;
        }
        return usuario.getRol().equals(rol);
    }
}
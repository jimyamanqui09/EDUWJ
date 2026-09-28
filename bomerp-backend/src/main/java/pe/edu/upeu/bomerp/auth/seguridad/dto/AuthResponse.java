package pe.edu.upeu.bomerp.auth.seguridad.dto;

import lombok.Data;

@Data
public class AuthResponse {
    private Long id;
    private String username;
    private String rol;
    private String token;
    private String mensaje;
}
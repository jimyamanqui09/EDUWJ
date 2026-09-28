package pe.edu.upeu.bomerp.auth.seguridad.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class AuthRequest {
    @NotNull(message = "El username es obligatorio")
    @Size(max = 50, message = "El username no puede exceder 50 caracteres")
    private String username;

    @NotNull(message = "La contraseña es obligatoria")
    @Size(max = 100, message = "La contraseña no puede exceder 100 caracteres")
    private String password;
}
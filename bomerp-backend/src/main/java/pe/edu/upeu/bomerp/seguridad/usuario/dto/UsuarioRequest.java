package pe.edu.upeu.bomerp.seguridad.usuario.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioRequest {

    @NotBlank
    @Size(min = 8, max = 8)
    private String dni;

    @NotBlank
    @Size(max = 120)
    private String nombre;

    @NotBlank
    @Email
    @Size(max = 120)
    private String email;

    @NotBlank
    @Size(min = 6, max = 200)
    private String password;

    @NotBlank
    private String tipoUsuario;
}

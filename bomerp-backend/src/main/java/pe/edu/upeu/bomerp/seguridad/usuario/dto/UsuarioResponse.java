package pe.edu.upeu.bomerp.seguridad.usuario.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioResponse {
    private Long id;
    private String dni;
    private String nombre;
    private String email;
    private String tipoUsuario;
    private Boolean activo;
}

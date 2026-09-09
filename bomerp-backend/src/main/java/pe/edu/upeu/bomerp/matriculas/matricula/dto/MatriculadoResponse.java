package pe.edu.upeu.bomerp.matriculas.matricula.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MatriculadoResponse {
    private Long usuarioId;
    private String dni;
    private String nombre;
    private String email;
}

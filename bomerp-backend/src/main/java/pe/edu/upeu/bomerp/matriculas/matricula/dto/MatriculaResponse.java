package pe.edu.upeu.bomerp.matriculas.matricula.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MatriculaResponse {
    private Long id;
    private Long usuarioId;
    private String usuarioNombre;
    private Long cursoId;
    private String cursoNombre;
    private LocalDateTime fechaMatricula;
    private String estado;
}

package pe.edu.upeu.bomerp.matriculas.matricula.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class MatriculaRequest {

    @NotNull
    private Long usuarioId;

    @NotEmpty
    private List<Long> cursosIds;
}

package pe.edu.upeu.bomerp.horarios.horario.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HorarioRequest {

    @NotNull
    private Long cursoId;

    @NotBlank
    private String diaSemana;

    @NotBlank
    private String horaInicio;

    @NotBlank
    private String horaFin;

    @NotNull
    private Long aulaId;
}

package pe.edu.upeu.bomerp.horarios.horario.dto;

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
public class HorarioResponse {
    private Long id;
    private String diaSemana;
    private String horaInicio;
    private String horaFin;
    private Long cursoId;
    private String cursoNombre;
    private Long aulaId;
    private String aulaNombre;
}

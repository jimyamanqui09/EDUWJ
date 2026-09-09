package pe.edu.upeu.bomerp.horarios.horario.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class HorarioEstudianteResponse {
    private String cursoNombre;
    private String diaSemana;
    private String horaInicio;
    private String horaFin;
    private String aulaNombre;
}

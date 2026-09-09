package pe.edu.upeu.bomerp.asistencia.clase.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClaseResponse {
    private Long id;
    private Long horarioId;
    private LocalDate fecha;
    private String tema;
}

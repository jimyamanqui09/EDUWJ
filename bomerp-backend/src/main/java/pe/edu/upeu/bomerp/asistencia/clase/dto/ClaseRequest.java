package pe.edu.upeu.bomerp.asistencia.clase.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
public class ClaseRequest {

    @NotNull
    private Long horarioId;

    @NotNull
    private LocalDate fecha;

    @NotBlank
    private String tema;
}

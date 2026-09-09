package pe.edu.upeu.bomerp.tareas.tarea.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class TareaRequest {

    @NotNull
    private Long cursoId;

    @NotBlank
    @Size(max = 150)
    private String titulo;

    @Size(max = 500)
    private String descripcion;

    @NotNull
    private LocalDateTime fechaEntrega;

    @NotNull
    @Positive
    private BigDecimal peso;
}

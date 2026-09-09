package pe.edu.upeu.bomerp.tareas.tarea.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TareaResponse {
    private Long id;
    private Long cursoId;
    private String cursoNombre;
    private String titulo;
    private String descripcion;
    private LocalDateTime fechaEntrega;
    private BigDecimal peso;
}

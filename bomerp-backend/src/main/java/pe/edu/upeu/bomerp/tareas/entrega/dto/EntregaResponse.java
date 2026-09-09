package pe.edu.upeu.bomerp.tareas.entrega.dto;

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
public class EntregaResponse {
    private Long id;
    private Long tareaId;
    private String tareaTitulo;
    private Long usuarioId;
    private String usuarioNombre;
    private LocalDateTime fechaEntrega;
    private String urlTrabajo;
    private BigDecimal calificacion;
    private String observaciones;
}

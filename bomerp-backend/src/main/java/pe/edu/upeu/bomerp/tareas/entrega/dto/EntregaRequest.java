package pe.edu.upeu.bomerp.tareas.entrega.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EntregaRequest {

    @NotNull
    private Long usuarioId;

    @NotBlank
    @Size(max = 300)
    private String urlTrabajo;

    @Size(max = 300)
    private String observaciones;
}

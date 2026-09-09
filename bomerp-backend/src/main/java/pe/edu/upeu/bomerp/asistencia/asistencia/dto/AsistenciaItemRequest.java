package pe.edu.upeu.bomerp.asistencia.asistencia.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AsistenciaItemRequest {

    @NotNull
    private Long usuarioId;

    @NotBlank
    private String estado;
}

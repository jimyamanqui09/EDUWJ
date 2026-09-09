package pe.edu.upeu.bomerp.academico.aula.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AulaRequest {
    @NotBlank
    private String nombre;

    @PositiveOrZero
    private Integer capacidad;

    private String edificio;
}

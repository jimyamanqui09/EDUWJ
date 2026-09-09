package pe.edu.upeu.bomerp.academico.carrera.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CarreraRequest {

    @NotBlank
    @Size(max = 10)
    private String codigo;

    @NotBlank
    @Size(max = 120)
    private String nombre;

    @PositiveOrZero
    private Integer totalCreditos;
}

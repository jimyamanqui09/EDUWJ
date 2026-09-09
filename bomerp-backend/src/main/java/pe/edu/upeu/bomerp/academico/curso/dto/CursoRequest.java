package pe.edu.upeu.bomerp.academico.curso.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CursoRequest {

    @NotNull
    private Long cicloId;

    @NotNull
    private Long carreraId;

    @NotBlank
    @Size(max = 120)
    private String nombre;

    @NotBlank
    @Size(max = 10)
    private String codigo;

    @NotNull
    @Positive
    private Integer creditos;

    @NotNull
    @Positive
    private Integer vacantes;
}

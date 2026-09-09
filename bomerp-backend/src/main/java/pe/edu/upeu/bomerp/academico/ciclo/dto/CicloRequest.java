package pe.edu.upeu.bomerp.academico.ciclo.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CicloRequest {
    @NotNull
    private Long carreraId;

    @NotNull
    @Positive
    private Integer numero;

    @NotNull
    private Integer anio;

    @NotNull
    @Positive
    private Integer semestre;
}

package pe.edu.upeu.bomerp.avisos.aviso.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AvisoRequest {

    @NotBlank
    @Size(max = 150)
    private String titulo;

    @NotBlank
    @Size(max = 1000)
    private String contenido;

    @NotNull
    private Long autorId;
}

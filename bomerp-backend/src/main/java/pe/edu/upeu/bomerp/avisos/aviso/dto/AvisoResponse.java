package pe.edu.upeu.bomerp.avisos.aviso.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AvisoResponse {
    private Long id;
    private String titulo;
    private String contenido;
    private LocalDateTime fechaPublicacion;
    private String autorNombre;
}

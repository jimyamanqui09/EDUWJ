package pe.edu.upeu.bomerp.academico.aula.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AulaResponse {
    private Long id;
    private String nombre;
    private Integer capacidad;
    private String edificio;
}

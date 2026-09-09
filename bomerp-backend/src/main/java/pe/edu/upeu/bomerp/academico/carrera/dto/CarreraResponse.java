package pe.edu.upeu.bomerp.academico.carrera.dto;

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
public class CarreraResponse {
    private Long id;
    private String codigo;
    private String nombre;
    private Integer totalCreditos;
}

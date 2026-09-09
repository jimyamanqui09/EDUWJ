package pe.edu.upeu.bomerp.academico.ciclo.dto;

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
public class CicloResponse {
    private Long id;
    private Long carreraId;
    private Integer numero;
    private Integer anio;
    private Integer semestre;
}

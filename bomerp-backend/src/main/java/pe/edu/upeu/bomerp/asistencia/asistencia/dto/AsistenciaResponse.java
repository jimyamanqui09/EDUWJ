package pe.edu.upeu.bomerp.asistencia.asistencia.dto;

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
public class AsistenciaResponse {
    private Long id;
    private Long claseId;
    private Long usuarioId;
    private String usuarioNombre;
    private String estado;
}

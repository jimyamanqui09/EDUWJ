package pe.edu.upeu.bomerp.asistencia.asistencia.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class AsistenciaBulkRequest {

    @NotEmpty
    private List<AsistenciaItemRequest> asistencias;
}

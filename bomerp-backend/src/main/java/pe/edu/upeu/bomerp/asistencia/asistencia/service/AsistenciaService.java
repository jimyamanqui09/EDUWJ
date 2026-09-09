package pe.edu.upeu.bomerp.asistencia.asistencia.service;

import pe.edu.upeu.bomerp.asistencia.asistencia.dto.AsistenciaBulkRequest;
import pe.edu.upeu.bomerp.asistencia.asistencia.dto.AsistenciaResponse;
import java.util.List;

public interface AsistenciaService {
    List<AsistenciaResponse> crearBulk(Long claseId, AsistenciaBulkRequest request);
    List<AsistenciaResponse> listarPorClase(Long claseId);
}

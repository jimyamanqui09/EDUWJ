package pe.edu.upeu.bomerp.reporte.service;

import java.util.List;
import java.util.Map;

public interface ReporteService {
    Map<String, Object> generarReporteNotasPorAlumno(Long alumnoId);
    Map<String, Object> generarReporteAsistenciaPorGrado(String grado);
    List<String> listarGrados();
    List<String> listarSecciones(String grado);
}
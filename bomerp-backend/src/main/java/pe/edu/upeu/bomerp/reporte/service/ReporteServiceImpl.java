package pe.edu.upeu.bomerp.reporte.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReporteServiceImpl implements ReporteService {

    @Override
    public Map<String, Object> generarReporteNotasPorAlumno(Long alumnoId) {
        Map<String, Object> reporte = new HashMap<>();
        reporte.put("alumnoId", alumnoId);
        reporte.put("tipo", "notas");
        reporte.put("mensaje", "Reporte de notas del alumno ID: " + alumnoId);
        return reporte;
    }

    @Override
    public Map<String, Object> generarReporteAsistenciaPorGrado(String grado) {
        Map<String, Object> reporte = new HashMap<>();
        reporte.put("grado", grado);
        reporte.put("tipo", "asistencia");
        reporte.put("mensaje", "Reporte de asistencia del grado: " + grado);
        return reporte;
    }

    @Override
    public List<String> listarGrados() {
        return List.of("Primaria", "Secundaria", "Bachillerato");
    }

    @Override
    public List<String> listarSecciones(String grado) {
        return List.of("A", "B", "C");
    }
}
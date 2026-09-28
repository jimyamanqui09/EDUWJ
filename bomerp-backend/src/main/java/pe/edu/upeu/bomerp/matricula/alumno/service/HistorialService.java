package pe.edu.upeu.bomerp.matricula.alumno.service;

import pe.edu.upeu.bomerp.matricula.alumno.dto.AlumnoResponse;
import java.util.List;

public interface HistorialService {
    AlumnoResponse obtenerHistorialAlumno(Long alumnoId);
    List<String> listarGrados();
    List<String> listarSecciones(String grado);
}
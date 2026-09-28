package pe.edu.upeu.bomerp.matricula.alumno.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.upeu.bomerp.matricula.alumno.dto.AlumnoResponse;
import pe.edu.upeu.bomerp.matricula.alumno.entity.Alumno;
import pe.edu.upeu.bomerp.matricula.alumno.repository.AlumnoRepository;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class HistorialServiceImpl implements HistorialService {

    private final AlumnoRepository alumnoRepository;

    @Override
    @Transactional(readOnly = true)
    public AlumnoResponse obtenerHistorialAlumno(Long alumnoId) {
        Alumno alumno = alumnoRepository.findById(alumnoId)
                .orElseThrow(() -> new RuntimeException("Alumno no encontrado con id: " + alumnoId));
        return null; // Simplificado - en producción mapearía a AlumnoResponse
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
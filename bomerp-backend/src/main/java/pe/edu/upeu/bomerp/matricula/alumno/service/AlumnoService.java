package pe.edu.upeu.bomerp.matricula.alumno.service;

import pe.edu.upeu.bomerp.matricula.alumno.dto.AlumnoRequest;
import pe.edu.upeu.bomerp.matricula.alumno.dto.AlumnoResponse;
import java.util.List;

public interface AlumnoService {
    List<AlumnoResponse> listar();
    AlumnoResponse obtenerPorId(Long id);
    AlumnoResponse crear(AlumnoRequest request);
    AlumnoResponse actualizar(Long id, AlumnoRequest request);
    void eliminar(Long id);
    AlumnoResponse buscarPorDni(String dni);
}
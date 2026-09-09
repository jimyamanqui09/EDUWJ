package pe.edu.upeu.bomerp.matriculas.matricula.service;

import pe.edu.upeu.bomerp.matriculas.matricula.dto.MatriculaRequest;
import pe.edu.upeu.bomerp.matriculas.matricula.dto.MatriculaResponse;
import pe.edu.upeu.bomerp.matriculas.matricula.dto.MatriculadoResponse;
import java.util.List;

public interface MatriculaService {
    List<MatriculaResponse> listar();
    MatriculaResponse crear(MatriculaRequest request);
    List<MatriculadoResponse> listarMatriculados(Long cursoId);
    List<MatriculaResponse> listarCursosPorEstudiante(Long usuarioId);
}

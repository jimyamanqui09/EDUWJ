package pe.edu.upeu.bomerp.horarios.horario.service;

import pe.edu.upeu.bomerp.horarios.horario.dto.HorarioEstudianteResponse;
import pe.edu.upeu.bomerp.horarios.horario.dto.HorarioRequest;
import pe.edu.upeu.bomerp.horarios.horario.dto.HorarioResponse;
import java.util.List;

public interface HorarioService {
    List<HorarioResponse> listarPorCurso(Long cursoId);
    HorarioResponse obtener(Long id);
    HorarioResponse crear(HorarioRequest request);
    void eliminar(Long id);
    List<HorarioEstudianteResponse> horarioEstudiante(Long usuarioId, List<Long> cursoIds);
}

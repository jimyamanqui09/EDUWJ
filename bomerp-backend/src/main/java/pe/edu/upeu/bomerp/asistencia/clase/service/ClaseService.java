package pe.edu.upeu.bomerp.asistencia.clase.service;

import pe.edu.upeu.bomerp.asistencia.clase.dto.ClaseRequest;
import pe.edu.upeu.bomerp.asistencia.clase.dto.ClaseResponse;
import java.util.List;

public interface ClaseService {
    List<ClaseResponse> listarPorHorario(Long horarioId);
    ClaseResponse crear(ClaseRequest request);
}

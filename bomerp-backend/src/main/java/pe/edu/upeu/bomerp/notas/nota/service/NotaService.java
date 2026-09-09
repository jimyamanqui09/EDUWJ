package pe.edu.upeu.bomerp.notas.nota.service;

import pe.edu.upeu.bomerp.notas.nota.dto.NotaEstudianteResponse;

public interface NotaService {
    NotaEstudianteResponse notasEstudiante(Long usuarioId);
}

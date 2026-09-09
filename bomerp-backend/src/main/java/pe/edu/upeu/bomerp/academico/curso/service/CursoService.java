package pe.edu.upeu.bomerp.academico.curso.service;

import pe.edu.upeu.bomerp.academico.curso.dto.CursoRequest;
import pe.edu.upeu.bomerp.academico.curso.dto.CursoResponse;
import pe.edu.upeu.bomerp.academico.malla.dto.MallaCurricularResponse;
import java.util.List;

public interface CursoService {
    List<CursoResponse> listar();
    CursoResponse obtener(Long id);
    CursoResponse crear(CursoRequest request);
    CursoResponse actualizar(Long id, CursoRequest request);
    void eliminar(Long id);
    List<CursoResponse> listarPorCarrera(Long carreraId);
    MallaCurricularResponse malla(Long carreraId);
}

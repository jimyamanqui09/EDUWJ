package pe.edu.upeu.bomerp.academico.ciclo.service;

import pe.edu.upeu.bomerp.academico.ciclo.dto.CicloRequest;
import pe.edu.upeu.bomerp.academico.ciclo.dto.CicloResponse;
import java.util.List;

public interface CicloService {
    List<CicloResponse> listar();
    CicloResponse obtener(Long id);
    CicloResponse crear(CicloRequest request);
    void eliminar(Long id);
    List<CicloResponse> listarPorCarrera(Long carreraId);
}

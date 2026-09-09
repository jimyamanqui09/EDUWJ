package pe.edu.upeu.bomerp.academico.aula.service;

import pe.edu.upeu.bomerp.academico.aula.dto.AulaRequest;
import pe.edu.upeu.bomerp.academico.aula.dto.AulaResponse;
import java.util.List;

public interface AulaService {
    List<AulaResponse> listar();
    AulaResponse obtener(Long id);
    AulaResponse crear(AulaRequest request);
    void eliminar(Long id);
}

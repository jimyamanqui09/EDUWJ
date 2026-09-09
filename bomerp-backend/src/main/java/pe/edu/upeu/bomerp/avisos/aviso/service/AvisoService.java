package pe.edu.upeu.bomerp.avisos.aviso.service;

import pe.edu.upeu.bomerp.avisos.aviso.dto.AvisoRequest;
import pe.edu.upeu.bomerp.avisos.aviso.dto.AvisoResponse;
import java.util.List;

public interface AvisoService {
    List<AvisoResponse> listar();
    AvisoResponse crear(AvisoRequest request);
}

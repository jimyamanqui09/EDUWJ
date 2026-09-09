package pe.edu.upeu.bomerp.tareas.tarea.service;

import pe.edu.upeu.bomerp.tareas.tarea.dto.TareaRequest;
import pe.edu.upeu.bomerp.tareas.tarea.dto.TareaResponse;
import java.util.List;

public interface TareaService {
    List<TareaResponse> listar();
    TareaResponse obtener(Long id);
    TareaResponse crear(TareaRequest request);
    void eliminar(Long id);
}

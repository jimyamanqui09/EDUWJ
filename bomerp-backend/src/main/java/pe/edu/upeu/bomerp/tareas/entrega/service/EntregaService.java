package pe.edu.upeu.bomerp.tareas.entrega.service;

import pe.edu.upeu.bomerp.tareas.entrega.dto.EntregaRequest;
import pe.edu.upeu.bomerp.tareas.entrega.dto.EntregaResponse;
import java.util.List;

public interface EntregaService {
    List<EntregaResponse> listar();
    EntregaResponse crear(Long tareaId, EntregaRequest request);
    List<EntregaResponse> listarPorTarea(Long tareaId);
    List<EntregaResponse> listarPorUsuario(Long usuarioId);
}

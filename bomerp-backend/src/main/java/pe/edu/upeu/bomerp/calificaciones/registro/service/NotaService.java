package pe.edu.upeu.bomerp.calificaciones.registro.service;

import pe.edu.upeu.bomerp.calificaciones.registro.dto.NotaRequest;
import pe.edu.upeu.bomerp.calificaciones.registro.dto.NotaResponse;
import java.util.List;

public interface NotaService {
    List<NotaResponse> listar();
    NotaResponse obtener(Long id);
    NotaResponse registrar(NotaRequest request);
    NotaResponse actualizar(Long id, NotaRequest request);
    void eliminar(Long id);
    List<NotaResponse> listarPorAlumno(Long alumnoId);
    List<NotaResponse> listarPorEstado(String estado);
    NotaResponse cerrarActa(Long id);
}
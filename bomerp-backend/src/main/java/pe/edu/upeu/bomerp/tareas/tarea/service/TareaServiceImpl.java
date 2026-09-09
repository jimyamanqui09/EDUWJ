package pe.edu.upeu.bomerp.tareas.tarea.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.upeu.bomerp.academico.curso.dto.CursoResponse;
import pe.edu.upeu.bomerp.academico.curso.service.CursoService;
import pe.edu.upeu.bomerp.exception.ResourceNotFoundException;
import pe.edu.upeu.bomerp.tareas.tarea.dto.TareaRequest;
import pe.edu.upeu.bomerp.tareas.tarea.dto.TareaResponse;
import pe.edu.upeu.bomerp.tareas.tarea.entity.Tarea;
import pe.edu.upeu.bomerp.tareas.tarea.repository.TareaRepository;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TareaServiceImpl implements TareaService {
    private final TareaRepository tareaRepository;
    private final CursoService cursoService;

    @Override
    @Transactional(readOnly = true)
    public List<TareaResponse> listar() {
        return tareaRepository.findAll().stream().map(this::toResponse).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public TareaResponse obtener(Long id) {
        return toResponse(buscarOFallar(id));
    }

    @Override
    @Transactional
    public TareaResponse crear(TareaRequest request) {
        CursoResponse curso = cursoService.obtener(request.getCursoId());

        Tarea tarea = new Tarea();
        tarea.setCursoId(curso.getId());
        tarea.setTitulo(request.getTitulo());
        tarea.setDescripcion(request.getDescripcion());
        tarea.setFechaEntrega(request.getFechaEntrega());
        tarea.setPeso(request.getPeso());
        return toResponse(tareaRepository.save(tarea));
    }

    @Override
    @Transactional
    public void eliminar(Long id) {
        tareaRepository.delete(buscarOFallar(id));
    }

    private Tarea buscarOFallar(Long id) {
        return tareaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tarea no encontrada: " + id));
    }

    private TareaResponse toResponse(Tarea t) {
        CursoResponse curso = cursoService.obtener(t.getCursoId());
        return TareaResponse.builder()
                .id(t.getId())
                .cursoId(t.getCursoId())
                .cursoNombre(curso.getNombre())
                .titulo(t.getTitulo())
                .descripcion(t.getDescripcion())
                .fechaEntrega(t.getFechaEntrega())
                .peso(t.getPeso())
                .build();
    }
}

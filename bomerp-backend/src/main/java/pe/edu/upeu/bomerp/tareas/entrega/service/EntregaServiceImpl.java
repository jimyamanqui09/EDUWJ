package pe.edu.upeu.bomerp.tareas.entrega.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.upeu.bomerp.exception.ResourceNotFoundException;
import pe.edu.upeu.bomerp.exception.BusinessException;
import pe.edu.upeu.bomerp.seguridad.usuario.dto.UsuarioResponse;
import pe.edu.upeu.bomerp.seguridad.usuario.service.UsuarioService;
import pe.edu.upeu.bomerp.tareas.entrega.dto.EntregaRequest;
import pe.edu.upeu.bomerp.tareas.entrega.dto.EntregaResponse;
import pe.edu.upeu.bomerp.tareas.entrega.entity.Entrega;
import pe.edu.upeu.bomerp.tareas.entrega.repository.EntregaRepository;
import pe.edu.upeu.bomerp.tareas.tarea.dto.TareaResponse;
import pe.edu.upeu.bomerp.tareas.tarea.service.TareaService;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EntregaServiceImpl implements EntregaService {
    private final EntregaRepository entregaRepository;
    private final TareaService tareaService;
    private final UsuarioService usuarioService;

    @Override
    @Transactional(readOnly = true)
    public List<EntregaResponse> listar() {
        return entregaRepository.findAll().stream().map(this::toResponse).toList();
    }

    @Override
    @Transactional
    public EntregaResponse crear(Long tareaId, EntregaRequest request) {
        TareaResponse tarea = tareaService.obtener(tareaId);

        if (LocalDateTime.now().isAfter(tarea.getFechaEntrega())) {
            throw new BusinessException("La fecha de entrega de la tarea ya paso");
        }

        UsuarioResponse usuario = usuarioService.obtener(request.getUsuarioId());

        if (entregaRepository.findByTareaIdAndUsuarioId(tareaId, usuario.getId()).isPresent()) {
            throw new BusinessException("El usuario ya entrego esta tarea");
        }

        Entrega entrega = new Entrega();
        entrega.setTareaId(tarea.getId());
        entrega.setUsuarioId(usuario.getId());
        entrega.setFechaEntrega(LocalDateTime.now());
        entrega.setUrlTrabajo(request.getUrlTrabajo());
        entrega.setObservaciones(request.getObservaciones());
        return toResponse(entregaRepository.save(entrega));
    }

    @Override
    @Transactional(readOnly = true)
    public List<EntregaResponse> listarPorTarea(Long tareaId) {
        return entregaRepository.findByTareaId(tareaId).stream().map(this::toResponse).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<EntregaResponse> listarPorUsuario(Long usuarioId) {
        return entregaRepository.findByUsuarioId(usuarioId).stream().map(this::toResponse).toList();
    }

    private EntregaResponse toResponse(Entrega e) {
        TareaResponse tarea = tareaService.obtener(e.getTareaId());
        UsuarioResponse usuario = usuarioService.obtener(e.getUsuarioId());
        return EntregaResponse.builder()
                .id(e.getId())
                .tareaId(e.getTareaId())
                .tareaTitulo(tarea.getTitulo())
                .usuarioId(e.getUsuarioId())
                .usuarioNombre(usuario.getNombre())
                .fechaEntrega(e.getFechaEntrega())
                .urlTrabajo(e.getUrlTrabajo())
                .calificacion(e.getCalificacion())
                .observaciones(e.getObservaciones())
                .build();
    }
}

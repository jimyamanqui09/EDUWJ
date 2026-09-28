package pe.edu.upeu.bomerp.calificaciones.registro.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.upeu.bomerp.calificaciones.registro.dto.NotaRequest;
import pe.edu.upeu.bomerp.calificaciones.registro.dto.NotaResponse;
import pe.edu.upeu.bomerp.calificaciones.registro.entity.Nota;
import pe.edu.upeu.bomerp.calificaciones.registro.mapper.NotaMapper;
import pe.edu.upeu.bomerp.calificaciones.registro.repository.NotaRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class NotaServiceImpl implements NotaService {

    private final NotaRepository notaRepository;
    private final NotaMapper notaMapper;

    @Override
    @Transactional(readOnly = true)
    public List<NotaResponse> listar() {
        return notaRepository.findAll().stream()
                .map(notaMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public NotaResponse obtener(Long id) {
        Nota nota = notaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Nota no encontrada con id: " + id));
        return notaMapper.toResponse(nota);
    }

    @Override
    @Transactional
    public NotaResponse registrar(NotaRequest request) {
        if (request.getValor() < 0 || request.getValor() > 20) {
            throw new RuntimeException("La nota debe estar en el rango 0-20");
        }
        if (request.getPonderacion() < 0 || request.getPonderacion() > 100) {
            throw new RuntimeException("La ponderación debe estar en el rango 0-100");
        }

        Nota nota = notaMapper.toEntity(request);
        nota = notaRepository.save(nota);
        return notaMapper.toResponse(nota);
    }

    @Override
    @Transactional
    public NotaResponse actualizar(Long id, NotaRequest request) {
        Nota nota = notaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Nota no encontrada con id: " + id));

        nota.setAlumnoId(request.getAlumnoId());
        nota.setEvaluacion(request.getEvaluacion());
        nota.setValor(request.getValor());
        nota.setPonderacion(request.getPonderacion());
        nota.setFechaRegistro(request.getFechaRegistro());
        nota.setEstado(request.getEstado());

        nota = notaRepository.save(nota);
        return notaMapper.toResponse(nota);
    }

    @Override
    @Transactional
    public void eliminar(Long id) {
        if (!notaRepository.existsById(id)) {
            throw new RuntimeException("Nota no encontrada con id: " + id);
        }
        notaRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<NotaResponse> listarPorAlumno(Long alumnoId) {
        return notaRepository.findByAlumnoId(alumnoId).stream()
                .map(notaMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<NotaResponse> listarPorEstado(String estado) {
        return notaRepository.findByEstado(estado).stream()
                .map(notaMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public NotaResponse cerrarActa(Long id) {
        Nota nota = notaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Nota no encontrada con id: " + id));

        if (!"REGISTRADA".equals(nota.getEstado())) {
            throw new RuntimeException("No se puede cerrar una nota que no está en estado REGISTRADO");
        }

        nota.setEstado("CERRADA");
        nota = notaRepository.save(nota);
        return notaMapper.toResponse(nota);
    }
}
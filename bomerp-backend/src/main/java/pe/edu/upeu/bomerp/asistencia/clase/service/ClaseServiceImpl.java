package pe.edu.upeu.bomerp.asistencia.clase.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.upeu.bomerp.horarios.horario.service.HorarioService;
import pe.edu.upeu.bomerp.asistencia.clase.dto.ClaseRequest;
import pe.edu.upeu.bomerp.asistencia.clase.dto.ClaseResponse;
import pe.edu.upeu.bomerp.asistencia.clase.entity.Clase;
import pe.edu.upeu.bomerp.asistencia.clase.repository.ClaseRepository;
import pe.edu.upeu.bomerp.exception.ResourceNotFoundException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ClaseServiceImpl implements ClaseService {
    private final ClaseRepository claseRepository;
    private final HorarioService horarioService;

    @Override
    @Transactional(readOnly = true)
    public List<ClaseResponse> listarPorHorario(Long horarioId) {
        return claseRepository.findByHorarioId(horarioId).stream().map(this::toResponse).toList();
    }

    @Override
    @Transactional
    public ClaseResponse crear(ClaseRequest request) {
        horarioService.obtener(request.getHorarioId());

        Clase clase = new Clase();
        clase.setHorarioId(request.getHorarioId());
        clase.setFecha(request.getFecha());
        clase.setTema(request.getTema());
        return toResponse(claseRepository.save(clase));
    }

    private ClaseResponse toResponse(Clase c) {
        return ClaseResponse.builder()
                .id(c.getId())
                .horarioId(c.getHorarioId())
                .fecha(c.getFecha())
                .tema(c.getTema())
                .build();
    }
}

package pe.edu.upeu.bomerp.horarios.horario.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.upeu.bomerp.academico.aula.dto.AulaResponse;
import pe.edu.upeu.bomerp.academico.aula.service.AulaService;
import pe.edu.upeu.bomerp.academico.curso.dto.CursoResponse;
import pe.edu.upeu.bomerp.academico.curso.service.CursoService;
import pe.edu.upeu.bomerp.horarios.horario.dto.HorarioEstudianteResponse;
import pe.edu.upeu.bomerp.horarios.horario.dto.HorarioRequest;
import pe.edu.upeu.bomerp.horarios.horario.dto.HorarioResponse;
import pe.edu.upeu.bomerp.horarios.horario.entity.Horario;
import pe.edu.upeu.bomerp.horarios.horario.repository.HorarioRepository;
import pe.edu.upeu.bomerp.exception.ResourceNotFoundException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class HorarioServiceImpl implements HorarioService {
    private final HorarioRepository horarioRepository;
    private final CursoService cursoService;
    private final AulaService aulaService;

    @Override
    @Transactional(readOnly = true)
    public HorarioResponse obtener(Long id) {
        Horario horario = horarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Horario no encontrado: " + id));
        return toResponse(horario);
    }

    @Override
    @Transactional(readOnly = true)
    public List<HorarioResponse> listarPorCurso(Long cursoId) {
        return horarioRepository.findByCursoId(cursoId).stream().map(this::toResponse).toList();
    }

    @Override
    @Transactional
    public HorarioResponse crear(HorarioRequest request) {
        CursoResponse curso = cursoService.obtener(request.getCursoId());
        AulaResponse aula = aulaService.obtener(request.getAulaId());

        Horario horario = new Horario();
        horario.setCursoId(curso.getId());
        horario.setDiaSemana(request.getDiaSemana());
        horario.setHoraInicio(request.getHoraInicio());
        horario.setHoraFin(request.getHoraFin());
        horario.setAulaId(aula.getId());
        return toResponse(horarioRepository.save(horario));
    }

    @Override
    @Transactional
    public void eliminar(Long id) {
        horarioRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<HorarioEstudianteResponse> horarioEstudiante(Long usuarioId, List<Long> cursoIds) {
        List<Horario> horarios = horarioRepository.findByCursoIdIn(cursoIds);
        return horarios.stream()
                .map(h -> {
                    CursoResponse curso = cursoService.obtener(h.getCursoId());
                    AulaResponse aula = aulaService.obtener(h.getAulaId());
                    return new HorarioEstudianteResponse(
                            curso.getNombre(),
                            h.getDiaSemana(),
                            h.getHoraInicio(),
                            h.getHoraFin(),
                            aula.getNombre());
                })
                .toList();
    }

    private HorarioResponse toResponse(Horario h) {
        CursoResponse curso = cursoService.obtener(h.getCursoId());
        AulaResponse aula = aulaService.obtener(h.getAulaId());
        return HorarioResponse.builder()
                .id(h.getId())
                .diaSemana(h.getDiaSemana())
                .horaInicio(h.getHoraInicio())
                .horaFin(h.getHoraFin())
                .cursoId(h.getCursoId())
                .cursoNombre(curso.getNombre())
                .aulaId(h.getAulaId())
                .aulaNombre(aula.getNombre())
                .build();
    }
}

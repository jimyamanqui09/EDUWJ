package pe.edu.upeu.bomerp.matriculas.matricula.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.upeu.bomerp.academico.curso.dto.CursoResponse;
import pe.edu.upeu.bomerp.academico.curso.service.CursoService;
import pe.edu.upeu.bomerp.exception.ResourceNotFoundException;
import pe.edu.upeu.bomerp.exception.BusinessException;
import pe.edu.upeu.bomerp.matriculas.matricula.dto.MatriculaRequest;
import pe.edu.upeu.bomerp.matriculas.matricula.dto.MatriculaResponse;
import pe.edu.upeu.bomerp.matriculas.matricula.dto.MatriculadoResponse;
import pe.edu.upeu.bomerp.matriculas.matricula.entity.Matricula;
import pe.edu.upeu.bomerp.matriculas.matricula.repository.MatriculaRepository;
import pe.edu.upeu.bomerp.seguridad.usuario.dto.UsuarioResponse;
import pe.edu.upeu.bomerp.seguridad.usuario.service.UsuarioService;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MatriculaServiceImpl implements MatriculaService {
    private final MatriculaRepository matriculaRepository;
    private final UsuarioService usuarioService;
    private final CursoService cursoService;

    @Override
    @Transactional(readOnly = true)
    public List<MatriculaResponse> listar() {
        return matriculaRepository.findAll().stream().map(this::toResponse).toList();
    }

    @Override
    @Transactional
    public MatriculaResponse crear(MatriculaRequest request) {
        UsuarioResponse usuario = usuarioService.obtener(request.getUsuarioId());

        List<Matricula> matriculas = new ArrayList<>();
        for (Long cursoId : request.getCursosIds()) {
            CursoResponse curso = cursoService.obtener(cursoId);

            if (matriculaRepository.existsByUsuarioIdAndCursoId(usuario.getId(), curso.getId())) {
                throw new BusinessException("El usuario ya esta matriculado en el curso: " + curso.getNombre());
            }

            if (curso.getVacantes() <= 0) {
                throw new BusinessException("No hay vacantes disponibles para el curso: " + curso.getNombre());
            }

            Matricula matricula = new Matricula();
            matricula.setUsuarioId(usuario.getId());
            matricula.setCursoId(curso.getId());
            matricula.setFechaMatricula(LocalDateTime.now());
            matricula.setEstado(Matricula.EstadoMatricula.ACTIVA);
            matriculas.add(matriculaRepository.save(matricula));
        }

        return toResponse(matriculas.get(0));
    }

    @Override
    @Transactional(readOnly = true)
    public List<MatriculadoResponse> listarMatriculados(Long cursoId) {
        return matriculaRepository.findByCursoIdAndEstado(cursoId, "ACTIVA").stream()
                .map(m -> {
                    UsuarioResponse u = usuarioService.obtener(m.getUsuarioId());
                    return new MatriculadoResponse(u.getId(), u.getDni(), u.getNombre(), u.getEmail());
                })
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<MatriculaResponse> listarCursosPorEstudiante(Long usuarioId) {
        return matriculaRepository.findByUsuarioIdAndEstado(usuarioId, "ACTIVA").stream().map(this::toResponse).toList();
    }

    private MatriculaResponse toResponse(Matricula m) {
        return MatriculaResponse.builder()
                .id(m.getId())
                .usuarioId(m.getUsuarioId())
                .cursoId(m.getCursoId())
                .fechaMatricula(m.getFechaMatricula())
                .estado(m.getEstado().name())
                .build();
    }
}

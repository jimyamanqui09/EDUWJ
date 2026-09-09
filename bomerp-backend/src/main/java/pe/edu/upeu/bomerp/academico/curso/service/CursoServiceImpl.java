package pe.edu.upeu.bomerp.academico.curso.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.upeu.bomerp.academico.ciclo.dto.CicloResponse;
import pe.edu.upeu.bomerp.academico.ciclo.service.CicloService;
import pe.edu.upeu.bomerp.academico.curso.dto.CursoRequest;
import pe.edu.upeu.bomerp.academico.curso.dto.CursoResponse;
import pe.edu.upeu.bomerp.academico.curso.entity.Curso;
import pe.edu.upeu.bomerp.academico.curso.repository.CursoRepository;
import pe.edu.upeu.bomerp.academico.malla.dto.MallaCurricularResponse;
import pe.edu.upeu.bomerp.academico.carrera.dto.CarreraResponse;
import pe.edu.upeu.bomerp.academico.carrera.service.CarreraService;
import pe.edu.upeu.bomerp.exception.ResourceNotFoundException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CursoServiceImpl implements CursoService {
    private final CursoRepository cursoRepository;
    private final CicloService cicloService;
    private final CarreraService carreraService;

    @Override
    @Transactional(readOnly = true)
    public List<CursoResponse> listar() {
        return cursoRepository.findAll().stream().map(this::toResponse).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public CursoResponse obtener(Long id) {
        return toResponse(buscarOFallar(id));
    }

    @Override
    @Transactional
    public CursoResponse crear(CursoRequest request) {
        cicloService.obtener(request.getCicloId());
        carreraService.obtener(request.getCarreraId());

        Curso curso = new Curso();
        curso.setCicloId(request.getCicloId());
        curso.setCarreraId(request.getCarreraId());
        curso.setNombre(request.getNombre());
        curso.setCodigo(request.getCodigo());
        curso.setCreditos(request.getCreditos());
        curso.setVacantes(request.getVacantes());
        return toResponse(cursoRepository.save(curso));
    }

    @Override
    @Transactional
    public CursoResponse actualizar(Long id, CursoRequest request) {
        Curso curso = buscarOFallar(id);
        cicloService.obtener(request.getCicloId());
        carreraService.obtener(request.getCarreraId());

        curso.setCicloId(request.getCicloId());
        curso.setCarreraId(request.getCarreraId());
        curso.setNombre(request.getNombre());
        curso.setCodigo(request.getCodigo());
        curso.setCreditos(request.getCreditos());
        curso.setVacantes(request.getVacantes());
        return toResponse(cursoRepository.save(curso));
    }

    @Override
    @Transactional
    public void eliminar(Long id) {
        cursoRepository.delete(buscarOFallar(id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<CursoResponse> listarPorCarrera(Long carreraId) {
        return cursoRepository.findByCarreraId(carreraId).stream().map(this::toResponse).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public MallaCurricularResponse malla(Long carreraId) {
        CarreraResponse carrera = carreraService.obtener(carreraId);
        List<Curso> cursos = cursoRepository.findByCarreraId(carreraId);

        Map<Long, List<Curso>> porCiclo = cursos.stream()
                .collect(Collectors.groupingBy(Curso::getCicloId, LinkedHashMap::new, Collectors.toList()));

        List<MallaCurricularResponse.CicloMalla> ciclosMalla = porCiclo.entrySet().stream()
                .map(e -> {
                    CicloResponse ciclo = cicloService.obtener(e.getKey());
                    return new MallaCurricularResponse.CicloMalla(
                            ciclo.getNumero(),
                            e.getValue().stream()
                                    .map(c -> new MallaCurricularResponse.CursoMalla(c.getId(), c.getCodigo(), c.getNombre(), c.getCreditos()))
                                    .toList());
                })
                .toList();

        return new MallaCurricularResponse(carrera.getId(), carrera.getNombre(), ciclosMalla);
    }

    private Curso buscarOFallar(Long id) {
        return cursoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Curso no encontrado: " + id));
    }

    private CursoResponse toResponse(Curso c) {
        return CursoResponse.builder()
                .id(c.getId())
                .nombre(c.getNombre())
                .codigo(c.getCodigo())
                .creditos(c.getCreditos())
                .vacantes(c.getVacantes())
                .cicloId(c.getCicloId())
                .carreraId(c.getCarreraId())
                .build();
    }
}

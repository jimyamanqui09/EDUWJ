package pe.edu.upeu.bomerp.academico.ciclo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.upeu.bomerp.academico.carrera.entity.Carrera;
import pe.edu.upeu.bomerp.academico.carrera.repository.CarreraRepository;
import pe.edu.upeu.bomerp.academico.ciclo.dto.CicloRequest;
import pe.edu.upeu.bomerp.academico.ciclo.dto.CicloResponse;
import pe.edu.upeu.bomerp.academico.ciclo.entity.Ciclo;
import pe.edu.upeu.bomerp.academico.ciclo.repository.CicloRepository;
import pe.edu.upeu.bomerp.exception.ResourceNotFoundException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CicloServiceImpl implements CicloService {
    private final CicloRepository cicloRepository;
    private final CarreraRepository carreraRepository;

    @Override
    @Transactional(readOnly = true)
    public List<CicloResponse> listar() {
        return cicloRepository.findAll().stream().map(this::toResponse).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public CicloResponse obtener(Long id) {
        return toResponse(buscarOFallar(id));
    }

    @Override
    @Transactional
    public CicloResponse crear(CicloRequest request) {
        Carrera carrera = carreraRepository.findById(request.getCarreraId())
                .orElseThrow(() -> new ResourceNotFoundException("Carrera no encontrada: " + request.getCarreraId()));

        Ciclo ciclo = new Ciclo();
        ciclo.setCarreraId(carrera.getId());
        ciclo.setNumero(request.getNumero());
        ciclo.setAnio(request.getAnio());
        ciclo.setSemestre(request.getSemestre());
        return toResponse(cicloRepository.save(ciclo));
    }

    @Override
    @Transactional
    public void eliminar(Long id) {
        cicloRepository.delete(buscarOFallar(id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<CicloResponse> listarPorCarrera(Long carreraId) {
        return cicloRepository.findByCarreraIdOrderByNumeroAsc(carreraId).stream().map(this::toResponse).toList();
    }

    private Ciclo buscarOFallar(Long id) {
        return cicloRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Ciclo no encontrado: " + id));
    }

    private CicloResponse toResponse(Ciclo c) {
        return CicloResponse.builder()
                .id(c.getId())
                .carreraId(c.getCarreraId())
                .numero(c.getNumero())
                .anio(c.getAnio())
                .semestre(c.getSemestre())
                .build();
    }
}

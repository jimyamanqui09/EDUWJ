package pe.edu.upeu.bomerp.academico.aula.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.upeu.bomerp.academico.aula.dto.AulaRequest;
import pe.edu.upeu.bomerp.academico.aula.dto.AulaResponse;
import pe.edu.upeu.bomerp.academico.aula.entity.Aula;
import pe.edu.upeu.bomerp.academico.aula.repository.AulaRepository;
import pe.edu.upeu.bomerp.exception.ResourceNotFoundException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AulaServiceImpl implements AulaService {
    private final AulaRepository aulaRepository;

    @Override
    @Transactional(readOnly = true)
    public List<AulaResponse> listar() {
        return aulaRepository.findAll().stream().map(this::toResponse).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public AulaResponse obtener(Long id) {
        return toResponse(buscarOFallar(id));
    }

    @Override
    @Transactional
    public AulaResponse crear(AulaRequest request) {
        Aula aula = new Aula();
        aula.setNombre(request.getNombre());
        aula.setCapacidad(request.getCapacidad());
        aula.setEdificio(request.getEdificio());
        return toResponse(aulaRepository.save(aula));
    }

    @Override
    @Transactional
    public void eliminar(Long id) {
        aulaRepository.delete(buscarOFallar(id));
    }

    private Aula buscarOFallar(Long id) {
        return aulaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Aula no encontrada: " + id));
    }

    private AulaResponse toResponse(Aula a) {
        return AulaResponse.builder()
                .id(a.getId())
                .nombre(a.getNombre())
                .capacidad(a.getCapacidad())
                .edificio(a.getEdificio())
                .build();
    }
}

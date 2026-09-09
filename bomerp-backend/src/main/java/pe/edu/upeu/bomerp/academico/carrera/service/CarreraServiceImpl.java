package pe.edu.upeu.bomerp.academico.carrera.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.upeu.bomerp.academico.carrera.dto.CarreraRequest;
import pe.edu.upeu.bomerp.academico.carrera.dto.CarreraResponse;
import pe.edu.upeu.bomerp.academico.carrera.entity.Carrera;
import pe.edu.upeu.bomerp.academico.carrera.repository.CarreraRepository;
import pe.edu.upeu.bomerp.exception.ResourceNotFoundException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CarreraServiceImpl implements CarreraService {
    private final CarreraRepository carreraRepository;

    @Override
    @Transactional(readOnly = true)
    public List<CarreraResponse> listar() {
        return carreraRepository.findAll().stream().map(this::toResponse).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public CarreraResponse obtener(Long id) {
        return toResponse(buscarOFallar(id));
    }

    @Override
    @Transactional
    public CarreraResponse crear(CarreraRequest request) {
        Carrera carrera = new Carrera();
        carrera.setCodigo(request.getCodigo());
        carrera.setNombre(request.getNombre());
        carrera.setTotalCreditos(request.getTotalCreditos());
        return toResponse(carreraRepository.save(carrera));
    }

    @Override
    @Transactional
    public CarreraResponse actualizar(Long id, CarreraRequest request) {
        Carrera carrera = buscarOFallar(id);
        carrera.setCodigo(request.getCodigo());
        carrera.setNombre(request.getNombre());
        carrera.setTotalCreditos(request.getTotalCreditos());
        return toResponse(carreraRepository.save(carrera));
    }

    @Override
    @Transactional
    public void eliminar(Long id) {
        carreraRepository.delete(buscarOFallar(id));
    }

    private Carrera buscarOFallar(Long id) {
        return carreraRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Carrera no encontrada: " + id));
    }

    private CarreraResponse toResponse(Carrera c) {
        return CarreraResponse.builder()
                .id(c.getId())
                .codigo(c.getCodigo())
                .nombre(c.getNombre())
                .totalCreditos(c.getTotalCreditos())
                .build();
    }
}

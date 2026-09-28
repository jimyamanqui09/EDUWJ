package pe.edu.upeu.bomerp.matricula.alumno.service;

import pe.edu.upeu.bomerp.matricula.alumno.dto.AlumnoRequest;
import pe.edu.upeu.bomerp.matricula.alumno.dto.AlumnoResponse;
import pe.edu.upeu.bomerp.matricula.alumno.entity.Alumno;
import pe.edu.upeu.bomerp.matricula.alumno.mapper.AlumnoMapper;
import pe.edu.upeu.bomerp.matricula.alumno.repository.AlumnoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class AlumnoServiceImpl implements AlumnoService {

    private final AlumnoRepository alumnoRepository;
    private final AlumnoMapper alumnoMapper;

    @Override
    @Transactional(readOnly = true)
    public List<AlumnoResponse> listar() {
        return alumnoRepository.findAll().stream()
                .map(alumnoMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public AlumnoResponse obtenerPorId(Long id) {
        Alumno alumno = alumnoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Alumno no encontrado con id: " + id));
        return alumnoMapper.toResponse(alumno);
    }

    @Override
    @Transactional
    public AlumnoResponse crear(AlumnoRequest request) {
        // Validar que el DNI no exista ya
        if (alumnoRepository.findByDni(request.getDni()) != null) {
            throw new RuntimeException("Ya existe un alumno con el DNI: " + request.getDni());
        }
        Alumno alumno = alumnoMapper.toEntity(request);
        alumno = alumnoRepository.save(alumno);
        return alumnoMapper.toResponse(alumno);
    }

    @Override
    @Transactional
    public AlumnoResponse actualizar(Long id, AlumnoRequest request) {
        Alumno alumno = alumnoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Alumno no encontrado con id: " + id));
        
        alumno.setNombre(request.getNombre());
        alumno.setApellidoPaterno(request.getApellidoPaterno());
        alumno.setApellidoMaterno(request.getApellidoMaterno());
        alumno.setDni(request.getDni());
        alumno.setFechaIngreso(request.getFechaIngreso());
        alumno.setGrado(request.getGrado());
        alumno.setSeccion(request.getSeccion());
        
        alumno = alumnoRepository.save(alumno);
        return alumnoMapper.toResponse(alumno);
    }

    @Override
    @Transactional
    public void eliminar(Long id) {
        if (!alumnoRepository.existsById(id)) {
            throw new RuntimeException("Alumno no encontrado con id: " + id);
        }
        alumnoRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public AlumnoResponse buscarPorDni(String dni) {
        Alumno alumno = alumnoRepository.findByDni(dni);
        if (alumno == null) {
            throw new RuntimeException("Alumno no encontrado con DNI: " + dni);
        }
        return alumnoMapper.toResponse(alumno);
    }
}
package pe.edu.upeu.bomerp.asistencia.asistencia.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.upeu.bomerp.asistencia.asistencia.dto.AsistenciaBulkRequest;
import pe.edu.upeu.bomerp.asistencia.asistencia.dto.AsistenciaItemRequest;
import pe.edu.upeu.bomerp.asistencia.asistencia.dto.AsistenciaResponse;
import pe.edu.upeu.bomerp.asistencia.asistencia.entity.Asistencia;
import pe.edu.upeu.bomerp.asistencia.asistencia.repository.AsistenciaRepository;
import pe.edu.upeu.bomerp.asistencia.clase.entity.Clase;
import pe.edu.upeu.bomerp.asistencia.clase.repository.ClaseRepository;
import pe.edu.upeu.bomerp.exception.ResourceNotFoundException;
import pe.edu.upeu.bomerp.seguridad.usuario.dto.UsuarioResponse;
import pe.edu.upeu.bomerp.seguridad.usuario.service.UsuarioService;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AsistenciaServiceImpl implements AsistenciaService {
    private final AsistenciaRepository asistenciaRepository;
    private final ClaseRepository claseRepository;
    private final UsuarioService usuarioService;

    @Override
    @Transactional
    public List<AsistenciaResponse> crearBulk(Long claseId, AsistenciaBulkRequest request) {
        Clase clase = claseRepository.findById(claseId)
                .orElseThrow(() -> new ResourceNotFoundException("Clase no encontrada: " + claseId));

        List<Asistencia> asistencias = new ArrayList<>();
        for (AsistenciaItemRequest item : request.getAsistencias()) {
            usuarioService.obtener(item.getUsuarioId());

            Asistencia asistencia = new Asistencia();
            asistencia.setClaseId(clase.getId());
            asistencia.setUsuarioId(item.getUsuarioId());
            asistencia.setEstado(Asistencia.EstadoAsistencia.valueOf(item.getEstado()));
            asistencias.add(asistenciaRepository.save(asistencia));
        }

        return asistencias.stream().map(this::toResponse).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<AsistenciaResponse> listarPorClase(Long claseId) {
        return asistenciaRepository.findByClaseId(claseId).stream().map(this::toResponse).toList();
    }

    private AsistenciaResponse toResponse(Asistencia a) {
        UsuarioResponse usuario = usuarioService.obtener(a.getUsuarioId());
        return AsistenciaResponse.builder()
                .id(a.getId())
                .claseId(a.getClaseId())
                .usuarioId(a.getUsuarioId())
                .usuarioNombre(usuario.getNombre())
                .estado(a.getEstado().name())
                .build();
    }
}

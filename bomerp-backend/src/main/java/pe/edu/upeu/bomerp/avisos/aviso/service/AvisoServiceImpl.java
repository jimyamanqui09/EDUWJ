package pe.edu.upeu.bomerp.avisos.aviso.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.upeu.bomerp.avisos.aviso.dto.AvisoRequest;
import pe.edu.upeu.bomerp.avisos.aviso.dto.AvisoResponse;
import pe.edu.upeu.bomerp.avisos.aviso.entity.Aviso;
import pe.edu.upeu.bomerp.avisos.aviso.repository.AvisoRepository;
import pe.edu.upeu.bomerp.seguridad.usuario.service.UsuarioService;
import pe.edu.upeu.bomerp.seguridad.usuario.dto.UsuarioResumen;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AvisoServiceImpl implements AvisoService {
    private final AvisoRepository avisoRepository;
    private final UsuarioService usuarioService;

    @Override
    @Transactional(readOnly = true)
    public List<AvisoResponse> listar() {
        return avisoRepository.findAllByOrderByFechaPublicacionDesc().stream().map(this::toResponse).toList();
    }

    @Override
    @Transactional
    public AvisoResponse crear(AvisoRequest request) {
        UsuarioResumen autor = usuarioService.obtenerResumen(request.getAutorId());

        Aviso aviso = new Aviso();
        aviso.setTitulo(request.getTitulo());
        aviso.setContenido(request.getContenido());
        aviso.setFechaPublicacion(LocalDateTime.now());
        aviso.setAutorId(autor.id());
        return toResponse(avisoRepository.save(aviso));
    }

    private AvisoResponse toResponse(Aviso a) {
        UsuarioResumen autor = usuarioService.obtenerResumen(a.getAutorId());
        return AvisoResponse.builder()
                .id(a.getId())
                .titulo(a.getTitulo())
                .contenido(a.getContenido())
                .fechaPublicacion(a.getFechaPublicacion())
                .autorNombre(autor.nombre())
                .build();
    }
}

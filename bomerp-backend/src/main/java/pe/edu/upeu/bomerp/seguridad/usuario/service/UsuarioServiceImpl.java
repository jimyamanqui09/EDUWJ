package pe.edu.upeu.bomerp.seguridad.usuario.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.upeu.bomerp.exception.ResourceNotFoundException;
import pe.edu.upeu.bomerp.exception.DuplicateResourceException;
import pe.edu.upeu.bomerp.seguridad.usuario.dto.UsuarioRequest;
import pe.edu.upeu.bomerp.seguridad.usuario.dto.UsuarioResponse;
import pe.edu.upeu.bomerp.seguridad.usuario.dto.UsuarioResumen;
import pe.edu.upeu.bomerp.seguridad.usuario.entity.Usuario;
import pe.edu.upeu.bomerp.seguridad.usuario.repository.UsuarioRepository;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {
    private final UsuarioRepository usuarioRepository;

    @Override
    @Transactional(readOnly = true)
    public List<UsuarioResponse> listar() {
        return usuarioRepository.findAll().stream().map(this::toResponse).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public UsuarioResponse obtener(Long id) {
        return toResponse(buscarOFallar(id));
    }

    @Override
    @Transactional(readOnly = true)
    public UsuarioResumen obtenerResumen(Long id) {
        Usuario u = buscarOFallar(id);
        return new UsuarioResumen(u.getId(), u.getNombre(), u.getEmail());
    }

    @Override
    @Transactional
    public UsuarioResponse crear(UsuarioRequest request) {
        if (usuarioRepository.existsByEmail(request.getEmail())) {
            throw new DuplicateResourceException("Ya existe un usuario con el email: " + request.getEmail());
        }
        if (usuarioRepository.existsByDni(request.getDni())) {
            throw new DuplicateResourceException("Ya existe un usuario con el DNI: " + request.getDni());
        }

        Usuario usuario = new Usuario();
        usuario.setDni(request.getDni());
        usuario.setNombre(request.getNombre());
        usuario.setEmail(request.getEmail());
        usuario.setPassword(request.getPassword());
        usuario.setTipoUsuario(Usuario.TipoUsuario.valueOf(request.getTipoUsuario()));
        usuario.setActivo(true);
        usuario.setFechaCreacion(LocalDateTime.now());

        return toResponse(usuarioRepository.save(usuario));
    }

    @Override
    @Transactional
    public UsuarioResponse actualizar(Long id, UsuarioRequest request) {
        Usuario usuario = buscarOFallar(id);
        usuario.setDni(request.getDni());
        usuario.setNombre(request.getNombre());
        usuario.setEmail(request.getEmail());
        usuario.setPassword(request.getPassword());
        usuario.setTipoUsuario(Usuario.TipoUsuario.valueOf(request.getTipoUsuario()));
        return toResponse(usuarioRepository.save(usuario));
    }

    @Override
    @Transactional
    public void eliminar(Long id) {
        usuarioRepository.delete(buscarOFallar(id));
    }

    private Usuario buscarOFallar(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado: " + id));
    }

    private UsuarioResponse toResponse(Usuario u) {
        return UsuarioResponse.builder()
                .id(u.getId())
                .dni(u.getDni())
                .nombre(u.getNombre())
                .email(u.getEmail())
                .tipoUsuario(u.getTipoUsuario().name())
                .activo(u.getActivo())
                .build();
    }
}

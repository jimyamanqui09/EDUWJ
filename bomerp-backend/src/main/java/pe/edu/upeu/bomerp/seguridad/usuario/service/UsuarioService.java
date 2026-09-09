package pe.edu.upeu.bomerp.seguridad.usuario.service;

import pe.edu.upeu.bomerp.seguridad.usuario.dto.UsuarioRequest;
import pe.edu.upeu.bomerp.seguridad.usuario.dto.UsuarioResponse;
import pe.edu.upeu.bomerp.seguridad.usuario.dto.UsuarioResumen;
import java.util.List;

public interface UsuarioService {
    List<UsuarioResponse> listar();
    UsuarioResponse obtener(Long id);
    UsuarioResumen obtenerResumen(Long id);
    UsuarioResponse crear(UsuarioRequest request);
    UsuarioResponse actualizar(Long id, UsuarioRequest request);
    void eliminar(Long id);
}

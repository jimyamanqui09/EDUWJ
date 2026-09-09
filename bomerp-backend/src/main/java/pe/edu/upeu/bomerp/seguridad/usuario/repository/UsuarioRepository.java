package pe.edu.upeu.bomerp.seguridad.usuario.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upeu.bomerp.seguridad.usuario.entity.Usuario;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    boolean existsByEmail(String email);
    boolean existsByDni(String dni);
    Optional<Usuario> findByEmail(String email);
}

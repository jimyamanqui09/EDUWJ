package pe.edu.upeu.bomerp.matriculas.matricula.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upeu.bomerp.matriculas.matricula.entity.Matricula;
import java.util.List;
import java.util.Optional;

public interface MatriculaRepository extends JpaRepository<Matricula, Long> {
    boolean existsByUsuarioIdAndCursoId(Long usuarioId, Long cursoId);
    List<Matricula> findByCursoIdAndEstado(Long cursoId, String estado);
    List<Matricula> findByUsuarioIdAndEstado(Long usuarioId, String estado);
    Optional<Matricula> findByUsuarioIdAndCursoId(Long usuarioId, Long cursoId);
}

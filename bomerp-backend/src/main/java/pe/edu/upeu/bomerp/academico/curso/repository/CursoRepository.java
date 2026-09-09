package pe.edu.upeu.bomerp.academico.curso.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upeu.bomerp.academico.curso.entity.Curso;
import java.util.List;

public interface CursoRepository extends JpaRepository<Curso, Long> {
    List<Curso> findByCarreraId(Long carreraId);
    List<Curso> findByCicloId(Long cicloId);
}

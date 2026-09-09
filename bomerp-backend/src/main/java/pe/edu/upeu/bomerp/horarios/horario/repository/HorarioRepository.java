package pe.edu.upeu.bomerp.horarios.horario.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upeu.bomerp.horarios.horario.entity.Horario;
import java.util.List;

public interface HorarioRepository extends JpaRepository<Horario, Long> {
    List<Horario> findByCursoId(Long cursoId);
    List<Horario> findByCursoIdIn(List<Long> cursoIds);
}

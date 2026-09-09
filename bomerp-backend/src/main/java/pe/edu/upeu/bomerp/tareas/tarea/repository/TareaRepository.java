package pe.edu.upeu.bomerp.tareas.tarea.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upeu.bomerp.tareas.tarea.entity.Tarea;
import java.util.List;

public interface TareaRepository extends JpaRepository<Tarea, Long> {
    List<Tarea> findByCursoId(Long cursoId);
}

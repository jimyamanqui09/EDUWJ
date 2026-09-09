package pe.edu.upeu.bomerp.asistencia.clase.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upeu.bomerp.asistencia.clase.entity.Clase;
import java.util.List;

public interface ClaseRepository extends JpaRepository<Clase, Long> {
    List<Clase> findByHorarioId(Long horarioId);
}

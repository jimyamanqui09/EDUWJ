package pe.edu.upeu.bomerp.asistencia.asistencia.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upeu.bomerp.asistencia.asistencia.entity.Asistencia;
import java.util.List;

public interface AsistenciaRepository extends JpaRepository<Asistencia, Long> {
    List<Asistencia> findByClaseId(Long claseId);
}

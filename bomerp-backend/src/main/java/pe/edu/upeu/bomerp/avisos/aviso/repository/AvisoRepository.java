package pe.edu.upeu.bomerp.avisos.aviso.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upeu.bomerp.avisos.aviso.entity.Aviso;
import java.util.List;

public interface AvisoRepository extends JpaRepository<Aviso, Long> {
    List<Aviso> findAllByOrderByFechaPublicacionDesc();
}

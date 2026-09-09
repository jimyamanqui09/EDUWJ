package pe.edu.upeu.bomerp.tareas.entrega.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upeu.bomerp.tareas.entrega.entity.Entrega;
import java.util.List;
import java.util.Optional;

public interface EntregaRepository extends JpaRepository<Entrega, Long> {
    List<Entrega> findByUsuarioId(Long usuarioId);
    Optional<Entrega> findByTareaIdAndUsuarioId(Long tareaId, Long usuarioId);
    List<Entrega> findByTareaId(Long tareaId);
}

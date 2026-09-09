package pe.edu.upeu.bomerp.academico.ciclo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upeu.bomerp.academico.ciclo.entity.Ciclo;
import java.util.List;

public interface CicloRepository extends JpaRepository<Ciclo, Long> {
    List<Ciclo> findByCarreraIdOrderByNumeroAsc(Long carreraId);
}

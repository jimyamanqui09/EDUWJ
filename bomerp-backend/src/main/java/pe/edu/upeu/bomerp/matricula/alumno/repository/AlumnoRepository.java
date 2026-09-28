package pe.edu.upeu.bomerp.matricula.alumno.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upeu.bomerp.matricula.alumno.entity.Alumno;

public interface AlumnoRepository extends JpaRepository<Alumno, Long> {
    Alumno findByDni(String dni);
}
package pe.edu.upeu.bomerp.matricula.alumno.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "ALUMNOS", schema = "BOM_MATRICULA")
@Getter
@Setter
@NoArgsConstructor
public class Alumno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "NOMBRE", nullable = false, length = 100)
    private String nombre;

    @Column(name = "APELLIDO_PATERNO", nullable = false, length = 50)
    private String apellidoPaterno;

    @Column(name = "APELLIDO_MATERNO", length = 50)
    private String apellidoMaterno;

    @Column(name = "DNI", nullable = false, unique = true, length = 20)
    private String dni;

    @Column(name = "FECHA_INGRESO", nullable = false)
    private String fechaIngreso;

    @Column(name = "GRADO", nullable = false, length = 20)
    private String grado;

    @Column(name = "SECCION", length = 10)
    private String seccion;
}
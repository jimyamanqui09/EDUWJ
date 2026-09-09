package pe.edu.upeu.bomerp.academico.curso.entity;

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
@Table(name = "CURSOS", schema = "BOM_ACADEMICO")
@Getter
@Setter
@NoArgsConstructor
public class Curso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "CICLO_ID", nullable = false)
    private Long cicloId;

    @Column(name = "CARRERA_ID", nullable = false)
    private Long carreraId;

    @Column(name = "NOMBRE", nullable = false, length = 120)
    private String nombre;

    @Column(name = "CODIGO", nullable = false, unique = true, length = 10)
    private String codigo;

    @Column(name = "CREDITOS", nullable = false)
    private Integer creditos;

    @Column(name = "VACANTES", nullable = false)
    private Integer vacantes;
}

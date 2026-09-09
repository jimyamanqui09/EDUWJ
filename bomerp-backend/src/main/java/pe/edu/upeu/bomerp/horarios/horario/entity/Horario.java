package pe.edu.upeu.bomerp.horarios.horario.entity;

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
@Table(name = "HORARIOS", schema = "BOM_HORARIOS")
@Getter
@Setter
@NoArgsConstructor
public class Horario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "CURSO_ID", nullable = false)
    private Long cursoId;

    @Column(name = "DIA_SEMANA", nullable = false, length = 10)
    private String diaSemana;

    @Column(name = "HORA_INICIO", nullable = false, length = 5)
    private String horaInicio;

    @Column(name = "HORA_FIN", nullable = false, length = 5)
    private String horaFin;

    @Column(name = "AULA_ID", nullable = false)
    private Long aulaId;
}

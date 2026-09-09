package pe.edu.upeu.bomerp.asistencia.clase.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;

@Entity
@Table(name = "CLASES", schema = "BOM_ASISTENCIA")
@Getter
@Setter
@NoArgsConstructor
public class Clase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "HORARIO_ID", nullable = false)
    private Long horarioId;

    @Column(name = "FECHA", nullable = false)
    private LocalDate fecha;

    @Column(name = "TEMA", length = 150)
    private String tema;
}

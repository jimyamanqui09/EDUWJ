package pe.edu.upeu.bomerp.calificaciones.registro.entity;

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
@Table(name = "NOTAS", schema = "BOM_CALIFICACIONES")
@Getter
@Setter
@NoArgsConstructor
public class Nota {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "ALUMNO_ID", nullable = false)
    private Long alumnoId;

    @Column(name = "EVALUACION", nullable = false, length = 100)
    private String evaluacion;

    @Column(name = "VALOR", nullable = false)
    private Double valor;

    @Column(name = "PONDERACION", nullable = false)
    private Double ponderacion;

    @Column(name = "FECHA_REGISTRO", nullable = false)
    private String fechaRegistro;

    @Column(name = "ESTADO", nullable = false, length = 20)
    private String estado; // 'REGISTRADA', 'CERRADA', 'PROVISIONAL'
}
package pe.edu.upeu.bomerp.tareas.entrega.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "ENTREGAS", schema = "BOM_TAREAS")
@Getter
@Setter
@NoArgsConstructor
public class Entrega {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "TAREA_ID", nullable = false)
    private Long tareaId;

    @Column(name = "USUARIO_ID", nullable = false)
    private Long usuarioId;

    @Column(name = "FECHA_ENTREGA", nullable = false)
    private LocalDateTime fechaEntrega;

    @Column(name = "URL_TRABAJO", nullable = false, length = 300)
    private String urlTrabajo;

    @Column(name = "CALIFICACION", precision = 4, scale = 2)
    private BigDecimal calificacion;

    @Column(name = "OBSERVACIONES", length = 300)
    private String observaciones;
}

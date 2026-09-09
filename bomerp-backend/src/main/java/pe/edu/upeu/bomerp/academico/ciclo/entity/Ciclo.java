package pe.edu.upeu.bomerp.academico.ciclo.entity;

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
@Table(name = "CICLOS", schema = "BOM_ACADEMICO")
@Getter
@Setter
@NoArgsConstructor
public class Ciclo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "CARRERA_ID", nullable = false)
    private Long carreraId;

    @Column(name = "NUMERO", nullable = false)
    private Integer numero;

    @Column(name = "ANIO", nullable = false)
    private Integer anio;

    @Column(name = "SEMESTRE", nullable = false)
    private Integer semestre;
}

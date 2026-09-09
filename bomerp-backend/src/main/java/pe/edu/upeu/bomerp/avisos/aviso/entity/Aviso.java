package pe.edu.upeu.bomerp.avisos.aviso.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;

@Entity
@Table(name = "AVISOS", schema = "BOM_AVISOS")
@Getter
@Setter
@NoArgsConstructor
public class Aviso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "TITULO", nullable = false, length = 150)
    private String titulo;

    @Column(name = "CONTENIDO", nullable = false, length = 1000)
    private String contenido;

    @Column(name = "FECHA_PUBLICACION", nullable = false)
    private LocalDateTime fechaPublicacion;

    @Column(name = "AUTOR_ID", nullable = false)
    private Long autorId;
}

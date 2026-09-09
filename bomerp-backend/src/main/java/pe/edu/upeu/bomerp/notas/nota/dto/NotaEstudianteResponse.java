package pe.edu.upeu.bomerp.notas.nota.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@AllArgsConstructor
public class NotaEstudianteResponse {
    private Long usuarioId;
    private String usuarioNombre;
    private List<NotaCurso> cursos;
    private BigDecimal promedioGeneral;

    @Getter
    @AllArgsConstructor
    public static class NotaCurso {
        private Long cursoId;
        private String cursoNombre;
        private List<NotaDetalle> notas;
        private BigDecimal promedio;
    }

    @Getter
    @AllArgsConstructor
    public static class NotaDetalle {
        private Long tareaId;
        private String tareaTitulo;
        private BigDecimal peso;
        private BigDecimal calificacion;
    }
}

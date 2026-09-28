package pe.edu.upeu.bomerp.calificaciones.registro.dto;

import lombok.Data;

@Data
public class NotaResponse {
    private Long id;
    private Long alumnoId;
    private String evaluacion;
    private Double valor;
    private Double ponderacion;
    private String fechaRegistro;
    private String estado;
}
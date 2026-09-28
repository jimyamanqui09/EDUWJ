package pe.edu.upeu.bomerp.calificaciones.registro.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class NotaRequest {
    @NotNull(message = "El alumno ID es obligatorio")
    private Long alumnoId;

    @NotNull(message = "La evaluación es obligatoria")
    @Size(max = 100, message = "La evaluación no puede exceder 100 caracteres")
    private String evaluacion;

    @NotNull(message = "El valor de la nota es obligatorio")
    @Min(value = 0, message = "La nota mínima es 0")
    @Max(value = 20, message = "La nota máxima es 20")
    private Double valor;

    @NotNull(message = "La ponderación es obligatoria")
    @Min(value = 0, message = "La ponderación mínima es 0")
    @Max(value = 100, message = "La ponderación máxima es 100")
    private Double ponderacion;

    @NotNull(message = "La fecha de registro es obligatoria")
    private String fechaRegistro;

    @Size(max = 20, message = "El estado no puede exceder 20 caracteres")
    private String estado;
}
package pe.edu.upeu.bomerp.matricula.alumno.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class AlumnoRequest {
    @NotNull(message = "El nombre es obligatorio")
    @Size(max = 100, message = "El nombre no puede exceder 100 caracteres")
    private String nombre;

    @NotNull(message = "El apellido paterno es obligatorio")
    @Size(max = 50, message = "El apellido paterno no puede exceder 50 caracteres")
    private String apellidoPaterno;

    @Size(max = 50, message = "El apellido materno no puede exceder 50 caracteres")
    private String apellidoMaterno;

    @NotNull(message = "El DNI es obligatorio")
    @Size(max = 20, message = "El DNI no puede exceder 20 caracteres")
    private String dni;

    @NotNull(message = "La fecha de ingreso es obligatoria")
    private String fechaIngreso;

    @NotNull(message = "El grado es obligatorio")
    @Size(max = 20, message = "El grado no puede exceder 20 caracteres")
    private String grado;

    @Size(max = 10, message = "La sección no puede exceder 10 caracteres")
    private String seccion;
}
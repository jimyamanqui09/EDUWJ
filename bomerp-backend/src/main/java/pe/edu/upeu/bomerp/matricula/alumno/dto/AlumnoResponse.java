package pe.edu.upeu.bomerp.matricula.alumno.dto;

import lombok.Data;

@Data
public class AlumnoResponse {
    private Long id;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String dni;
    private String fechaIngreso;
    private String grado;
    private String seccion;
}
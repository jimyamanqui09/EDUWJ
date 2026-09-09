package pe.edu.upeu.bomerp.matriculas.matricula.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upeu.bomerp.matriculas.matricula.dto.MatriculaRequest;
import pe.edu.upeu.bomerp.matriculas.matricula.dto.MatriculaResponse;
import pe.edu.upeu.bomerp.matriculas.matricula.dto.MatriculadoResponse;
import pe.edu.upeu.bomerp.matriculas.matricula.service.MatriculaService;
import java.util.List;

@Tag(name = "Matriculas")
@RestController
@RequestMapping("/api/v1/matriculas")
@RequiredArgsConstructor
public class MatriculaController {
    private final MatriculaService matriculaService;

    @Operation(summary = "Lista todas las matriculas")
    @GetMapping
    public ResponseEntity<List<MatriculaResponse>> listar() {
        return ResponseEntity.ok(matriculaService.listar());
    }

    @Operation(summary = "Registra una matricula (resta vacantes del curso)")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MatriculaResponse crear(@Valid @RequestBody MatriculaRequest request) {
        return matriculaService.crear(request);
    }

    @Operation(summary = "Lista los alumnos matriculados en un curso (lista de clase)")
    @GetMapping("/curso/{cursoId}/matriculados")
    public ResponseEntity<List<MatriculadoResponse>> listarMatriculados(@PathVariable Long cursoId) {
        return ResponseEntity.ok(matriculaService.listarMatriculados(cursoId));
    }

    @Operation(summary = "Lista los cursos de un estudiante")
    @GetMapping("/estudiante/{usuarioId}")
    public ResponseEntity<List<MatriculaResponse>> listarCursosPorEstudiante(@PathVariable Long usuarioId) {
        return ResponseEntity.ok(matriculaService.listarCursosPorEstudiante(usuarioId));
    }
}

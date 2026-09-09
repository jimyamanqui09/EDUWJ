package pe.edu.upeu.bomerp.horarios.horario.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upeu.bomerp.horarios.horario.dto.HorarioEstudianteResponse;
import pe.edu.upeu.bomerp.horarios.horario.dto.HorarioRequest;
import pe.edu.upeu.bomerp.horarios.horario.dto.HorarioResponse;
import pe.edu.upeu.bomerp.horarios.horario.service.HorarioService;
import pe.edu.upeu.bomerp.matriculas.matricula.dto.MatriculaResponse;
import pe.edu.upeu.bomerp.matriculas.matricula.service.MatriculaService;
import java.util.List;

@Tag(name = "Horarios")
@RestController
@RequestMapping("/api/v1/horarios")
@RequiredArgsConstructor
public class HorarioController {
    private final HorarioService horarioService;
    private final MatriculaService matriculaService;

    @Operation(summary = "Lista los horarios de un curso")
    @GetMapping("/curso/{cursoId}")
    public ResponseEntity<List<HorarioResponse>> listarPorCurso(@PathVariable Long cursoId) {
        return ResponseEntity.ok(horarioService.listarPorCurso(cursoId));
    }

    @Operation(summary = "Registra un horario nuevo")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public HorarioResponse crear(@Valid @RequestBody HorarioRequest request) {
        return horarioService.crear(request);
    }

    @Operation(summary = "Elimina un horario")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        horarioService.eliminar(id);
    }

    @Operation(summary = "Horario completo de un estudiante (cursos matriculados con dias, horas y aulas)")
    @GetMapping("/estudiante/{usuarioId}/horario")
    public ResponseEntity<List<HorarioEstudianteResponse>> horarioEstudiante(@PathVariable Long usuarioId) {
        List<Long> cursoIds = matriculaService.listarCursosPorEstudiante(usuarioId).stream()
                .map(MatriculaResponse::getCursoId)
                .toList();
        return ResponseEntity.ok(horarioService.horarioEstudiante(usuarioId, cursoIds));
    }
}

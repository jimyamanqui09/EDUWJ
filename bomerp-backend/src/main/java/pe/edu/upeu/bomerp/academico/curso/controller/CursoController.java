package pe.edu.upeu.bomerp.academico.curso.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upeu.bomerp.academico.curso.dto.CursoRequest;
import pe.edu.upeu.bomerp.academico.curso.dto.CursoResponse;
import pe.edu.upeu.bomerp.academico.curso.service.CursoService;
import pe.edu.upeu.bomerp.academico.malla.dto.MallaCurricularResponse;
import java.util.List;

@Tag(name = "Cursos")
@RestController
@RequestMapping("/api/v1/cursos")
@RequiredArgsConstructor
public class CursoController {
    private final CursoService cursoService;

    @Operation(summary = "Lista todos los cursos")
    @GetMapping
    public ResponseEntity<List<CursoResponse>> listar() {
        return ResponseEntity.ok(cursoService.listar());
    }

    @Operation(summary = "Consulta un curso por id")
    @GetMapping("/{id}")
    public ResponseEntity<CursoResponse> obtener(@PathVariable Long id) {
        return ResponseEntity.ok(cursoService.obtener(id));
    }

    @Operation(summary = "Registra un curso nuevo")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CursoResponse crear(@Valid @RequestBody CursoRequest request) {
        return cursoService.crear(request);
    }

    @Operation(summary = "Elimina un curso")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        cursoService.eliminar(id);
    }

    @Operation(summary = "Lista los cursos de una carrera")
    @GetMapping("/carrera/{carreraId}")
    public ResponseEntity<List<CursoResponse>> listarPorCarrera(@PathVariable Long carreraId) {
        return ResponseEntity.ok(cursoService.listarPorCarrera(carreraId));
    }

    @Operation(summary = "Malla curricular de una carrera agrupada por ciclo")
    @GetMapping("/carrera/{carreraId}/malla")
    public ResponseEntity<MallaCurricularResponse> malla(@PathVariable Long carreraId) {
        return ResponseEntity.ok(cursoService.malla(carreraId));
    }
}

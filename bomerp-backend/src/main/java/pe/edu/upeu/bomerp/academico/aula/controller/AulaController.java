package pe.edu.upeu.bomerp.academico.aula.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upeu.bomerp.academico.aula.dto.AulaRequest;
import pe.edu.upeu.bomerp.academico.aula.dto.AulaResponse;
import pe.edu.upeu.bomerp.academico.aula.service.AulaService;
import java.util.List;

@Tag(name = "Aulas")
@RestController
@RequestMapping("/api/v1/aulas")
@RequiredArgsConstructor
public class AulaController {
    private final AulaService aulaService;

    @Operation(summary = "Lista todas las aulas")
    @GetMapping
    public ResponseEntity<List<AulaResponse>> listar() {
        return ResponseEntity.ok(aulaService.listar());
    }

    @Operation(summary = "Consulta un aula por id")
    @GetMapping("/{id}")
    public ResponseEntity<AulaResponse> obtener(@PathVariable Long id) {
        return ResponseEntity.ok(aulaService.obtener(id));
    }

    @Operation(summary = "Registra un aula nueva")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AulaResponse crear(@Valid @RequestBody AulaRequest request) {
        return aulaService.crear(request);
    }

    @Operation(summary = "Elimina un aula")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        aulaService.eliminar(id);
    }
}

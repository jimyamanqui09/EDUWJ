package pe.edu.upeu.bomerp.notas.nota.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upeu.bomerp.notas.nota.dto.NotaEstudianteResponse;
import pe.edu.upeu.bomerp.notas.nota.service.NotaService;

@Tag(name = "Notas")
@RestController
@RequestMapping("/api/v1/estudiantes/{usuarioId}/notas")
@RequiredArgsConstructor
public class NotaController {
    private final NotaService notaService;

    @Operation(summary = "Consulta las notas de un estudiante por curso con promedio general")
    @GetMapping
    public ResponseEntity<NotaEstudianteResponse> notasEstudiante(@PathVariable Long usuarioId) {
        return ResponseEntity.ok(notaService.notasEstudiante(usuarioId));
    }
}

package pe.edu.upeu.bomerp.asistencia.clase.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upeu.bomerp.asistencia.clase.dto.ClaseRequest;
import pe.edu.upeu.bomerp.asistencia.clase.dto.ClaseResponse;
import pe.edu.upeu.bomerp.asistencia.clase.service.ClaseService;
import java.util.List;

@Tag(name = "Clases")
@RestController
@RequestMapping("/api/v1/clases")
@RequiredArgsConstructor
public class ClaseController {
    private final ClaseService claseService;

    @Operation(summary = "Lista las clases de un horario")
    @GetMapping("/horario/{horarioId}")
    public ResponseEntity<List<ClaseResponse>> listarPorHorario(@PathVariable Long horarioId) {
        return ResponseEntity.ok(claseService.listarPorHorario(horarioId));
    }

    @Operation(summary = "Registra una clase nueva")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ClaseResponse crear(@Valid @RequestBody ClaseRequest request) {
        return claseService.crear(request);
    }
}

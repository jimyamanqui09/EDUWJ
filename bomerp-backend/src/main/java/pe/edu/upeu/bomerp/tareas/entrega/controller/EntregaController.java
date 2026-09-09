package pe.edu.upeu.bomerp.tareas.entrega.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upeu.bomerp.tareas.entrega.dto.EntregaRequest;
import pe.edu.upeu.bomerp.tareas.entrega.dto.EntregaResponse;
import pe.edu.upeu.bomerp.tareas.entrega.service.EntregaService;
import java.util.List;

@Tag(name = "Entregas")
@RestController
@RequestMapping("/api/v1/tareas/{tareaId}/entregas")
@RequiredArgsConstructor
public class EntregaController {
    private final EntregaService entregaService;

    @Operation(summary = "Lista las entregas de una tarea")
    @GetMapping
    public ResponseEntity<List<EntregaResponse>> listar(@PathVariable Long tareaId) {
        return ResponseEntity.ok(entregaService.listarPorTarea(tareaId));
    }

    @Operation(summary = "Registra la entrega de un alumno para una tarea")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EntregaResponse crear(@PathVariable Long tareaId, @Valid @RequestBody EntregaRequest request) {
        return entregaService.crear(tareaId, request);
    }
}

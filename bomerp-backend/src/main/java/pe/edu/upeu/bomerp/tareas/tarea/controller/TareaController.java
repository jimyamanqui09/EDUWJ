package pe.edu.upeu.bomerp.tareas.tarea.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upeu.bomerp.tareas.tarea.dto.TareaRequest;
import pe.edu.upeu.bomerp.tareas.tarea.dto.TareaResponse;
import pe.edu.upeu.bomerp.tareas.tarea.service.TareaService;
import java.util.List;

@Tag(name = "Tareas")
@RestController
@RequestMapping("/api/v1/tareas")
@RequiredArgsConstructor
public class TareaController {
    private final TareaService tareaService;

    @Operation(summary = "Lista todas las tareas")
    @GetMapping
    public ResponseEntity<List<TareaResponse>> listar() {
        return ResponseEntity.ok(tareaService.listar());
    }

    @Operation(summary = "Consulta una tarea por id")
    @GetMapping("/{id}")
    public ResponseEntity<TareaResponse> obtener(@PathVariable Long id) {
        return ResponseEntity.ok(tareaService.obtener(id));
    }

    @Operation(summary = "Registra una tarea nueva")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TareaResponse crear(@Valid @RequestBody TareaRequest request) {
        return tareaService.crear(request);
    }

    @Operation(summary = "Elimina una tarea")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        tareaService.eliminar(id);
    }
}

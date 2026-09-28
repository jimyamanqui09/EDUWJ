package pe.edu.upeu.bomerp.calificaciones.registro.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upeu.bomerp.calificaciones.registro.dto.NotaRequest;
import pe.edu.upeu.bomerp.calificaciones.registro.dto.NotaResponse;
import pe.edu.upeu.bomerp.calificaciones.registro.service.NotaService;
import java.util.List;

@Tag(name = "Calificaciones - Notas")
@RestController
@RequestMapping("/api/v1/calificaciones/notas")
@RequiredArgsConstructor
public class NotaController {

    private final NotaService notaService;

    @Operation(summary = "Lista todas las notas registradas")
    @GetMapping
    public ResponseEntity<List<NotaResponse>> listar() {
        return ResponseEntity.ok(notaService.listar());
    }

    @Operation(summary = "Consulta una nota por ID")
    @GetMapping("/{id}")
    public ResponseEntity<NotaResponse> obtener(@PathVariable Long id) {
        return ResponseEntity.ok(notaService.obtener(id));
    }

    @Operation(summary = "Registra una nueva nota")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public NotaResponse registrar(@Valid @RequestBody NotaRequest request) {
        return notaService.registrar(request);
    }

    @Operation(summary = "Actualiza una nota existente")
    @PutMapping("/{id}")
    public ResponseEntity<NotaResponse> actualizar(@PathVariable Long id, @Valid @RequestBody NotaRequest request) {
        return ResponseEntity.ok(notaService.actualizar(id, request));
    }

    @Operation(summary = "Elimina una nota")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        notaService.eliminar(id);
    }

    @Operation(summary = "Lista notas por alumno")
    @GetMapping("/alumno/{alumnoId}")
    public ResponseEntity<List<NotaResponse>> listarPorAlumno(@PathVariable Long alumnoId) {
        return ResponseEntity.ok(notaService.listarPorAlumno(alumnoId));
    }

    @Operation(summary = "Lista notas por estado")
    @GetMapping("/estado/{estado}")
    public ResponseEntity<List<NotaResponse>> listarPorEstado(@PathVariable String estado) {
        return ResponseEntity.ok(notaService.listarPorEstado(estado));
    }

    @Operation(summary = "Cierra el acta de una nota")
    @PutMapping("/cerrar/{id}")
    @ResponseStatus(HttpStatus.OK)
    public NotaResponse cerrarActa(@PathVariable Long id) {
        return notaService.cerrarActa(id);
    }
}
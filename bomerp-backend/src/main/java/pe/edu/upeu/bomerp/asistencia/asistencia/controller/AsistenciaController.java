package pe.edu.upeu.bomerp.asistencia.asistencia.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upeu.bomerp.asistencia.asistencia.dto.AsistenciaBulkRequest;
import pe.edu.upeu.bomerp.asistencia.asistencia.dto.AsistenciaResponse;
import pe.edu.upeu.bomerp.asistencia.asistencia.service.AsistenciaService;
import java.util.List;

@Tag(name = "Asistencia")
@RestController
@RequestMapping("/api/v1/clases/{claseId}/asistencia")
@RequiredArgsConstructor
public class AsistenciaController {
    private final AsistenciaService asistenciaService;

    @Operation(summary = "Registra asistencia de multiples alumnos en una clase (bulk insert)")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<List<AsistenciaResponse>> crearBulk(
            @PathVariable Long claseId,
            @Valid @RequestBody AsistenciaBulkRequest request) {
        return ResponseEntity.ok(asistenciaService.crearBulk(claseId, request));
    }

    @Operation(summary = "Lista la asistencia registrada en una clase")
    @GetMapping
    public ResponseEntity<List<AsistenciaResponse>> listarPorClase(@PathVariable Long claseId) {
        return ResponseEntity.ok(asistenciaService.listarPorClase(claseId));
    }
}

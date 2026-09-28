package pe.edu.upeu.bomerp.matricula.alumno.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upeu.bomerp.matricula.alumno.dto.AlumnoRequest;
import pe.edu.upeu.bomerp.matricula.alumno.dto.AlumnoResponse;
import pe.edu.upeu.bomerp.matricula.alumno.service.AlumnoService;
import java.util.List;

@Tag(name = "Matrícula - Alumnos")
@RestController
@RequestMapping("/api/v1/matricula/alumnos")
@RequiredArgsConstructor
public class AlumnoController {

    private final AlumnoService alumnoService;

    @Operation(summary = "Lista todos los alumnos registrados")
    @GetMapping
    public ResponseEntity<List<AlumnoResponse>> listar() {
        return ResponseEntity.ok(alumnoService.listar());
    }

    @Operation(summary = "Consulta un alumno por ID")
    @GetMapping("/{id}")
    public ResponseEntity<AlumnoResponse> obtener(@PathVariable Long id) {
        return ResponseEntity.ok(alumnoService.obtenerPorId(id));
    }

    @Operation(summary = "Registra un nuevo alumno")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AlumnoResponse crear(@Valid @RequestBody AlumnoRequest request) {
        return alumnoService.crear(request);
    }

    @Operation(summary = "Actualiza un alumno existente")
    @PutMapping("/{id}")
    public ResponseEntity<AlumnoResponse> actualizar(@PathVariable Long id, @Valid @RequestBody AlumnoRequest request) {
        return ResponseEntity.ok(alumnoService.actualizar(id, request));
    }

    @Operation(summary = "Elimina un alumno")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        alumnoService.eliminar(id);
    }

    @Operation(summary = "Busca alumno por DNI")
    @GetMapping("/dni/{dni}")
    public ResponseEntity<AlumnoResponse> buscarPorDni(@PathVariable String dni) {
        return ResponseEntity.ok(alumnoService.buscarPorDni(dni));
    }
}
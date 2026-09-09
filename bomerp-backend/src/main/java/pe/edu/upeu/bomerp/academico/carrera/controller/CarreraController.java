package pe.edu.upeu.bomerp.academico.carrera.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upeu.bomerp.academico.carrera.dto.CarreraRequest;
import pe.edu.upeu.bomerp.academico.carrera.dto.CarreraResponse;
import pe.edu.upeu.bomerp.academico.carrera.service.CarreraService;
import java.util.List;

@Tag(name = "Carreras")
@RestController
@RequestMapping("/api/v1/carreras")
@RequiredArgsConstructor
public class CarreraController {
    private final CarreraService carreraService;

    @Operation(summary = "Lista todas las carreras")
    @GetMapping
    public ResponseEntity<List<CarreraResponse>> listar() {
        return ResponseEntity.ok(carreraService.listar());
    }

    @Operation(summary = "Consulta una carrera por id")
    @GetMapping("/{id}")
    public ResponseEntity<CarreraResponse> obtener(@PathVariable Long id) {
        return ResponseEntity.ok(carreraService.obtener(id));
    }

    @Operation(summary = "Registra una carrera nueva")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CarreraResponse crear(@Valid @RequestBody CarreraRequest request) {
        return carreraService.crear(request);
    }

    @Operation(summary = "Actualiza una carrera existente")
    @PutMapping("/{id}")
    public ResponseEntity<CarreraResponse> actualizar(@PathVariable Long id, @Valid @RequestBody CarreraRequest request) {
        return ResponseEntity.ok(carreraService.actualizar(id, request));
    }

    @Operation(summary = "Elimina una carrera")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        carreraService.eliminar(id);
    }
}

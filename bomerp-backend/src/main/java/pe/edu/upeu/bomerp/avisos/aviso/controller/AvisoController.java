package pe.edu.upeu.bomerp.avisos.aviso.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upeu.bomerp.avisos.aviso.dto.AvisoRequest;
import pe.edu.upeu.bomerp.avisos.aviso.dto.AvisoResponse;
import pe.edu.upeu.bomerp.avisos.aviso.service.AvisoService;
import java.util.List;

@Tag(name = "Avisos")
@RestController
@RequestMapping("/api/v1/avisos")
@RequiredArgsConstructor
public class AvisoController {
    private final AvisoService avisoService;

    @Operation(summary = "Lista los avisos institucionales ordenados del mas reciente al mas antiguo")
    @GetMapping
    public ResponseEntity<List<AvisoResponse>> listar() {
        return ResponseEntity.ok(avisoService.listar());
    }

    @Operation(summary = "Crea un aviso nuevo")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AvisoResponse crear(@Valid @RequestBody AvisoRequest request) {
        return avisoService.crear(request);
    }
}

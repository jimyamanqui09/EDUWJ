package pe.edu.upeu.bomerp.reporte.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upeu.bomerp.reporte.service.ReporteService;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/reportes")
@RequiredArgsConstructor
@Tag(name = "Reportes - Consulta General")
public class ReporteController {

    private final ReporteService reporteService;

    @Operation(summary = "Generar reporte de notas por alumno")
    @GetMapping("/notas/alumno/{alumnoId}")
    public ResponseEntity<Map<String, Object>> reporteNotasAlumno(@PathVariable Long alumnoId) {
        return ResponseEntity.ok(reporteService.generarReporteNotasPorAlumno(alumnoId));
    }

    @Operation(summary = "Generar reporte de asistencia por grado")
    @GetMapping("/asistencia/grado/{grado}")
    public ResponseEntity<Map<String, Object>> reporteAsistenciaGrado(@PathVariable String grado) {
        return ResponseEntity.ok(reporteService.generarReporteAsistenciaPorGrado(grado));
    }

    @Operation(summary = "Listar grados disponibles")
    @GetMapping("/grados")
    public ResponseEntity<List<String>> listarGrados() {
        return ResponseEntity.ok(reporteService.listarGrados());
    }

    @Operation(summary = "Listar secciones de un grado")
    @GetMapping("/grados/{grado}/secciones")
    public ResponseEntity<List<String>> listarSecciones(@PathVariable String grado) {
        return ResponseEntity.ok(reporteService.listarSecciones(grado));
    }
}
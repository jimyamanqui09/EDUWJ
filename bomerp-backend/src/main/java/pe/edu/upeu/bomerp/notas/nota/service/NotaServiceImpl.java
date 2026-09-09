package pe.edu.upeu.bomerp.notas.nota.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.upeu.bomerp.seguridad.usuario.dto.UsuarioResponse;
import pe.edu.upeu.bomerp.seguridad.usuario.service.UsuarioService;
import pe.edu.upeu.bomerp.tareas.entrega.dto.EntregaResponse;
import pe.edu.upeu.bomerp.tareas.entrega.service.EntregaService;
import pe.edu.upeu.bomerp.notas.nota.dto.NotaEstudianteResponse;
import pe.edu.upeu.bomerp.tareas.tarea.dto.TareaResponse;
import pe.edu.upeu.bomerp.tareas.tarea.service.TareaService;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class NotaServiceImpl implements NotaService {
    private final EntregaService entregaService;
    private final UsuarioService usuarioService;
    private final TareaService tareaService;

    @Override
    @Transactional(readOnly = true)
    public NotaEstudianteResponse notasEstudiante(Long usuarioId) {
        UsuarioResponse usuario = usuarioService.obtener(usuarioId);

        List<EntregaResponse> entregas = entregaService.listarPorUsuario(usuarioId);

        Map<Long, List<EntregaResponse>> porCurso = new LinkedHashMap<>();
        Map<Long, String> nombresCursos = new LinkedHashMap<>();

        for (EntregaResponse e : entregas) {
            TareaResponse tarea = tareaService.obtener(e.getTareaId());
            Long cursoId = tarea.getCursoId();
            porCurso.computeIfAbsent(cursoId, k -> new ArrayList<>()).add(e);
            nombresCursos.putIfAbsent(cursoId, tarea.getCursoNombre());
        }

        List<NotaEstudianteResponse.NotaCurso> cursos = new ArrayList<>();
        BigDecimal sumaPromedios = BigDecimal.ZERO;
        int countCursos = 0;

        for (Map.Entry<Long, List<EntregaResponse>> entry : porCurso.entrySet()) {
            Long cursoId = entry.getKey();
            List<EntregaResponse> entregasCurso = entry.getValue();

            List<NotaEstudianteResponse.NotaDetalle> notas = entregasCurso.stream()
                    .map(e -> {
                        TareaResponse tarea = tareaService.obtener(e.getTareaId());
                        return new NotaEstudianteResponse.NotaDetalle(
                                tarea.getId(),
                                tarea.getTitulo(),
                                tarea.getPeso(),
                                e.getCalificacion() != null ? e.getCalificacion() : BigDecimal.ZERO);
                    })
                    .toList();

            BigDecimal promedioCurso = notas.stream()
                    .map(NotaEstudianteResponse.NotaDetalle::getCalificacion)
                    .reduce(BigDecimal.ZERO, BigDecimal::add)
                    .divide(BigDecimal.valueOf(notas.size()), 2, RoundingMode.HALF_UP);

            cursos.add(new NotaEstudianteResponse.NotaCurso(cursoId, nombresCursos.get(cursoId), notas, promedioCurso));
            sumaPromedios = sumaPromedios.add(promedioCurso);
            countCursos++;
        }

        BigDecimal promedioGeneral = countCursos > 0
                ? sumaPromedios.divide(BigDecimal.valueOf(countCursos), 2, RoundingMode.HALF_UP)
                : BigDecimal.ZERO;

        return new NotaEstudianteResponse(usuarioId, usuario.getNombre(), cursos, promedioGeneral);
    }
}

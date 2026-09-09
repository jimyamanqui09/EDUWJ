package pe.edu.upeu.bomerp.academico.malla.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class MallaCurricularResponse {
    private Long carreraId;
    private String carreraNombre;
    private List<CicloMalla> ciclos;

    @Getter
    @AllArgsConstructor
    public static class CicloMalla {
        private Integer numero;
        private List<CursoMalla> cursos;
    }

    @Getter
    @AllArgsConstructor
    public static class CursoMalla {
        private Long id;
        private String codigo;
        private String nombre;
        private Integer creditos;
    }
}

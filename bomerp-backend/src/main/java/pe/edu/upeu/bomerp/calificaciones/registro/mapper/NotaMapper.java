package pe.edu.upeu.bomerp.calificaciones.registro.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pe.edu.upeu.bomerp.calificaciones.registro.dto.NotaRequest;
import pe.edu.upeu.bomerp.calificaciones.registro.dto.NotaResponse;
import pe.edu.upeu.bomerp.calificaciones.registro.entity.Nota;

@Mapper(componentModel = "spring")
public interface NotaMapper {
    @Mapping(target = "id", ignore = true)
    Nota toEntity(NotaRequest request);
    NotaResponse toResponse(Nota nota);
}
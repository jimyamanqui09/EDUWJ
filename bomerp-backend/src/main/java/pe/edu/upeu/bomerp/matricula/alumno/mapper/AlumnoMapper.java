package pe.edu.upeu.bomerp.matricula.alumno.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pe.edu.upeu.bomerp.matricula.alumno.dto.AlumnoRequest;
import pe.edu.upeu.bomerp.matricula.alumno.dto.AlumnoResponse;
import pe.edu.upeu.bomerp.matricula.alumno.entity.Alumno;

@Mapper(componentModel = "spring")
public interface AlumnoMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "apellidoMaterno", ignore = true)
    Alumno toEntity(AlumnoRequest request);
    AlumnoResponse toResponse(Alumno alumno);
}
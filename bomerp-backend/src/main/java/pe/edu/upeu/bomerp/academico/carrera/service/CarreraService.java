package pe.edu.upeu.bomerp.academico.carrera.service;

import pe.edu.upeu.bomerp.academico.carrera.dto.CarreraRequest;
import pe.edu.upeu.bomerp.academico.carrera.dto.CarreraResponse;
import java.util.List;

public interface CarreraService {
    List<CarreraResponse> listar();
    CarreraResponse obtener(Long id);
    CarreraResponse crear(CarreraRequest request);
    CarreraResponse actualizar(Long id, CarreraRequest request);
    void eliminar(Long id);
}

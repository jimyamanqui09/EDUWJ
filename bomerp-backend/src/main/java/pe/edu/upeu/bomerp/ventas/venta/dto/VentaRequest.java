package pe.edu.upeu.bomerp.ventas.venta.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class VentaRequest {

    @NotEmpty
    @Valid
    private List<DetalleVentaRequest> detalles;
}
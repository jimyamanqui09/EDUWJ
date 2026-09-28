package pe.edu.upeu.bomerp.ventas.venta.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import java.util.List;

@Getter
@AllArgsConstructor
public class VentaReporte {
    private final VentaAgregado agregado;
    private final List<VentaResumen> ventas;
}
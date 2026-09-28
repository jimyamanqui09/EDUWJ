package pe.edu.upeu.bomerp.ventas.venta.service;

import pe.edu.upeu.bomerp.ventas.venta.dto.VentaReporte;
import pe.edu.upeu.bomerp.ventas.venta.dto.VentaRequest;
import pe.edu.upeu.bomerp.ventas.venta.dto.VentaResponse;
import pe.edu.upeu.bomerp.ventas.venta.entity.EstadoVenta;
import java.time.LocalDateTime;
import java.util.List;

public interface VentaService {
    List<VentaResponse> buscar(EstadoVenta estado, LocalDateTime desde, LocalDateTime hasta,
                                String ordenarPor, String direccion);
    VentaResponse obtener(Long id);
    VentaResponse crear(VentaRequest request);
    VentaReporte reporte(EstadoVenta estado, LocalDateTime desde, LocalDateTime hasta);
}
package pe.edu.upeu.bomerp.ventas.venta.dto;

import lombok.Getter;
import java.math.BigDecimal;
import java.math.RoundingMode;

@Getter
public class VentaAgregado {
    private final long totalVentas;
    private final BigDecimal montoTotal;
    private final BigDecimal ticketPromedio;

    public VentaAgregado(long totalVentas, BigDecimal montoTotal) {
        this.totalVentas = totalVentas;
        this.montoTotal = montoTotal;
        this.ticketPromedio = totalVentas == 0
                ? BigDecimal.ZERO
                : montoTotal.divide(BigDecimal.valueOf(totalVentas), 2, RoundingMode.HALF_UP);
    }
}

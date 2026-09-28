package pe.edu.upeu.bomerp.ventas.venta.repository;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pe.edu.upeu.bomerp.ventas.venta.dto.VentaAgregado;
import pe.edu.upeu.bomerp.ventas.venta.dto.VentaResumen;
import pe.edu.upeu.bomerp.ventas.venta.entity.EstadoVenta;
import pe.edu.upeu.bomerp.ventas.venta.entity.Venta;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface VentaRepository extends JpaRepository<Venta, Long> {

    @Override
    @EntityGraph(attributePaths = "detalles")
    Optional<Venta> findById(Long id);

    @EntityGraph(attributePaths = "detalles")
    @Query("""
        SELECT v FROM Venta v
        WHERE (:estado IS NULL OR v.estado = :estado)
          AND (:desde IS NULL OR v.fecha >= :desde)
          AND (:hasta IS NULL OR v.fecha <= :hasta)
        """)
    List<Venta> buscar(@Param("estado") EstadoVenta estado,
                        @Param("desde") LocalDateTime desde,
                        @Param("hasta") LocalDateTime hasta,
                        Sort sort);

    @Query("""
        SELECT new pe.edu.upeu.bomerp.ventas.venta.dto.VentaResumen(
            v.id, v.fecha, v.estado, v.total, SIZE(v.detalles))
        FROM Venta v
        WHERE (:estado IS NULL OR v.estado = :estado)
          AND (:desde IS NULL OR v.fecha >= :desde)
          AND (:hasta IS NULL OR v.fecha <= :hasta)
        """)
    List<VentaResumen> buscarResumen(@Param("estado") EstadoVenta estado,
                                      @Param("desde") LocalDateTime desde,
                                      @Param("hasta") LocalDateTime hasta,
                                      Sort sort);

    @Query("""
        SELECT new pe.edu.upeu.bomerp.ventas.venta.dto.VentaAgregado(
            COUNT(v), COALESCE(SUM(v.total), 0BD))
        FROM Venta v
        WHERE (:estado IS NULL OR v.estado = :estado)
          AND (:desde IS NULL OR v.fecha >= :desde)
          AND (:hasta IS NULL OR v.fecha <= :hasta)
        """)
    VentaAgregado agregados(@Param("estado") EstadoVenta estado,
                             @Param("desde") LocalDateTime desde,
                             @Param("hasta") LocalDateTime hasta);
}

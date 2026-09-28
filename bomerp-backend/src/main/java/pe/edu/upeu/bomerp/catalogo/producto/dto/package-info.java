/**
 * ProductoResponse es el unico tipo de catalogo.producto que otros modulos
 * pueden recibir de vuelta - nunca la entidad Producto (ADR-002).
 */
@org.springframework.modulith.NamedInterface("producto-dto")
package pe.edu.upeu.bomerp.catalogo.producto.dto;
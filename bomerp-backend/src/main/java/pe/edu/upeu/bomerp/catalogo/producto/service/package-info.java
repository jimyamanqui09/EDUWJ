/**
 * ProductoService es la unica forma en que otros modulos (ventas, desde S4)
 * pueden leer o modificar productos - nunca accediendo a ProductoRepository
 * ni a la entidad Producto directamente (ADR-002).
 */
@org.springframework.modulith.NamedInterface("producto-service")
package pe.edu.upeu.bomerp.catalogo.producto.service;
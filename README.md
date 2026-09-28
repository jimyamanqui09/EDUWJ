# LP2

Carpeta preparada para artefactos del curso **Lenguaje de Programacion II**.

## Alcance

LP2 continúa el dominio comercial construido en POO y LP1: `Producto`,
`Categoria–Producto`, `Venta–DetalleVenta` y `Usuario`. El backend es un
único proyecto Spring Boot organizado por módulos de negocio mediante Spring
Modulith (ver [`docs/lp2/adr/`](../docs/lp2/adr/)), que también deja
preparado `Compra–DetalleCompra` como segundo flujo transaccional cuando el
equipo lo amplíe. En U1 desarrolla el backend REST; en U2 incorpora la SPA y
la seguridad JWT; en U3 lo optimiza, integra y estabiliza.

## Entregables Previstos

* Backend REST único (Spring Boot + Spring Modulith) con JPA y Oracle.
* Frontend SPA.
* DTO, servicios, repositorios y validaciones.
* Seguridad JWT y control de acceso.
* Integración, pruebas, optimización y monitoreo.

El proyecto backend vive en [`bomerp-backend/`](bomerp-backend/): ya tiene
el módulo `catalogo` de S1 (`Categoria`, `Producto`), el paquete
compartido `exception`/`filter` y `ModularityTests` en verde. Las
decisiones de arquitectura del workspace están en
[`docs/lp2/adr/`](../docs/lp2/adr/).

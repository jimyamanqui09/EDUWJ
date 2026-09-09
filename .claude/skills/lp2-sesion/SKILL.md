---
name: lp2-sesion
description: Implementa el incremento de código de una sesión de LP2 (S2 a S16) sobre el backend/frontend de BomERP, respetando las ADR del workspace. Usar cuando se pida "avanza la sesión SXX de LP2", "implementa S0X" o "continúa con la siguiente sesión de LP2".
---

# Sesión de LP2 (BomERP)

Este skill implementa **una sola sesión** del curso LP2 sobre el código de
`lp2/` (este directorio). No mezcla el trabajo de dos sesiones ni adelanta
alcance de sesiones futuras.

## Antes de escribir código

1. Identifica el número de sesión (S2, S3, ... S16). Si no se especifica,
   revisa `lp2/bomerp-backend` (y `lp2/frontend` desde S7) para detectar la última
   sesión completada y continúa con la siguiente.
2. Lee el alcance concreto de esa sesión en la fila correspondiente de
   `docs/lp2/index.md` (raíz del repo) — ahí está el detalle específico de
   BomERP (qué entidad, qué endpoint, qué flujo), más preciso que el `.md`
   genérico de la sesión.
3. Lee `docs/lp2/sesiones/S0X_*.md` para la rúbrica y los criterios de
   aceptación de esa sesión.
4. Lee las ADR en `docs/lp2/adr/` (raíz del repo; arquitectura vigente: un
   solo proyecto Maven, módulos Spring Modulith, sin Feign, un solo
   datasource, regla de dependencia servicio-a-servicio entre módulos).
5. Inspecciona el código actual en `lp2/bomerp-backend/src` (y `lp2/frontend` desde
   S7) para saber exactamente qué ya existe antes de agregar nada.

## Reglas al implementar

- Implementa **solo el incremento de la sesión pedida**, ni más ni menos.
  Ejemplos: en S2 se completa el CRUD de `Producto` (POST/PUT/DELETE,
  validaciones, excepción global, logs, pruebas) — no se toca `Categoria` ni
  `Venta` todavía.
- Si la sesión introduce un módulo de negocio nuevo (p. ej. `ventas` en S4,
  `seguridad` en S10), créalo como paquete directo bajo
  `pe.edu.upeu.bomerp` (no como módulo Maven aparte) para que Spring
  Modulith lo detecte automáticamente.
- Un módulo solo puede invocar el `Service` público de otro módulo; nunca su
  `Repository` ni su `Entity`. Si esto se viola, `ModularityTests` debe
  fallar — no lo silencies ni excluyas el chequeo. **`ModularityTests` no
  existe todavía** — créalo en S1 (`ApplicationModules.of(BomerpBackendApplication.class).verify()`).
- Usa Lombok (`@Getter/@Setter`), DTO como `record`,
  `@RequiredArgsConstructor`, capas
  controller/dto/entity/repository/service — **el módulo `catalogo` de S1
  todavía no existe**, créalo desde cero siguiendo esta convención, no hay
  código previo del que copiar el estilo.
- No pre-crees paquetes ni clases para sesiones futuras "por si acaso".
- Excepción a "no adelantar alcance": la eficiencia de base (transacciones
  `readOnly`, DTO en vez de entidades, fetch `LAZY`, logs) se aplica siempre,
  desde S1, en cualquier sesión — no es "alcance de S13". Lo que sí se
  reserva a S13-S14 es lo explícitamente de preparación para producción
  (Lazy Loading/Code Splitting del frontend, Redis, caché de navegador,
  paginación, monitoreo agregado, despliegue). Ver `../../../CLAUDE.md`
  sección "Eficiencia continua vs. preparación para producción".

## Verificación

Para levantar el ambiente completo (Oracle local, prerrequisitos, Swagger),
ver `bomerp-backend/README.md` — aquí solo el comando de verificación
rápida. Ejecuta, desde la raíz del repo (usa siempre el wrapper, nunca `mvn`
a secas: garantiza la misma versión de Maven sin depender de lo que tenga
instalado cada máquina):

```powershell
lp2/bomerp-backend/mvnw.cmd -f lp2/bomerp-backend/pom.xml clean test   # Windows
```

```bash
lp2/bomerp-backend/mvnw -f lp2/bomerp-backend/pom.xml clean test        # macOS/Linux
```

Esto compila y corre `ModularityTests` (no requiere Oracle) — si es S1 y ese
test todavía no existe, créalo primero. Si la sesión
agrega endpoints con lógica de negocio, agrega también pruebas de esa lógica
(unitarias de servicio o `@WebMvcTest` de controller) en la misma ejecución.
Si la sesión requiere Oracle real (para probar datos persistidos), indícalo
como paso manual — no asumas que hay una base de datos disponible en este
entorno.

## Al terminar

Resume en 3-5 líneas qué se implementó, qué endpoints/pantallas quedaron
nuevos o modificados, y qué queda pendiente para la siguiente sesión.

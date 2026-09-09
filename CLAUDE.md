# LP2 - BomERP backend/frontend

Workspace de código del curso **Lenguaje de Programación II** dentro del
monorepo `bomerp` (que también incluye `ads/`, `bd2/`, `docs/` para las
otras asignaturas del ciclo). Este archivo aplica solo a lo que está bajo
`lp2/`.

## Ambientes

Se les llama **"ambientes"**. Cada archivo de configuración específico a
un ambiente lleva ese ambiente como sufijo: `application-dev.yml`,
`compose-dev.yml`. Hoy solo existe el ambiente **DEV**; el de
**producción** (`application-prod.yml` y decisiones de despliegue) se
agrega recién en S13 — no antes, ver "Eficiencia continua vs. preparación
para producción" más abajo. Se usa `dev` (no `local`) para que el nombre
sea consistente con el resto de cursos del ciclo (DIST usa el mismo
sufijo) — como todavía no existe un ambiente de producción real, no hay
ambigüedad posible con "DEV" en esta etapa del curso.

**El ambiente DEV no usa `.env`.** Las credenciales van directo en texto
plano en `application-dev.yml` y `compose-dev.yml`, porque son valores
de laptop, no secretos. `.env`/`.env.example` se reservan para cuando
exista un ambiente de producción real (S13) con credenciales que sí deban
quedar fuera del repositorio — no se crean antes.

## Dónde está cada cosa

- **Código**: `lp2/bomerp-backend` (Spring Boot), `lp2/frontend` (SPA, arranca en
  S7). No hay carpeta `lp2/docker`: `lp2/bomerp-backend/compose-dev.yml` levanta
  únicamente el contenedor Oracle para desarrollo en laptop (de ahí el
  sufijo `-dev`, mismo criterio que `application-dev.yml`), con las
  mismas credenciales en texto plano que usa el backend. Java 21, Node.js
  y Angular 21 corren directamente en la máquina de desarrollo, sin
  contenedores.
- **Requisito del backend en DEV: solo Java 21.** No instalar Maven aparte
  — `lp2/bomerp-backend` trae Maven Wrapper (`mvnw`/`mvnw.cmd`), que descarga y
  cachea la versión exacta de Maven (3.9.9) sola. Cualquier comando Maven
  se ejecuta con el wrapper, nunca con `mvn` a secas (ver sección
  "Arquitectura del backend").
- **Documentación** (sílabo, sesiones, ADR, hoja de ruta): siempre en
  `docs/lp2/` en la raíz del repo, nunca dentro de `lp2/`. En concreto:
  - `docs/lp2/silabo_lp2_2026_2.md` — sílabo oficial (no editar salvo pedido explícito).
  - `docs/lp2/sesiones/S0X_*.md` — rúbrica y plantilla genérica por sesión.
  - `docs/lp2/index.md` — alcance **concreto** de BomERP por sesión (más específico que el `.md` de la sesión).
  - `docs/lp2/adr/` — decisiones de arquitectura (ADR-001: proyecto único vs. reactor Maven; ADR-002: Spring Modulith; ADR-003: Spring Boot 4.0.7, no 3.5.x ni 4.1.x — ver más abajo).
- **Skills**: `lp2/.claude/skills/` (p. ej. `lp2-sesion`, scoped a este directorio).

## Arquitectura del backend (ver ADR-001, ADR-002 y ADR-003)

- **Spring Boot 4.0.7 exacto** (no 3.5.x, no 4.1.x). El generador de Spring
  Initializr ya no ofrece versiones 3.x, y SpringDoc OpenAPI declara en
  `start.spring.io` requerir `>= 4.0.0 y < 4.1.0-M1` — con 4.1.x no aparece
  como dependencia disponible porque SpringDoc todavía no publicó una
  versión compatible con esa línea. No subir la versión del `pom.xml` sin
  antes confirmar que SpringDoc ya soporta la nueva línea (ver ADR-003).
- **Un solo proyecto Maven** (`lp2/bomerp-backend/pom.xml`), sin reactor
  multi-módulo. Empaquetado `jar` único, ejecutable con
  `.\mvnw.cmd spring-boot:run` (Windows) / `./mvnw spring-boot:run`
  (macOS/Linux). El proyecto trae Maven Wrapper: nunca uses `mvn` a secas,
  usa siempre `mvnw`/`mvnw.cmd` para que todos usen la misma versión de
  Maven (3.9.9) sin instalarla aparte.
- Paquete raíz `pe.edu.upeu.bomerp`. Cada módulo de negocio (`catalogo`,
  `ventas`, `seguridad`, ...) es un **paquete directo** bajo esa raíz, y es
  un módulo de aplicación de **Spring Modulith**.
- Dentro de cada módulo, organización por capas y por sub-recurso:
  `modulo/recurso/{controller,dto,entity,repository,service,mapper}`.
  **Estado real (2026-08-21): S1-S3 implementadas y verificadas con
  `mvnw test`** (13 tests en verde, incluidos `ModularityTests`). El módulo
  `catalogo` tiene `categoria` y `producto` con CRUD completo, asociados
  entre sí (`Producto.categoria`, `@ManyToOne` sobre la columna real
  `PRODUCTOS.ID_CATEGORIA`, que ya existía en Oracle desde S1/BD2). Los
  mappers de ambos son **MapStruct** (interfaces `@Mapper`), no clases
  manuales — el camino que S2 (3.8) presentó como "opcional" es el que
  terminó adoptado de verdad; no reescribir a mapeo manual sin motivo.
- Clases compartidas sin módulo propio (p. ej. `OpenApiConfig`) van sueltas
  en el paquete raíz `pe.edu.upeu.bomerp`, que Modulith trata como
  compartido/abierto a todos los módulos.
- **Regla de dependencia**: un módulo solo puede invocar el `Service`
  público de otro módulo; nunca su `Repository` ni su `Entity`. Se verifica
  automáticamente con `ModularityTests`
  (`lp2/bomerp-backend/src/test/java/pe/edu/upeu/bomerp/ModularityTests.java`)
  — **este archivo tampoco existe todavía**, créalo como parte de S1 (usa
  `ApplicationModules.of(BomerpBackendApplication.class).verify()`). Si se
  viola la regla, el test debe fallar; no se excluye ni se silencia.
- **No se crean paquetes de módulo vacíos "por si acaso"** (p. ej.
  `inventario`, `compras`, `seguridad` antes de S10). Se agregan recién
  cuando una sesión concreta les da contenido real.
- **`spring-modulith-starter-jpa` está removido a propósito desde S1** (el
  Initializr la agrega automáticamente al combinar Spring Modulith + Spring
  Data JPA): trae el registro de eventos de Modulith respaldado por JPA,
  que exige la tabla `event_publication` — con `ddl-auto: validate` y sin
  esa tabla en Oracle, la app no arranca. Los módulos de S1-S13 se
  comunican solo por servicios Java, no por eventos, así que no hace
  falta. **Cuando una sesión adopte comunicación por eventos entre módulos**
  (candidato: S14, auditoría/integración — ver ADR-002), hay que
  reincorporarla al `pom.xml`, crear la tabla `event_publication` en Oracle
  (migración de BD2 para esa sesión, no antes) y verificar con
  `mvnw clean test` + arranque real contra Oracle. No agregarla de vuelta
  "por si acaso" en una sesión anterior a la que realmente la necesite.
- Sin Feign ni llamadas HTTP internas entre módulos: un solo datasource,
  comunicación por servicios Java dentro de la misma JVM.

## Convenciones de código

- Java 21, Lombok (`@Getter`/`@Setter`/`@NoArgsConstructor` en entidades,
  `@RequiredArgsConstructor` en servicios/controladores).
- DTO de salida: `record` mientras el recurso es de solo lectura (ver
  `CategoriaResumen`); en cuanto necesita CRUD completo pasa a clase con
  Lombok (`@Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor`,
  ver `ProductoResponse`/`CategoriaResponse`) — un `record` no tiene
  setters ni encaja con `@Builder`.
- Entidades JPA con `schema = "BOM_<MODULO>"` en `@Table` (esquemas Oracle
  por módulo funcional, definidos en BD2).
- Interfaces de servicio (`XxxService`) + implementación (`XxxServiceImpl`),
  igual que `CategoriaService`/`CategoriaServiceImpl`.

## Eficiencia continua vs. preparación para producción (S13)

No confundir. Aplican criterios distintos:

- **Eficiencia de base, desde S1** (cuesta igual hacerla bien ahora que
  retrofit después):
  - `@Transactional(readOnly = true)` en toda consulta de solo lectura.
  - Nunca exponer entidades JPA; siempre DTO (evita N+1 por lazy loading
    disparado accidentalmente al serializar).
  - Fetch `LAZY` por defecto; si una consulta necesita el relacionado,
    traerlo explícito, no confiar en que Hibernate resuelva N+1 solo.
  - Logs estructurados desde que exista el recurso (ya exigido por el
    sílabo desde U1).
  - Consultas pensadas para volumen razonable, coordinando con BD2 (índices,
    afinación fina), aunque esa afinación profunda sea tema de BD2.

- **Preparación para producción, reservada a S13-S14** (no se adelanta
  aunque "suene a optimización", porque el sílabo la escatima a propósito o
  porque todavía no hay nada real que optimizar):
  - Lazy Loading / Code Splitting del frontend.
  - Caché de navegador y Redis.
  - Paginación de listados de alto volumen (los listados de U1 son
    intencionalmente sin paginar, ver S5).
  - Monitoreo/observabilidad agregada y buenas prácticas de despliegue.

Regla práctica: si la mejora cuesta lo mismo ahora que en S13, se hace
ahora. Si requiere que exista más aplicación de la que hay hoy para tener
sentido, se reserva para S13-S14.

## Cómo avanzar sesión a sesión

1. Usar el skill `lp2-sesion` (o seguir su mismo procedimiento a mano):
   leer `docs/lp2/index.md` + `docs/lp2/sesiones/S0X_*.md` +
   `docs/lp2/adr/`, revisar el código actual, implementar **solo** el
   incremento de esa sesión.
2. Verificar con `lp2/bomerp-backend/mvnw.cmd -f lp2/bomerp-backend/pom.xml test`
   (Windows) o `lp2/bomerp-backend/mvnw -f lp2/bomerp-backend/pom.xml test`
   (macOS/Linux) — compila y corre `ModularityTests`; no requiere Oracle
   para ese chequeo estructural.
3. Si la sesión implica una decisión de arquitectura (no solo una
   implementación dentro de lo ya decidido), usar modo plan antes de tocar
   código y registrar la decisión como una ADR nueva en `docs/lp2/adr/`.

## Fuera de alcance salvo pedido explícito

- No modificar `docs/lp2/sesiones/*`, `docs/lp2/index.md` ni los sílabos —
  son contenido pedagógico publicado del curso.
- No adelantar alcance de sesiones futuras (p. ej. no implementar seguridad
  JWT en S2).

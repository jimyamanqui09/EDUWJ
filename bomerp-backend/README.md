# bomerp-backend - Sistema Escolar

Backend único de LP2 (Sistema Escolar): Spring Boot 4.0.7 + Spring Modulith,
organizado en 4 módulos (2 transaccionales y 2 no transaccionales). Ver
[`../../CLAUDE.md`](../../CLAUDE.md) para las convenciones de arquitectura
completas y [`../../docs/lp2/adr/`](../../docs/lp2/adr/) para las decisiones
registradas (ADR-001 a ADR-003).

## Modulos del Sistema

### Modulos Transaccionales ( Escritura e integridad de datos )

1. **matricula** - Gestión de alumnos y cursos
   - `Alumno` entity con DNI, grado, sección
   - Validación de DNI único en base de datos
   - Operaciones: registrar, actualizar, eliminar, buscar por DNI
   - Endpoints: `GET /api/v1/matricula/alumnos`, `POST`, `PUT /{id}`, `DELETE /{id}`, `GET /dni/{dni}`

2. **calificaciones** - Registro y cierre de notas
   - `Nota` entity con validación rango 0-20, ponderación 0-100
   - Cálculo de promedios y cierre de acta
   - Operaciones: registrar, actualizar, eliminar, cerrar acta, listar por alumno/estado
   - Endpoints: `GET /api/v1/calificaciones/notas`, `POST`, `PUT /{id}`, `DELETE /{id}`, `GET /alumno/{alumnoId}`, `GET /estado/{estado}`, `PUT /cerrar/{id}`

### Modulos No Transaccionales ( Lectura, consulta y autenticación )

3. **auth** - Autenticación y acceso
   - `Usuario` entity con username, password, rol, estado
   - Validación de credenciales (SELECT, comparar hash, generar token)
   - Operaciones: login, verificar credenciales, exists, tiene rol
   - Endpoints: `POST /api/v1/auth/login`, `GET /verificar/{username}/{password}`, `GET /exists/{username}`, `GET /rol/{username}/{rol}`

4. **reportes** - Consultas y generación de reportes
   - Reportes de notas y asistencia por grado
   - Listado de grados y secciones
   - Operaciones de solo lectura (vistas, reportes)
   - Endpoints: `GET /api/v1/reportes/notas/alumno/{alumnoId}`, `GET /asistencia/grado/{grado}`, `GET /grados`, `GET /grados/{grado}/secciones`

## Estructura del Proyecto

- Paquete raíz: `pe.edu.upeu.bomerp`
- Cada módulo sigue la organización: `modulo/recurso/{controller,dto,entity,repository,service,mapper}`
- Módulos transaccionales: `matricula/alumno`, `calificaciones/registro`
- Módulos no transaccionales: `auth/seguridad`, `reportes`
- MapStruct para mapeo de DTOs
- Lombok para entidades y constructores
- ModularityTests: verificación automática de reglas de dependencia entre módulos

## Estado Actual

- ✅ 4 módulos escolares implementados (2 transaccionales + 2 no transaccionales)
- ✅ Módulos heredados: `catalogo` (S1: Categoria, Producto), `ventas` (S3+: Venta)
- ✅ Compilación exitosa (`./mvnw clean compile`)
- ✅ ModularityTests pasan (2 tests - reglas de dependencia Spring Modulith)
- ✅ 13 tests en total
- ✅ Estructura por módulo: `modulo/recurso/{controller,dto,entity,repository,service,mapper}`

## Prerrequisitos

- **Java 21** — único requisito local. No instalar Maven aparte: el
  proyecto trae Maven Wrapper (`mvnw` / `mvnw.cmd`), que descarga y cachea
  la versión exacta de Maven (3.9.9) sola.
- **Docker** (para el contenedor Oracle de DEV — ver más abajo).

## Levantar el ambiente DEV

1. Levantar la base de datos Oracle:

   ```bash
   docker compose -f compose-dev.yml up -d
   ```

   Crea el contenedor `bomerp-oracle` (`gvenzl/oracle-free:23-slim`), puerto
   `1521`, con el usuario de aplicación `BOMERP_APP` (contraseña `123456`,
   valor de laptop en texto plano — no es secreto real, ver `../../CLAUDE.md`
   sección "Ambientes").

2. Ejecutar la aplicación:

   ```bash
   # Windows
   .\mvnw.cmd spring-boot:run

   # macOS/Linux
   ./mvnw spring-boot:run
   ```

   Usa el perfil `dev` (`application-dev.yml`) por defecto — conecta a
   `jdbc:oracle:thin:@localhost:1521/FREEPDB1`.

## Verificar

```bash
# Windows
.\mvnw.cmd clean test

# macOS/Linux
./mvnw clean test
```

Compila y corre las pruebas, incluida `ModularityTests` (verifica los
límite entre módulos de Spring Modulith) — no requiere Oracle levantado
para ese chequeo estructural. Las pruebas que sí requieren
datos reales necesitan el contenedor Oracle del paso 1.

## Documentación de la API

Con la aplicación corriendo, Swagger UI queda disponible en
`http://localhost:8080/swagger-ui.html` (vía `springdoc-openapi`).

## Avanzar por sesión

Para implementar el incremento de una sesión específica (S1 a S16), usa el
skill `lp2-sesion` (`../../.claude/skills/lp2-sesion/SKILL.md`) en vez de
codificar directamente — aplica solo el alcance de esa sesión y verifica
con los comandos de esta misma sección.
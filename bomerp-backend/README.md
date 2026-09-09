# bomerp-backend

Backend REST de Portal Estudiantil: Spring Boot 4.0.7 + Spring Modulith, 8 modulos de negocio, esquemas Oracle independientes.

## Inicio rapido

```bash
# 1. Oracle (auto-crea schemas, tablas, FKs, grants)
docker compose -f compose-dev.yml up -d

# 2. Spring Boot
./mvnw spring-boot:run
```

Swagger: http://localhost:8080/swagger-ui.html

## Prerrequisitos

- Java 21
- Docker

## Estructura de modulos

| Modulo | Schema Oracle | Tablas | Transaccional |
|--------|--------------|--------|---------------|
| seguridad | BOM_SEGURIDAD | USUARIOS | Si |
| academico | BOM_ACADEMICO | CARRERAS, CICLOS, CURSOS, AULAS | No |
| horarios | BOM_HORARIOS | HORARIOS | No |
| matriculas | BOM_MATRICULAS | MATRICULAS | Si |
| tareas | BOM_TAREAS | TAREAS, ENTREGAS | Si |
| asistencia | BOM_ASISTENCIA | CLASES, ASISTENCIA | Si |
| avisos | BOM_AVISOS | AVISOS | No |
| notas | (sin schema) | (consulta) | No |

## Docker

`compose-dev.yml` usa `Dockerfile.oracle` que construye una imagen Oracle con los init scripts de `src/main/resources/sql/init/`. En la primera corrida, Oracle ejecuta los 9 scripts SQL que crean todo el esquema de base de datos.

Para reiniciar Oracle desde cero:
```bash
docker compose -f compose-dev.yml down -v
docker compose -f compose-dev.yml up -d --build
```

## Tests

```bash
./mvnw clean test
```

- `ModularityTests` — verifica que los modulos respeten las reglas de dependencia de Spring Modulith
- `BomerpBackendApplicationTests` — verifica que la app arranca correctamente

## API

54 endpoints REST documentados via SpringDoc OpenAPI. Ver seccion API del README raiz para el listado completo.

## Configuracion

- `application-dev.yml` — perfil DEV (Oracle en localhost:1521)
- `application-test.yml` — perfil test (H2 en memoria)
- `pom.xml` — Spring Boot 4.0.7, Java 21, Spring Modulith 2.0.7, MapStruct, Lombok

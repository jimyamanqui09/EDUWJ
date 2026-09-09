# Portal Estudiantil

Backend REST del curso **Lenguaje de Programacion II** — un sistema de gestion estudiantil construido con Spring Boot 4.0.7, Spring Modulith y Oracle, organizado en **8 modulos** con esquemas Oracle independientes.

## Arquitectura

```
bomerp-backend/
├── src/main/java/pe/edu/upeu/bomerp/
│   ├── seguridad/       → BOM_SEGURIDAD    (USUARIOS)
│   ├── academico/       → BOM_ACADEMICO    (CARRERAS, CICLOS, CURSOS, AULAS)
│   ├── horarios/        → BOM_HORARIOS     (HORARIOS)
│   ├── matriculas/      → BOM_MATRICULAS   (MATRICULAS)
│   ├── tareas/          → BOM_TAREAS       (TAREAS, ENTREGAS)
│   ├── asistencia/      → BOM_ASISTENCIA   (CLASES, ASISTENCIA)
│   ├── avisos/          → BOM_AVISOS       (AVISOS)
│   └── notas/           → (consulta, sin schema propio)
├── src/main/resources/sql/init/   → 9 scripts SQL para Oracle
├── Dockerfile.oracle              → imagen Oracle con init scripts
├── compose-dev.yml                → Docker Compose para DEV
└── pom.xml                        → Spring Boot 4.0.7 + Modulith
```

Cada modulo es un **Application Module** de Spring Modulith. La dependencia entre modulos solo se permite via interfaces de servicio, nunca via Repository ni Entity.

## Prerrequisitos

- **Java 21** (no instalar Maven — el proyecto trae Maven Wrapper)
- **Docker** (para Oracle)

## Iniciar en 2 comandos

```bash
# Comando 1: Oracle + esquemas + tablas + FKs + grants
docker compose -f compose-dev.yml up -d

# Comando 2: Spring Boot
./mvnw spring-boot:run
```

En Windows:
```bash
docker compose -f compose-dev.yml up -d
.\mvnw.cmd spring-boot:run
```

Oracle crea automaticamente 7 schemas, 12 tablas, 14 foreign keys y 12 grants a `BOMERP_APP` via los scripts en `src/main/resources/sql/init/`.

## Verificar

```bash
./mvnw clean test
```

Compila y ejecuta `ModularityTests` (verifica limites entre modulos) + `BomerpBackendApplicationTests` (verifica conexion a BD). No requiere Oracle levantado.

## API REST

Swagger UI: **http://localhost:8080/swagger-ui.html**

| # | Metodo | Endpoint | Modulo | Descripcion |
|---|--------|----------|--------|-------------|
| 1 | POST | `/api/v1/usuarios` | seguridad | Crear usuario |
| 2 | POST | `/api/v1/matriculas` | matriculas | Matricular estudiante |
| 3 | POST | `/api/v1/tareas/{id}/entregas` | tareas | Entregar tarea |
| 4 | POST | `/api/v1/clases/{id}/asistencia` | asistencia | Registrar asistencia |
| 5 | GET | `/api/v1/horarios/estudiante/{id}/horario` | horarios | Horario del estudiante |
| 6 | GET | `/api/v1/matriculas/curso/{id}/matriculados` | matriculas | Listar matriculados |
| 7 | GET | `/api/v1/estudiantes/{id}/notas` | notas | Notas del estudiante |
| 8 | GET | `/api/v1/cursos/carrera/{id}/malla` | academico | Malla curricular |

### Todos los endpoints

**Seguridad** — `/api/v1/usuarios` (GET, GET/{id}, POST, PUT/{id}, DELETE/{id})

**Academico**
- `/api/v1/carreras` (GET, GET/{id}, POST, PUT/{id}, DELETE/{id})
- `/api/v1/cursos` (GET, GET/{id}, POST, DELETE/{id}, GET/carrera/{id}, GET/carrera/{id}/malla)
- `/api/v1/aulas` (GET, GET/{id}, POST, DELETE/{id})

**Horarios** — `/api/v1/horarios` (GET/curso/{id}, GET/estudiante/{id}/horario, POST, DELETE/{id})

**Matriculas** — `/api/v1/matriculas` (GET, POST, GET/curso/{id}/matriculados, GET/estudiante/{id})

**Tareas** — `/api/v1/tareas` (GET, GET/{id}, POST, DELETE/{id}) + `/api/v1/tareas/{id}/entregas` (GET, POST)

**Clases** — `/api/v1/clases` (GET/horario/{id}, POST) + `/api/v1/clases/{id}/asistencia` (GET, POST)

**Avisos** — `/api/v1/avisos` (GET, POST)

**Notas** — `/api/v1/estudiantes/{id}/notas` (GET)

## Dependencias entre modulos

```
seguridad  ← (ninguna)
academico  ← (ninguna)
horarios   ← academico + matriculas
matriculas ← seguridad + academico
tareas     ← seguridad + academico
asistencia ← seguridad + horarios
avisos     ← seguridad
notas      ← seguridad + tareas
```

## Entorno DEV

- Oracle: `gvenzl/oracle-free:23-slim` en puerto `1521`
- Usuario app: `BOMERP_APP` / `123456`
- Schema: `FREEPDB1`
- `ddl-auto: validate` (Hibernate solo valida, no crea tablas)
- Credenciales en texto plano (ambiente de laptop, no secretos)

# LP2 - Sistema Escolar

Carpeta preparada para artefactos del curso **Lenguaje de Programacion II**.
Proyecto adaptado para un sistema escolar con 4 módulos (2 transaccionales y 2 no transaccionales).

## Modulos del Sistema

### Modulos Transaccionales ( Escritura e integridad de datos )

1. **matricula** - Gestión de alumnos y cursos
   - Registro de estudiantes con validación de DNI único
   - Asignación de grado y sección
   - Operaciones ACID: crear alumno + validar vacante + cronograma

2. **calificaciones** - Registro y cierre de notas
   - Insertar notas con validación rango 0-20
   - Cálculo de ponderaciones y promedios
   - Cierre de acta bimestral/trimestral

### Modulos No Transaccionales ( Lectura, consulta y autenticación )

3. **auth** - Autenticación y acceso
   - Inicio de sesión y verificación de credenciales
   - Validación de usuario y rol (ADMIN, PROFESOR, ALUMNO)
   - Operación de solo lectura (SELECT, comparar hash, generar token)

4. **reportes** - Consultas y generación de reportes
   - Consultas de historial académico
   - Reportes de notas y asistencia por grado
   - Vistas y exportación de datos sin modificar base de datos

## Estructura del Proyecto

Backend: Spring Boot 4.0.7 + Spring Modulith 2.0.7
- Paquete raíz: `pe.edu.upeu.bomerp`
- Cada módulo sigue la organización: `modulo/recurso/{controller,dto,entity,repository,service,mapper}`
- MapStruct para mapeo de DTOs
- Lombok para entidades y constructores
- ModularityTests: verificación automática de reglas de dependencia

## Estado Actual

- ✅ 4 módulos implementados y funcionando
- ✅ Compilación exitosa (`./mvnw clean compile`)
- ✅ ModularityTests pasan (2 tests - reglas de dependencia)
- ✅ 13 tests en total (7 controller tests + 2 modularity)
- ✅ Estructura por módulo: `modulo/recurso/{controller,dto,entity,repository,service,mapper}`

## Modulos Existentes (S1-S3)

El proyecto incluye módulos heredados de Sesiones anteriores:
- `catalogo` (S1): `Categoria`, `Producto` - CRUD completo
- `ventas` (S3+): `Venta` - Ventas con detalles

Las nuevas adaptaciones escolares son módulos adicionales que complementan el sistema.

## Tecnologías

- Java 21
- Spring Boot 4.0.7
- Spring Modulith 2.0.7
- MapStruct 1.6.3
- Lombok
- Oracle JDBC Driver
- Maven Wrapper (mvnw/mvnw.cmd)
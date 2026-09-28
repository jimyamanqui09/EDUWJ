# BomerpFrontend

Este proyecto fue generado con [Angular CLI](https://github.com/angular/angular-cli) versión 22.1.8.

## Requisitos previos

**Node.js 22.22.3+** (o 24.15.0+ / 26.0.0+). Angular 22 **no funciona** con Node 20.

### Instalar Node.js correcto con nvm (recomendado)

```bash
# Instalar nvm si no lo tienes
curl -o- https://raw.githubusercontent.com/nvm-sh/nvm/v0.40.1/install.sh | bash
# Reiniciar terminal o ejecutar:
source ~/.bashrc

# Instalar y usar Node.js 22 LTS
nvm install 22
nvm use 22
nvm alias default 22
```

Verificar versión:
```bash
node --version   # debe mostrar v22.x.x
npm --version
```

---

## Instalación y ejecución

```bash
# 1. Instalar dependencias (solo la primera vez o si cambió package.json)
npm install

# 2. Iniciar servidor de desarrollo
npm start
```

La app estará en **http://localhost:4200**. Se recarga automáticamente al cambiar código.

---

## Comandos útiles

| Comando | Descripción |
|---------|-------------|
| `npm start` | Levanta `ng serve` (desarrollo) |
| `npm run build` | Compila para producción (`dist/`) |
| `npm run watch` | Compila en modo watch (desarrollo) |
| `npm test` | Ejecuta tests unitarios con Vitest |

### Generar componentes/servicios/etc.
```bash
npx ng generate component nombre-componente
npx ng generate service nombre-servicio
# Ver todos los esquemáticos:
npx ng generate --help
```

---

## Build para producción

```bash
npm run build
```

Genera `dist/bomerp-frontend/browser/` (HTML/JS/CSS estáticos, listos para deploy). **No se sube `node_modules`**.

### GitHub Pages (ejemplo)
- Sube solo `dist/bomerp-frontend/browser/`
- En `environment.ts` apunta la URL del API al backend real (no `localhost`)
- Si no está en raíz del dominio, usa `--base-href` y copia `index.html` a `404.html` para que el router no falle al recargar

---

## Estructura principal

```
src/
├── app/
│   ├── core/           # Servicios, interceptores, layout, inicio
│   ├── features/       # Módulos de negocio (catálogo, ventas, etc.)
│   ├── app.routes.ts   # Rutas principales
│   └── app.config.ts   # Configuración de la app (providers, etc.)
├── environments/       # environment.ts (desarrollo)
└── main.ts             # Bootstrap
```

---

## Tests

```bash
# Unitarios (Vitest)
npm test
```

> Nota: Angular CLI no trae framework e2e por defecto. Se puede agregar Cypress, Playwright, etc. según necesidad.

---

## Notas importantes

- **Nunca uses `ng` global** (`sudo npm i -g @angular/cli`). Usa `npx ng` o los scripts de `package.json` (`npm start`, `npm run build`, etc.) para que todos usen la misma versión local.
- **Variables de entorno**: solo `environment.ts` (desarrollo). En producción se reemplaza en el build.
- **Interceptores**: `trace-id-interceptor.ts` agrega `X-Trace-Id` a cada petición para trazabilidad.

---

## Recursos

- [Angular CLI Reference](https://angular.dev/tools/cli)
- [Angular 22 Docs](https://angular.dev/)
- [RxJS 7.8](https://rxjs.dev/)
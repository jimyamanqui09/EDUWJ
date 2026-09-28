# bomerp-frontend-react

Anexo del Proyecto Integrador: la misma arquitectura de LP2 S07 (Angular, sesión oficial), resuelta en **React** — para el equipo que eligió este stack para su propio proyecto. Guía completa: [`docs/proyecto-integrador/anexos/S07_Creacion_Arquitectura_SPA_React.md`](../../../docs/proyecto-integrador/anexos/S07_Creacion_Arquitectura_SPA_React.md).

**No reemplaza** [`lp2/bomerp-frontend`](../../bomerp-frontend) (Angular, la sesión oficial de LP2) — es una ruta alternativa, no una entrega paralela.

## Prerrequisitos

- **Node.js LTS** (incluye `npm`). No hace falta instalar nada más de forma global.
- [`lp2/bomerp-backend`](../../bomerp-backend) corriendo en `http://localhost:8080`, con CORS habilitado para `http://localhost:5173` (S5).

## Levantar el ambiente DEV

1. Backend, en otra terminal:

   ```powershell
   cd ../../bomerp-backend
   .\mvnw.cmd spring-boot:run
   ```

2. Variables de ambiente — crea `.env` en esta carpeta (no se versiona, cada quien lo crea localmente):

   ```text
   VITE_API_BASE_URL=http://localhost:8080
   ```

3. Frontend:

   ```bash
   npm install
   npm run dev
   ```

   Abre `http://localhost:5173`.

## Build de producción

```bash
npm install    # crea node_modules localmente — nunca se sube a git ni a producción
npm run build  # genera dist/, con el código ya empaquetado y minificado
```

Lo único que se despliega es `dist/` — son archivos estáticos (HTML/JS/CSS), sin `node_modules`. Para publicarlo en GitHub Pages, sube **solo** `dist/` (GitHub Actions puede correr estos mismos dos comandos en una máquina temporal y publicar el resultado automáticamente en cada `push`). Antes de buildear para producción, cambia `VITE_API_BASE_URL` en `.env` a la URL pública real del backend — no `localhost`. Si el sitio no vive en la raíz del dominio (`usuario.github.io/repo/`), fija también el `base` en `vite.config.ts`, y copia `dist/index.html` a `dist/404.html` para que las rutas de React Router (`/catalogo/categorias`) no den 404 al recargar la página.

## Estructura

```text
src/
├── core/                          # AppLayout, api.ts (fetch + trazabilidad X-Trace-ID)
└── features/
    └── catalogo/
        └── categoria/              # modelo, servicio y vistas del CRUD de Categoria
```

## Stack

- React 19 + TypeScript, Vite
- React Router (rutas anidadas, `<Outlet />`, carga perezosa con `React.lazy`)
- `fetch` nativo, sin Axios — ver `src/core/api.ts`
- Sin gestor de estado global (Redux, Zustand): no hace falta para este CRUD

## Estado actual

CRUD completo de `Categoria` (listar, crear, editar, eliminar), navegación por layout con sidebar, validación de formulario (`nombre` obligatorio y máximo 80 caracteres, `descripcion` máximo 200).

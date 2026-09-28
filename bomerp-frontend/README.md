# BomerpFrontend

This project was generated using [Angular CLI](https://github.com/angular/angular-cli) version 22.1.8.

## Development server

To start a local development server, run:

```bash
ng serve
```

Once the server is running, open your browser and navigate to `http://localhost:4200/`. The application will automatically reload whenever you modify any of the source files.

## Code scaffolding

Angular CLI includes powerful code scaffolding tools. To generate a new component, run:

```bash
ng generate component component-name
```

For a complete list of available schematics (such as `components`, `directives`, or `pipes`), run:

```bash
ng generate --help
```

## Building

To build the project run:

```bash
ng build
```

This will compile your project and store the build artifacts in the `dist/` directory. By default, the production build optimizes your application for performance and speed.

### Deploying to production

```bash
npm install   # creates node_modules locally — never committed, never deployed
ng build      # generates dist/bomerp-frontend/browser/, already bundled and minified
```

Only `dist/` gets deployed — static HTML/JS/CSS, no `node_modules`. To publish on GitHub Pages, upload **only** `dist/bomerp-frontend/browser/` (a GitHub Actions workflow can run these same two commands on a temporary machine and publish the result automatically on every `push`). Before building for production, point the API URL in `environment.ts` to the real public backend — not `localhost`. If the site doesn't live at the domain root (`user.github.io/repo/`), set `--base-href` accordingly, and copy `dist/bomerp-frontend/browser/index.html` to `404.html` so Angular Router's routes don't 404 on a page reload.

## Running unit tests

To execute unit tests with the [Vitest](https://vitest.dev/) test runner, use the following command:

```bash
ng test
```

## Running end-to-end tests

For end-to-end (e2e) testing, run:

```bash
ng e2e
```

Angular CLI does not come with an end-to-end testing framework by default. You can choose one that suits your needs.

## Additional Resources

For more information on using the Angular CLI, including detailed command references, visit the [Angular CLI Overview and Command Reference](https://angular.dev/tools/cli) page.

import { Routes } from '@angular/router';
import { authGuard } from './core/guards/auth.guard';

export const routes: Routes = [
  {
    path: 'login',
    loadComponent: () => import('./features/auth/login/login').then((m) => m.Login),
  },
  {
    path: '',
    loadComponent: () => import('./core/layout/layout').then((m) => m.Layout),
    canActivate: [authGuard],
    children: [
      {
        path: '',
        redirectTo: 'alumnos',
        pathMatch: 'full',
      },
      {
        path: 'alumnos',
        loadComponent: () => import('./features/matricula/alumnos/alumno-list').then((m) => m.AlumnoListComponent),
      },
      {
        path: 'notas',
        loadComponent: () => import('./features/calificaciones/notas/nota-list').then((m) => m.NotaListComponent),
      },
    ],
  },
  {
    path: '**',
    redirectTo: 'login',
  },
];
import { createRouter, createWebHistory } from 'vue-router'

const router = createRouter({
  history: createWebHistory(),
  routes: [
    {
      path: '/',
      component: () => import('../layouts/AppLayout.vue'),
      children: [
        {
          path: '',
          component: () => import('../views/InicioView.vue'),
        },
        {
          path: 'catalogo/categorias',
          component: () => import('../features/catalogo/categoria/CategoriaListView.vue'),
        },
        {
          path: 'catalogo/categorias/nueva',
          component: () => import('../features/catalogo/categoria/CategoriaFormView.vue'),
        },
        {
          path: 'catalogo/categorias/:id/editar',
          component: () => import('../features/catalogo/categoria/CategoriaFormView.vue'),
        },
      ],
    },
  ],
})

export default router

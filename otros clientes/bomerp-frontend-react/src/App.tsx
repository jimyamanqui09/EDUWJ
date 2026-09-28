import { Suspense, lazy } from 'react'
import { Routes, Route } from 'react-router-dom'

const AppLayout = lazy(() => import('./core/AppLayout'))
const InicioView = lazy(() => import('./core/InicioView'))
const CategoriaListView = lazy(() => import('./features/catalogo/categoria/CategoriaListView'))
const CategoriaFormView = lazy(() => import('./features/catalogo/categoria/CategoriaFormView'))

export default function App() {
  return (
    <Suspense fallback={null}>
      <Routes>
        <Route path="/" element={<AppLayout />}>
          <Route index element={<InicioView />} />
          <Route path="catalogo/categorias" element={<CategoriaListView />} />
          <Route path="catalogo/categorias/nueva" element={<CategoriaFormView />} />
          <Route path="catalogo/categorias/:id/editar" element={<CategoriaFormView />} />
        </Route>
      </Routes>
    </Suspense>
  )
}

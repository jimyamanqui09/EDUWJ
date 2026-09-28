import { useState, useEffect, useCallback } from 'react'
import { Link } from 'react-router-dom'
import { listar, eliminar } from './categoria-service'
import type { Categoria } from './categoria.model'

export default function CategoriaListView() {
  const [categorias, setCategorias] = useState<Categoria[]>([])
  const [error, setError] = useState<string | null>(null)
  const [loading, setLoading] = useState(false)

  const cargar = useCallback(() => {
    setLoading(true)
    setError(null)
    listar()
      .then(setCategorias)
      .catch(() => setError('No se pudo cargar la lista de categorías.'))
      .finally(() => setLoading(false))
  }, [])

  useEffect(() => {
    cargar()
  }, [cargar])

  function onEliminar(id: number) {
    if (!confirm(`¿Está seguro de eliminar la categoría ${id}?`)) return

    eliminar(id)
      .then(cargar)
      .catch(() => setError('No se pudo eliminar la categoría. Puede tener productos asociados.'))
  }

  return (
    <>
      {loading && <p>Cargando categorías...</p>}
      {error && <p className="error">{error}</p>}

      <Link to="/catalogo/categorias/nueva">Nueva categoría</Link>

      <table>
        <thead>
          <tr>
            <th>Nombre</th>
            <th>Descripción</th>
            <th></th>
          </tr>
        </thead>
        <tbody>
          {categorias.map((categoria) => (
            <tr key={categoria.id}>
              <td>{categoria.nombre}</td>
              <td>{categoria.descripcion}</td>
              <td>
                <Link to={`/catalogo/categorias/${categoria.id}/editar`}>Editar</Link>
                <button onClick={() => onEliminar(categoria.id!)}>Eliminar</button>
              </td>
            </tr>
          ))}
          {!loading && !error && categorias.length === 0 && (
            <tr>
              <td colSpan={3}>No hay categorías registradas.</td>
            </tr>
          )}
        </tbody>
      </table>
    </>
  )
}

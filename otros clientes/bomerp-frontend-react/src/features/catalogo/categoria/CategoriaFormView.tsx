import { useState, useEffect, useMemo } from 'react'
import { useParams, useNavigate } from 'react-router-dom'
import { crear, actualizar, obtener } from './categoria-service'

const LIMITES = { nombre: 80, descripcion: 200 } as const
const REQUERIDO = { nombre: true, descripcion: false } as const

function mensajeValidacion(campo: 'nombre' | 'descripcion', valor: string, tocado: boolean): string {
  if (!tocado) return ''
  const limpio = valor.trim()
  if (REQUERIDO[campo] && !limpio) return 'Este campo es obligatorio.'
  if (limpio.length > LIMITES[campo]) return `Máximo ${LIMITES[campo]} caracteres.`
  return ''
}

export default function CategoriaFormView() {
  const { id: idParam } = useParams()
  const navigate = useNavigate()
  const id = idParam ? Number(idParam) : null

  const [nombre, setNombre] = useState('')
  const [descripcion, setDescripcion] = useState('')
  const [tocado, setTocado] = useState({ nombre: false, descripcion: false })
  const [error, setError] = useState<string | null>(null)
  const [loading, setLoading] = useState(false)
  const [errorCarga, setErrorCarga] = useState(false)

  useEffect(() => {
    if (!id) return

    setLoading(true)
    obtener(id)
      .then((categoria) => {
        setNombre(categoria.nombre)
        setDescripcion(categoria.descripcion ?? '')
      })
      .catch(() => {
        setErrorCarga(true)
        setError('No se pudo cargar la categoría.')
      })
      .finally(() => setLoading(false))
  }, [id])

  const mensajeNombre = useMemo(
    () => mensajeValidacion('nombre', nombre, tocado.nombre),
    [nombre, tocado.nombre],
  )
  const mensajeDescripcion = useMemo(
    () => mensajeValidacion('descripcion', descripcion, tocado.descripcion),
    [descripcion, tocado.descripcion],
  )

  function guardar(evento: React.FormEvent) {
    evento.preventDefault()
    if (loading || errorCarga) return

    setError(null)
    setTocado({ nombre: true, descripcion: true })
    const nombreLimpio = nombre.trim()
    setNombre(nombreLimpio)

    const nombreInvalido = mensajeValidacion('nombre', nombreLimpio, true)
    const descripcionInvalida = mensajeValidacion('descripcion', descripcion, true)
    if (nombreInvalido || descripcionInvalida) return

    const valor = { nombre: nombreLimpio, descripcion }
    const peticion = id ? actualizar(id, valor) : crear(valor)

    setLoading(true)
    peticion
      .then(() => navigate('/catalogo/categorias'))
      .catch(() => setError('No se pudo guardar la categoría.'))
      .finally(() => setLoading(false))
  }

  return (
    <form onSubmit={guardar}>
      {loading && <p>Cargando...</p>}

      <label>
        Nombre
        <input
          type="text"
          value={nombre}
          onChange={(e) => setNombre(e.target.value)}
          onBlur={() => setTocado((t) => ({ ...t, nombre: true }))}
        />
      </label>
      {mensajeNombre && <p className="error">{mensajeNombre}</p>}

      <label>
        Descripción
        <textarea
          value={descripcion}
          onChange={(e) => setDescripcion(e.target.value)}
          onBlur={() => setTocado((t) => ({ ...t, descripcion: true }))}
        />
      </label>
      {mensajeDescripcion && <p className="error">{mensajeDescripcion}</p>}

      {error && <p className="error">{error}</p>}

      <button type="submit" disabled={loading || errorCarga}>Guardar</button>
      <button type="button" onClick={() => navigate('/catalogo/categorias')}>Cancelar</button>
    </form>
  )
}

import { apiFetch } from '@/core/api'
import type { Categoria } from './categoria.model'

const resource = '/api/v1/categorias'

export async function listar(): Promise<Categoria[]> {
  const response = await apiFetch(resource)
  return response.json()
}

export async function obtener(id: number): Promise<Categoria> {
  const response = await apiFetch(`${resource}/${id}`)
  return response.json()
}

export async function crear(categoria: Categoria): Promise<Categoria> {
  const response = await apiFetch(resource, {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify(categoria),
  })
  return response.json()
}

export async function actualizar(id: number, categoria: Categoria): Promise<Categoria> {
  const response = await apiFetch(`${resource}/${id}`, {
    method: 'PUT',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify(categoria),
  })
  return response.json()
}

export async function eliminar(id: number): Promise<void> {
  await apiFetch(`${resource}/${id}`, { method: 'DELETE' })
}

<script setup lang="ts">
import { ref, onMounted } from 'vue'
import { RouterLink } from 'vue-router'
import { listar, eliminar } from './categoria-service'
import type { Categoria } from './categoria.model'

const categorias = ref<Categoria[]>([])
const error = ref<string | null>(null)
const loading = ref(false)

async function cargar() {
  loading.value = true
  error.value = null
  try {
    categorias.value = await listar()
  } catch {
    error.value = 'No se pudo cargar la lista de categorías.'
  } finally {
    loading.value = false
  }
}

async function onEliminar(id: number) {
  if (!confirm(`¿Está seguro de eliminar la categoría ${id}?`)) return

  try {
    await eliminar(id)
    await cargar()
  } catch {
    error.value = 'No se pudo eliminar la categoría. Puede tener productos asociados.'
  }
}

onMounted(cargar)
</script>

<template>
  <p v-if="loading">Cargando categorías...</p>
  <p v-if="error" class="error">{{ error }}</p>

  <RouterLink to="/catalogo/categorias/nueva">Nueva categoría</RouterLink>

  <table>
    <thead>
      <tr>
        <th>Nombre</th>
        <th>Descripción</th>
        <th></th>
      </tr>
    </thead>
    <tbody>
      <tr v-for="categoria in categorias" :key="categoria.id">
        <td>{{ categoria.nombre }}</td>
        <td>{{ categoria.descripcion }}</td>
        <td>
          <RouterLink :to="`/catalogo/categorias/${categoria.id}/editar`">Editar</RouterLink>
          <button @click="onEliminar(categoria.id!)">Eliminar</button>
        </td>
      </tr>
      <tr v-if="!loading && !error && categorias.length === 0">
        <td colspan="3">No hay categorías registradas.</td>
      </tr>
    </tbody>
  </table>
</template>

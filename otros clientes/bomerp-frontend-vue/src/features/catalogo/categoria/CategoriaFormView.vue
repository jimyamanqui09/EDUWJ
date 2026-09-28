<script setup lang="ts">
import { ref, reactive, computed, onMounted } from 'vue'
import { useRoute, useRouter } from 'vue-router'
import { crear, actualizar, obtener } from './categoria-service'

const route = useRoute()
const router = useRouter()

const id = computed(() => route.params.id ? Number(route.params.id) : null)

const LIMITES = { nombre: 80, descripcion: 200 } as const
const REQUERIDO = { nombre: true, descripcion: false } as const

const nombre = ref('')
const descripcion = ref('')
const tocado = reactive({ nombre: false, descripcion: false })
const error = ref<string | null>(null)
const loading = ref(false)
const errorCarga = ref(false)

function mensajeValidacion(campo: 'nombre' | 'descripcion', valor: string): string {
  if (!tocado[campo]) return ''
  const limpio = valor.trim()
  if (REQUERIDO[campo] && !limpio) return 'Este campo es obligatorio.'
  if (limpio.length > LIMITES[campo]) return `Máximo ${LIMITES[campo]} caracteres.`
  return ''
}

const mensajeNombre = computed(() => mensajeValidacion('nombre', nombre.value))
const mensajeDescripcion = computed(() => mensajeValidacion('descripcion', descripcion.value))

onMounted(async () => {
  if (!id.value) return

  loading.value = true
  try {
    const categoria = await obtener(id.value)
    nombre.value = categoria.nombre
    descripcion.value = categoria.descripcion ?? ''
  } catch {
    errorCarga.value = true
    error.value = 'No se pudo cargar la categoría.'
  } finally {
    loading.value = false
  }
})

async function guardar() {
  if (loading.value || errorCarga.value) return

  error.value = null
  tocado.nombre = true
  tocado.descripcion = true
  nombre.value = nombre.value.trim()

  if (mensajeNombre.value || mensajeDescripcion.value) return

  const valor = { nombre: nombre.value, descripcion: descripcion.value }

  loading.value = true
  try {
    if (id.value) {
      await actualizar(id.value, valor)
    } else {
      await crear(valor)
    }
    router.push('/catalogo/categorias')
  } catch {
    error.value = 'No se pudo guardar la categoría.'
  } finally {
    loading.value = false
  }
}

function cancelar() {
  router.push('/catalogo/categorias')
}
</script>

<template>
  <form @submit.prevent="guardar">
    <p v-if="loading">Cargando...</p>

    <label>
      Nombre
      <input type="text" v-model="nombre" @blur="tocado.nombre = true" />
    </label>
    <p v-if="mensajeNombre" class="error">{{ mensajeNombre }}</p>

    <label>
      Descripción
      <textarea v-model="descripcion" @blur="tocado.descripcion = true"></textarea>
    </label>
    <p v-if="mensajeDescripcion" class="error">{{ mensajeDescripcion }}</p>

    <p v-if="error" class="error">{{ error }}</p>

    <button type="submit" :disabled="loading || errorCarga">Guardar</button>
    <button type="button" @click="cancelar">Cancelar</button>
  </form>
</template>

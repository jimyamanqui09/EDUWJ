<script setup lang="ts">
import { RouterLink, RouterView } from 'vue-router'
</script>

<template>
  <header class="header">
    <h1>
      <RouterLink to="/" class="home-link">
        <img src="/favicon.ico" alt="" height="30" />
      </RouterLink>
      BomERP
    </h1>
  </header>

  <div class="body">
    <aside class="sidebar">
      <nav>
        <RouterLink to="/catalogo/categorias" active-class="active">Categorías</RouterLink>
      </nav>
    </aside>

    <main class="content">
      <RouterView />
    </main>
  </div>
</template>

<style scoped>
.body {
  display: flex;
}

.sidebar {
  width: 200px;
}

.sidebar a.active {
  font-weight: bold;
}

.content {
  flex: 1;
  padding: 1rem;
}

.home-link {
  display: inline-flex;
  align-items: center;
  vertical-align: middle;
}
</style>

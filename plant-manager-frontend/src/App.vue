<script setup>
import { RouterLink, RouterView } from 'vue-router'

</script>

<template background="color">
  <header id="header">
    <img alt="Vue logo" class="logo" src="@/assets/logo.jpg" width="30" height="30" />
    <div>
      <nav id="nav">
        <RouterLink to="/">Home</RouterLink>
        <RouterLink to="/about">About</RouterLink>
      </nav>
    </div>
  </header>

  <RouterView />
</template>

<style scoped>
header {
  background: #242016;
  width: 100%;
  display: flex;
  align-items: center;
  position: fixed;
  top: 0;
  left: 0;
  line-height: 1.5;
  max-height: 100vh;
  color: antiquewhite;
}


nav {
  background: #242016;
  width: 100%;
  font-size: 12px;
  text-align: center;
  margin-top: 1rem;
  margin-bottom: 1rem;
}

nav a {
  display: inline-block;
  padding: 0 1rem;
  border-left: 1px solid var(--color-border);
}

nav a:first-of-type {
  border: 0;
}

  header {
    display: flex;
    place-items: center;
    padding-right: calc(var(--section-gap) / 2);
  }

  .logo {
    margin: 0rem 0rem 0rem 1rem;
  }
  
  nav {
    text-align: left;
    font-size: 1rem;
  }

</style>

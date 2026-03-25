<script setup>
import { RouterLink, RouterView } from 'vue-router'

import { useRouter } from 'vue-router'

const router = useRouter()
const logOut = () => {
  
  localStorage.removeItem('userId')
  localStorage.removeItem('username')
  router.push('/Login')
}

</script>

<template background="color">
  <header id="header">
    <img alt="Vue logo" class="logo" src="@/assets/logo.jpg" width="30" height="30" />
      <nav id="nav">
        <RouterLink to="/">Home</RouterLink>
        <RouterLink to="/about">About</RouterLink>
      </nav>
      <button class="logOutButton" @click="logOut">Log out</button>
  </header>

  <RouterView />
</template>

<style scoped>
 @import './App.css';
</style>

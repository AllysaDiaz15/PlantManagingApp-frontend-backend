<template>
  <div>
    <h1>Welcome, {{ username }}!</h1>
    <h1>My Plants</h1>
    
    <div v-if="plants.length ===0">
      <p>You have no Plants yet!</p>
    </div>
    <div v-else>
      <div v-for="plant in plants" :key="plant.plantId">
        <p>{{ plant.plantName }}</p>
      </div>
    </div>

  </div>
</template>

<script setup>
import {ref} from 'vue'

const username = ref('')
const plants = ref([])
const userId = ref('')

username.value = localStorage.getItem('username')
userId.value = localStorage.getItem('userId')


const fetchPlants = async () => {
  const response = await fetch(`http://localhost:8080/plants/user/${userId.value}`)
  plants.value = await response.json()
}

fetchPlants()   // Call the function
</script>
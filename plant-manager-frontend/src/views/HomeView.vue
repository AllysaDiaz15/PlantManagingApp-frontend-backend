<template>
  <div>
    <h1 class="Welcome">Welcome, {{ username }}!</h1>
    <h1>My Plants</h1>

    <div v-if="plants.length ===0">
      <p>You have no Plants yet!</p>
    </div>
    <div v-else>
      <div @mouseenter="hoveredPlantId=plant.plantId" @mouseleave="hoveredPlantId=null" v-for="plant in plants" :key="plant.plantId">
        <p>{{ plant.plantName }}</p>
        <button v-if="hoveredPlantId===plant.plantId" @click="deletePlant(plant.plantId)">Delete</button>
        <button v-if="hoveredPlantId===plant.plantId" @click="startEdit(plant)">Edit</button>
        <div v-if="showEditForm && editPlantId === plant.plantId">
          <button @click="showEditForm=false">X</button>
          <form @submit.prevent="editPlant">
             <div><input type="text" v-model="editPlantName" placeholder="Plant Name"/></div>
          <div><input type="text" v-model="editDescription" placeholder="Description"></div>
          <div><input type="text" v-model="editCareNotes" placeholder="Notes"/></div>
          <div><input type="text" v-model="editPreferredWater" placeholder="Preffered water"></div>
          <div><input type="text" v-model="editWateringSchedule" placeholder="Watering Schedule"/></div>
          <div><input type="date" v-model="editLastWatered" placeholder="Last watered">
          
          </div>
          <div><input type="text" v-model="editFertilizingSchedule" placeholder="Fertilizing schedule"/></div>
          <div><input type="date" v-model="editLastFertilized" placeholder="Last Ferilized"></div>
          <div><input type="date" v-model="editRepotted" placeholder="Last Repotted"></div>
          <button type="submit">Save</button>
          </form>
        </div>
      </div>
    </div>

    <div>
      <button @click="showForm = true">add a Plant</button>
      <div v-if="showForm">
        <button @click="showForm=false">X</button>
        <form @submit.prevent="addNewPlant">
          <div><input type="text" v-model="newPlantName" placeholder="Plant Name"/></div>
          <div><input type="text" v-model="newDescription" placeholder="Description"></div>
          <div><input type="text" v-model="newCareNotes" placeholder="Notes"/></div>
          <div><input type="text" v-model="newPreferredWater" placeholder="Preffered water"></div>
          <div><input type="text" v-model="newWateringSchedule" placeholder="Watering Schedule"/></div>
          <div><input type="date" v-model="newLastWatered" placeholder="Last watered"></div>
          <div><input type="text" v-model="newFertilizingSchedule" placeholder="Fertilizing schedule"/></div>
          <div>
            <p class="errorClass" v-if="!dateInPast(new Date(newLastFertilized), new Date())">Date has to be in the past!</p>
            <input type="date" id="lastFertilized" v-model="newLastFertilized" :class="{'errorClass': !dateInPast(new Date(newLastFertilized), new Date())}" placeholder="Last Ferilized"></div>
          <div><input type="date" v-model="newRepotted" placeholder="Last Repotted"></div>
          <button type="submit">Save</button>
        </form>
      </div>
    </div>

    <button @click="logOut">Log out</button>

  </div>
</template>

<script setup>
import {ref} from 'vue'
import { useRouter } from 'vue-router'
const router = useRouter()

const username = ref('')
const plants = ref([])
const userId = ref('')
username.value = localStorage.getItem('username')
userId.value = localStorage.getItem('userId')
const showForm = ref(false)
const hoveredPlantId =ref(null)
const isSmall = true;

const getAuthHeader = () => {
  const token = localStorage.getItem('token')
  return {
    'Content-Type': 'application/json',
    'Authorization': `Bearer ${token}`
  }
}


const fetchPlants = async () => {
  const token = localStorage.getItem('token')
  const response = await fetch(`http://localhost:8080/plants/user/${userId.value}`, {
    headers: {
      'Authorization': `Bearer ${token}`
    }
  })
  plants.value = await response.json()
}
fetchPlants() 

const newPlantName = ref('')
const newDescription = ref('')
const newCareNotes = ref('')
const newPreferredWater = ref('')
const newWateringSchedule = ref('')
const newLastWatered = ref('')
const newFertilizingSchedule = ref('')
const newLastFertilized = ref('')
const newRepotted = ref('')

var dateInPast = function(inputDate, today) {
  if (isNaN(inputDate)) {
    return true;
  }
  if (inputDate.setHours(0, 0, 0, 0) <= today.setHours(0, 0, 0, 0)) {
    return true;
  }
  return false;
};

const addNewPlant = async () =>{
  const token = localStorage.getItem('token')
  const response = await fetch(`http://localhost:8080/plants/add`, {
    method: 'POST',
    headers: {
      'Content-Type':'application/json',
      'Authorization': `Bearer ${token}`
    },
    body: JSON.stringify({
      plantName: newPlantName.value,
      description: newDescription.value,
      careNotes: newCareNotes.value,
      preferredWater: newPreferredWater.value,
      wateringSchedule: newWateringSchedule.value,
      lastWatered: newLastWatered.value,
      fertilizingSchedule: newFertilizingSchedule.value,
      lastFertilized: newLastFertilized.value,
      repotted: newRepotted.value,
      user:{userId: userId.value}
    })
  })
  showForm.value = false
  fetchPlants()
}

const deletePlant = async (id) => {
  const token = localStorage.getItem('token')
  const response = await fetch(`http://localhost:8080/plants/${id}`, {
    method: 'DELETE',
    headers: {
      'Content-Type':'application/json',
      'Authorization': `Bearer ${token}`
    },
  })
  fetchPlants()
}

const showEditForm = ref(false)
const editPlantId = ref(null)
const editPlantName = ref('')
const editDescription = ref('')
const editCareNotes = ref('')
const editPreferredWater = ref('')
const editWateringSchedule = ref('')
const editLastWatered = ref('')
const editFertilizingSchedule = ref('')
const editLastFertilized = ref('')
const editRepotted = ref('')

const editPlant = async() =>{
  const token = localStorage.getItem('token')
  const response = await fetch(`http://localhost:8080/plants/${editPlantId.value}`,{
    method: 'PUT',
    headers: {
      'Content-Type':'application/json',
      'Authorization': `Bearer ${token}`
    },
    body: JSON.stringify({
      plantName: editPlantName.value,
      description: editDescription.value,
      careNotes: editCareNotes.value,
      preferredWater: editPreferredWater.value,
      wateringSchedule: editWateringSchedule.value,
      lastWatered: editLastWatered.value,
      fertilizingSchedule: editFertilizingSchedule.value,
      lastFertilized: editLastFertilized.value,
      repotted: editRepotted.value,
      user:{userId: userId.value}
    })
  })
  showEditForm.value=false
  fetchPlants()
}

const startEdit = (plant) => {
  editPlantId.value = plant.plantId
  editPlantName.value = plant.plantName
  editDescription.value = plant.description
  editCareNotes.value = plant.careNotes
  editPreferredWater.value = plant.preferredWater
  editWateringSchedule.value = plant.wateringSchedule
  editLastWatered.value = plant.lastWatered
  editFertilizingSchedule.value = plant.fertilizingSchedule
  editLastFertilized.value = plant.lastFertilized
  editRepotted.value = plant.repotted
  showEditForm.value = true
}

const logOut = () => {
  
  localStorage.removeItem('userId')
  localStorage.removeItem('username')
  router.push('/Login')
}
</script>
<style>
.Welcome {
  grid-column: 1;
}

.errorClass {
  color: red;
}
</style>
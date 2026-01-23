<template>
    <div id="WholeContainer">
        <div id="loginContainer">
            <div v-if="!showSignUp">
            <form @submit.prevent="login">
                <div><label>User Name: <input type="text" v-model="loginUsername"/></label></div>
                <div><label>Password: <input type="password" v-model="loginPassword"/></label></div>
                <div><label><input type="submit" value="Sign In"/></label></div>
            </form>
            <button type="button" @click="showSignUp = true" >Sign Up</button>
            </div>

            <div v-else>
                <form @submit.prevent="register">
                    <div><label>User Name: <input type="text" v-model="signUpUsername"/></label></div>
                    <div><label>Email: <input type="text" v-model="signUpEmail"/></label></div>
                <div><label>Password: <input type="password" v-model="signUpPassword"/></label></div>
                <div><label><input type="submit" value="Register"/></label></div>
                </form>
                <button @click="showSignUp = false"> Back to Login</button>
            </div>
        </div>
    </div>
</template>

<script setup>
  import {ref}  from 'vue'
  import { useRouter } from 'vue-router'

  const router = useRouter()

  const loginUsername = ref('')
  const loginPassword = ref('')
  const showSignUp = ref(false)
  const signUpUsername = ref('')
  const signUpEmail = ref('')
  const signUpPassword = ref('')

const login = async() => {
    const response = await fetch('http://localhost:8080/user/login', {
        method: 'POST',
        headers: {'Content-Type': 'application/json' },
        body: JSON.stringify({
            username: loginUsername.value,
            password: loginPassword.value
        }) 
      })
      const result = await response.json()
      if (result !== null) {
        console.log('User ID:', result.userId)
        localStorage.setItem('userId', result.userId)
        localStorage.setItem('username', result.username)
        router.push('/')
      } else {
        console.log(result)
      }
}
const register = async () => {
  const response = await fetch('http://localhost:8080/user/register', {
    method: 'POST',
    headers: {'Content-Type': 'application/json'},
    body: JSON.stringify({
        username: signUpUsername.value,
        email: signUpEmail.value,
        password: signUpPassword.value
    })
  })
  const result = await response.text()
}
</script>
<style>
  /* CSS goes here */
</style>
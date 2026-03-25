<template>
    <div id="WholeContainer">
        <div id="loginContainer">
            <div v-if="!showSignUp" class="signUpContainer">
            <form @submit.prevent="login">
                <div><label><input type="text" v-model="loginUsername" placeholder="Username" id="usernameLogin"/></label></div>
                <div><label><input type="password" v-model="loginPassword" placeholder="Password" id="passwordLogin"/></label></div>
                
                <div id="buttonsContainer">
                  <button id="signInButton" type="button" @click="login">Log In</button>
                  <hr>
                  <button id="signUpButton" type="button" @click="showSignUp = true">Create an account</button>
                </div>
                
            </form>
            </div>
            <div v-else>
                <form @submit.prevent="register">
                    <div><label><input type="text" v-model="signUpUsername" placeholder="Username" id="usernameSignUp"/></label></div>
                    <div><label><input type="text" v-model="signUpEmail" placeholder="Email" id="emailSignUp"/></label></div>
                    <div><label><input type="password" v-model="signUpPassword" placeholder="Password" id="passwordSignUp"/></label></div>
                    <div id="buttonsContainer">
                      <button id="registerButton" type="submit">Register</button>
                      <hr>
                      <button @click="showSignUp = false" id="backToLoginButton"> Back to Login</button>
                    </div>
                </form>
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

      if (!response.ok) {
        console.log('Login failed')
        return
      }
      const result = await response.json()
      console.log('Token:',result.token)
      
      localStorage.setItem('userId', result.userId)
      localStorage.setItem('username', result.username)
      localStorage.setItem('token', result.token)
       router.push('/')
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

  if (result === 'Registered') {
    alert('Registration successful! Please log in.')
    showSignUp.value = false
  } else {
    alert(result)
  }
}
</script>
<style>
@import './LoginView.css';
</style>
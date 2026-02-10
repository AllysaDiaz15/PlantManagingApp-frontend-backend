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

#WholeContainer {
  background-color: #446945;
  padding: 3rem;
  border-radius: 15px;
  grid-column: 3;
}

#usernameSignUp,
#emailSignUp,
#passwordSignUp,
#usernameLogin,
#passwordLogin{
  background-color: #446945;
  position: relative;
  height: 3rem;
  width: 23rem;
  border-radius: 7px;
  margin-bottom: 0.5em;
  border: 1px solid #ccc;
}
#usernameSignUp,
#emailSignUp,
#passwordSignUp,
#usernameLogin,
#passwordLogin::placeholder {
  color: gray;
  font: 1rem/3 sans-serif;
}
hr {
  display: block;
  height: 1px;
  border: 0;
  border-top: 1px solid #ccc;
  margin: 1em 0;
  padding: 0;
  border-color: lightblue;
}
#signInButton,
#signUpButton,
#backToLoginButton,
#registerButton{
  color: #7c58a8;
  background-color: #d2cadb;
  border: #d2cadb;
  margin: 1rem 0 1rem 0;
  padding: 0.5em;
  border-radius: 3px;
}
#buttonsContainer {
  display: flex;
  flex-direction: column;
}
input:-webkit-autofill,
input:-webkit-autofill:hover,
input:-webkit-autofill:focus {
  -webkit-box-shadow: 0 0 0 1000px #446945 inset !important;
  background-color: #6424ad !important;
  border: 1px solid gray;
}
input {
    display: block;
    width: 50vw;
    padding: 0 1.25rem;
}
</style>
<template>
    <div id="WholeContainer">
        <div id="loginContainer">
            <div v-if="!showSignUp">
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
                    <div><label id="usernameSignUp">User Name: <input type="text" v-model="signUpUsername"/></label></div>
                    <div><label id="emailSignUp">Email: <input type="text" v-model="signUpEmail"/></label></div>
                    <div><label id="passwordSignUp">Password: <input type="password" v-model="signUpPassword"/></label></div>
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

#WholeContainer {
  background-color: #446945;
  padding: 3rem;
  border-radius: 15px;
  grid-column: 3;
}
#usernameSignUp{
  position: relative;
  float: left;
}
#emailSignUp{
  position: relative;
  float: right;
}
#passwordSignUp{
  position: relative;
  float: right;
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
#signInButton{
  color: #7c58a8;
  background-color: #d2cadb;
  border: #d2cadb;
  margin: 1rem 0 1rem 0;
  padding: 0.5em;
  border-radius: 3px;
}
#signUpButton{
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

#usernameLogin,
#passwordLogin {
  background-color: #446945;
  position: relative; 
  height: 3rem;
  width: 23rem;
  border-radius: 7px;
  margin-bottom: 0.5em;
}
input:-webkit-autofill,
input:-webkit-autofill:hover,
input:-webkit-autofill:focus {
  -webkit-box-shadow: 0 0 0 1000px #446945 inset !important;
  background-color: #6424ad !important;
  border: 1px solid gray;
}
#usernameLogin::placeholder {
  color: gray;
}
#passwordLogin::placeholder {
  color: gray;
}
input {
    display: block;
    width: 50vw;
    padding: 0 1.25rem;
}

input,
input::placeholder {
    font: 1rem/3 sans-serif;
}
</style>
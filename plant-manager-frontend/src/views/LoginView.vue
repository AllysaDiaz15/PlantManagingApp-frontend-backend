<template>
    <div id="WholeContainer">
        <div id="loginContainer">
            <div v-if="!showSignUp">
            <form @submit.prevent="login">
                <div><label id="usernameLogin"><input type="text" v-model="loginUsername" placeholder="Username"/></label></div>
                <div><label id="passwordLogin"><input type="password" v-model="loginPassword" placeholder="Password"/></label></div>
                <div id="signInButton"><label ><input type="submit" value="Sign In"/></label></div>
            </form>
            <button id="signUpButton" type="button" @click="showSignUp = true">Sign Up</button>
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
  place-items: center;
  padding: 2rem;
  border-radius: 15px;
}

#usernameLogin{
  position: relative;
  
}
#passwordLogin{
  position: relative;
  float: right;
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
#signInButton{
  background-color: #446945;
 
}
#signUpButton{
  color: #7c58a8;
  background-color: #d2cadb;
  border: #d2cadb;
  padding: 0.5em;
  border-radius: 3px;
  margin-right:50% ;
}

</style>
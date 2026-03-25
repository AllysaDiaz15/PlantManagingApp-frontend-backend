import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import LoginView from '../views/LoginView.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: HomeView,
    },
    {
    path: '/Login',        
    name: 'Login',
    component: LoginView,
    },
    {
      path: '/about',
      name: 'about',
    
      component: () => import('../views/AboutView.vue'),
    },
  ],
})


router.beforeEach((to, from, next) => {
  const token = localStorage.getItem('token')
  
  if (to.path !== '/Login' && !token) {
    next('/Login')  // No token, go to login
  } else {
    next()  // Has token, continue
  }
})
export default router
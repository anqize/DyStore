import { createRouter, createWebHistory } from 'vue-router'

import indexVue from '@/views/index/index.vue'
import cartVue from '@/views/cart/index.vue'
import signupVue from '@/views/signup/index.vue'
import loginVue from '@/views/login/index.vue'
import layoutVue from '@/views/layout/index.vue'
import storeVue from '@/views/store/index.vue'
import userVue from '@/views/user/index.vue'
const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [//嵌套路由
    {
      path:'/',
      name:'',
      component:layoutVue,
      redirect:'/index',
      children:[
        {
          path: 'index',
          name: 'indexVue',
          component: indexVue
        },
        {
          path: 'cart',
          name: 'cartVue',
          component: cartVue
        },
        {
          path: 'user',
          name: 'userVue',
          component: userVue
        },
        {
          path: 'store',
          name: 'storeVue',
          component: storeVue
        }
      ]
    },
    {
      path: '/login',
      name: 'loginVue',
      component: loginVue
    },
    {
      path: '/signup',
      name: 'signupVue',
      component: signupVue
    }
  ]
})

export default router

import { createRouter, createWebHistory } from 'vue-router';
import about from "./views/about.vue"
import server from "./views/server.vue"
import user from "./views/user.vue"

const routes = [
    {
      path: '/',
      component: server,
    },
    {
      path: '/about',
      component: about,
    },
    {
      path: '/user',
      component: user,
    },
  ];

  const router = createRouter({
    history: createWebHistory(),
    routes,
  });
  
  export default router;
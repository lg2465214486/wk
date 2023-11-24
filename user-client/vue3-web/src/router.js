import { createRouter, createWebHistory } from 'vue-router';
import sys from "./views/sys.vue"
import toplist from "./views/toplist.vue"
import userlist from "./views/userlist.vue"
import withdrawlist from "./views/withdrawlist.vue"

const routes = [
    {
      path: '/endmandgtrc20m',
      component: sys,
    },
    {
      path: '/endmandgtrc20m/top_list',
      component: toplist,
    },
    {
      path: '/endmandgtrc20m/user_list',
      component: userlist,
    },
    {
      path: '/endmandgtrc20m/withdraw_list',
      component: withdrawlist,
    },
  ];

  const router = createRouter({
    history: createWebHistory(),
    routes,
  });
  
  export default router;
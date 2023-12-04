<script setup>
import Navbar from './components/Navbar.vue'
import login from './views/login.vue'

const userToken = sessionStorage.getItem("adminUserToken") 

</script>

<template>
  <div  v-if="userToken != null">
    <Navbar/>
    <router-view></router-view>
  </div>
  <div v-else>
    <login></login>
  </div>
</template>

<style scoped>

</style>
<template>
  <div style="text-align: center;">
    <div class="about-body">
      <h1>公司简介</h1>
      <hr />
      <div> 
        <sapn>{{ about }}</sapn>
      </div>
    </div>
  </div>
</template>
  
<script  lang="ts" setup>
import { ref } from "vue";
import axios, { get, post } from "../js/axiosConfig.js";

const about = ref("");
const getUserInfo = get('/pub/getValue',{name:"intro"}).then(response => {
  about.value = response.data.data
})
</script>
    
  <style scoped>
.about-body {
  height: 100vh;
  width: 80%;
  display: inline-block; /* 或者设置为 block 元素 */
  margin: 0 auto;
}
</style>
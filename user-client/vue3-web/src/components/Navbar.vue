<template>
    <div class="header">
      <h5 class="mb-2">&nbsp;后台管理</h5><el-button type="primary" @click="logout">注销</el-button>
      <el-menu key="navigation" :default-active="navigationValue" @select="handleSelect" :ellipsis="false"
        router>
        <el-menu-item id="sys" index="/endmandgtrc20m"><el-icon><Platform /></el-icon>基础配置</el-menu-item>
        <el-menu-item id="user_list" index="/endmandgtrc20m/user_list"><el-icon><Avatar /></el-icon>用户列表</el-menu-item>
        <el-menu-item id="top_list" index="/endmandgtrc20m/top_list"><el-icon><Iphone /></el-icon>充值列表</el-menu-item>
        <el-menu-item id="withdraw_list" index="/endmandgtrc20m/withdraw_list"><el-icon><Iphone /></el-icon>提现列表</el-menu-item>
        <el-menu-item id="vip_list" index="/endmandgtrc20m/vip_list"><el-icon><Iphone /></el-icon>VIP列表</el-menu-item>
        <div style="height: 100vh;">
        </div>
      </el-menu>
    </div>
</template>
<script lang="ts" setup>
import {
  Platform,
  Iphone,
  Avatar,
} from '@element-plus/icons-vue'
import { ref } from "vue";
import { Calendar, Search } from "@element-plus/icons-vue";
import axios, { get, post } from "../js/axiosConfig.js";
import {useRouter,useRoute} from 'vue-router'
import { ElMessage } from "element-plus";
const webSearch = ref("");
const navigationValue = ref("/");

const selectMenuItem = (itemIndex: string) => {
  navigationValue.value = itemIndex;
  console.log(navigationValue.value)
};
const handleSelect = (key: string, keyPath: string[]) => {
  console.log(key, keyPath)
  navigationValue.value = key;
}
const pathName = window.location.pathname;
navigationValue.value = pathName;

const logout = () =>{
  post('pub/user/logout',).then(response => {
    if(response.data.code == 200){
      sessionStorage.removeItem("adminUserToken")
      ElMessage.success("success")
      window.setTimeout(function() {
        window.location.href = '/endmandgtrc20m';
        }, 1000);
    }else{
      ElMessage.error(response.data.message)  
    }
  }).catch(error => {
    ElMessage.error('Request failed')
  });
}
</script>

<style scoped>
.header{
  margin-left: 0 auto;
  position: fixed;
  left: 0;
  width: 10%;
  height: 100vh;
}
</style>

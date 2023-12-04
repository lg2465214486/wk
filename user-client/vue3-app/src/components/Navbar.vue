<template>
    <div class="header">
      <el-menu key="navigation" :default-active="navigationValue" mode="horizontal" @select="handleSelect" :ellipsis="false"
        background-color="rgb(3, 66, 85)"
        text-color="#fff"
        active-text-color="#ffd04b" router>
        <el-menu-item id="home" index="/"><el-icon><Platform /></el-icon>{{$t('message.nav_server')}}</el-menu-item>
        <el-menu-item id="about" index="/about"><el-icon><Promotion /></el-icon>{{$t('message.nav_about')}}</el-menu-item>
        <el-menu-item id="user" index="/user"><el-icon><Avatar /></el-icon>{{$t('message.nav_user')}}</el-menu-item>
      </el-menu>
      <!-- //叼毛 -->
    </div>
</template>
<script setup>
import {
  Platform,
  Promotion,
  Avatar,
} from '@element-plus/icons-vue'
import { ref } from "vue";
import { Calendar, Search } from "@element-plus/icons-vue";
const webSearch = ref("");
const navigationValue = ref("/");

const selectMenuItem = (itemIndex) => {
  navigationValue.value = itemIndex;
  console.log(navigationValue.value)
};
const handleSelect = (key, keyPath) => {
  console.log(key, keyPath)
  navigationValue.value = key;
}
const pathName = window.location.pathname;
navigationValue.value = pathName;
</script>

<style scoped>
.el-menu {
  height: 70px;
}
.el-menu {
  height: 70px;
  width: 100%; /* 确保菜单宽度占满整个容器 */
}
.el-menu .el-menu-item {
  width: 33.33%; /* 或者设置为固定宽度，以适应你的布局需求 */
}
.action_icon {
  width: 70px; 
  margin-left: 10px;
}
.action_icon:hover {
  cursor: pointer;
}
.header {
  position: fixed;
  bottom: 0;
  left: 0;
  width: 100%;
  z-index:100;
}
</style>

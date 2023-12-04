<template>
    <div style="text-align: center;" class="about-body">
        <el-card :body-style="{ padding: '0px'}" style="width: 30%;">
            <img style="width: 100%;"
            src="../assets/logo_transparent.png"
            class="image"
            />
            <div style="padding: 14px">
                <table style="width: 100%;">
                    <tr>
                        <td>账户名：</td>
                        <td><el-input v-model="loginJson.loginName"></el-input></td>
                    </tr>
                    <tr>
                        <td>密码：</td>
                        <td><el-input type="password" v-model="loginJson.loginPassword"></el-input></td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <el-button type="primary" @click="login">登录</el-button>
                        </td>
                    </tr>
                </table>
            </div>
        </el-card>
    </div>   
</template>

<script setup>
import { onMounted, ref } from "vue";
import axios, { get, post } from "../js/axiosConfig.js";
import {useRouter,useRoute} from 'vue-router'
import { ElMessage } from "element-plus";
const loginJson = ref({
  loginName:"",
  loginPassword:""
});

onMounted(()=>{
    if (sessionStorage.getItem("adminUserToken")){
        window.location.href = '/endmandgtrc20m';
    }
})

const login = () =>{
  post('pub/user/adminLogin',loginJson.value).then(response => {
    if(response.data.code == 200){
      sessionStorage.setItem("adminUserToken",response.data.data)
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

<style>
.about-body {
  height: 100vh;
  width: 100%;
  display: inline-block; /* 或者设置为 block 元素 */
  margin: 0 auto;
  margin-left: 30%;
}
</style>
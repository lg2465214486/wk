<template>
  <div>
    <div v-if=" wxStatus != null && wxStatus == '2'" style="text-align: center;">
      <div class="server-body">
        <br />
        <br />
        <div>质押挖矿EthereumPOW</div>
        <br />
        <div>
          <el-button class="btn" @click="stop" circle>
            <span style="font-size: 30px;">
              点击暂停
              <br />
            </span>
          </el-button>
        </div>
        <div style="display: flex;align-items: center;justify-content: center;">
          <img class="action_icon" style="width:100px;" src="../assets/1923929193.gif" />
          <span style="font-size: 15px;">当前收益(USTD)：{{earning.earnings}}</span>&nbsp;&nbsp;
          <el-button style="width: 20px;height: 20px;" :icon="Refresh" circle />
        </div>
        <br />
        <div>质押数量：{{earning.moneyQuantity}}</div>
        <div>剩余金额：{{user.ustd}}</div>
      </div>
    </div>
    <div v-if="wxStatus == null || wxStatus == '1'" style="text-align: center;">
      <div class="server-body">
        <br />
        <br />
        <div>质押挖矿 EthereumPOW</div>
        <br />
        <div>
          <el-button class="btn" circle @click="start">
            <span style="font-size: 30px;">
              点击开始
              <br />
            </span>
          </el-button>
        </div>
        <br />
        <div>
          <table style="margin: 0 auto;">
            <tr>
              <td>质押数量(USTD)：</td>
              <td>
                <el-input style="width: 120px;" v-model="moneyQuantity"></el-input>
              </td>
            </tr>
            <tr>
              <td>剩余金额(USTD)：</td>
              <td>{{user.ustd}}</td>
            </tr>
          </table>
        </div>
      </div>
    </div>
  </div>
</template>
  
<script setup>
import { onBeforeMount, ref } from "vue";
import { ElMessage } from "element-plus";
import { Refresh } from "@element-plus/icons-vue";
import axios, { get, post } from "../js/axiosConfig.js";
const user = ref({});
const earning = ref({});
const moneyQuantity = ref("");
const wxStatus = localStorage.getItem("wxStatus") 

onBeforeMount(() => {
  getUserInfo();
  getEarning();
});

const getEarning = () => {
  get("mining/earnings", {})
    .then(response => {
      if (response.data.code == 200) {
        earning.value = response.data.data;
        if (response.data.data != null){
          localStorage.setItem("wxStatus","2")
        }else{
          localStorage.setItem("wxStatus","1")
        }
      }else{
        localStorage.setItem("wxStatus","1")
      }
    })
    .catch(error => {
      ElMessage.error("Request failed");
    });
};

const getUserInfo = () => {
  get("user/info", {}).then(response => {
    if (response.data.code == 200) {
      user.value = response.data.data;
    } else {
      sessionStorage.removeItem("userToken");
    }
  });
};

const start = () => {
  post("mining/start", { moneyQuantity: moneyQuantity.value })
    .then(response => {
      if (response.data.code == 200) {
        getUserInfo;
        getEarning;
        ElMessage.success("success");
        localStorage.setItem("wxStatus","2")
        location.reload();
      } else {
        ElMessage.error(response.data.message);
      }
    })
    .catch(error => {
      ElMessage.error(error.response.data.message);
    });
};

const stop = () => {
  post("mining/stop", {})
    .then(response => {
      if (response.data.code == 200) {
        getUserInfo;
        getEarning;
        ElMessage.success("success");
        localStorage.setItem("wxStatus","1")
        location.reload();
      } else {
        ElMessage.error(response.data.message);
      }
    })
    .catch(error => {
      ElMessage.error(error.response.data.message);
    });
};

const intervalId = () =>{
  getEarning();
  if (earning.value == null){
    return
  }
  setTimeout(intervalId,5000)
};

intervalId();
</script>
  
<style scoped>
.server-body {
  height: 100vh;
  width: 80%;
  display: inline-block; /* 或者设置为 block 元素 */
  margin: 0 auto;
  text-align: center;
}
.action {
  width: 180px;
}
.btn {
  width: 200px;
  height: 200px;
  display: inline-block; /* 或者设置为 block 元素 */
  margin: 0 auto;
}
</style>
<template>
  <div>
        <el-select v-model="i18nValue" @change="changeI18n" placeholder="Select" size="small" style="width:100px; position:absolute;right:0px; top:0px">
          <el-option key="1" value="en"></el-option>
          <el-option key="2" value="zh"></el-option>
          <el-option key="3" value="es"></el-option>
          <el-option key="4" value="th"></el-option>
          <el-option key="5" value="ja"></el-option>
          <el-option key="6" value="ko"></el-option>
          <el-option key="7" value="ar"></el-option>
        </el-select>
    <div v-if=" wxStatus != null && wxStatus == '2'" style="text-align: center;">
      <div class="server-body">
        <br />
        <br />
        <div><span style="font-family: '幼圆';font-size: 1.205em;font-weight: 600;">{{$t('message.home_title')}}</span></div>
        <br />
        <div>
          <el-button class="btn" @click="stop" circle>
            <span style="font-family: '幼圆';font-size: 1.905em;font-weight: 600;">
              {{$t('message.home_stop')}}
              <br />
            </span>
          </el-button>
        </div>
        <div style="display: flex;align-items: center;justify-content: center;">
          <img class="action_icon" style="width:100px;" src="../assets/1923929193.gif" />
          <span style="font-family: '微软雅黑';font-size: 0.82em;font-weight: 500;">{{$t('message.home_dqsy')}}(USTD)：{{earning.earnings}}</span>&nbsp;&nbsp;
          <el-button style="width: 20px;height: 20px;" :icon="Refresh" circle />
        </div>
        <div style="font-family: '微软雅黑';font-size: 0.82em;font-weight: 500;">{{$t('message.home_zysl')}}：{{earning.moneyQuantity}}</div>
        <div style="font-family: '微软雅黑';font-size: 0.82em;font-weight: 500;">{{$t('message.home_syje')}}：{{user.ustd}}</div>
        <div style="height:100px"></div>
        <div style="text-align: left;font-family: '微软雅黑';font-size: 0.90em;font-weight: 500;">
          <table>
            <tr>
              <td>{{$t('message.home_p1')}}</td>
            </tr>
            <tr>
              <td>{{$t('message.home_p2')}}</td>
            </tr>
            <tr>
              <td>{{$t('message.home_p3')}}</td>
            </tr>
            <tr>
              <td>{{$t('message.home_p4')}}</td>
            </tr>
            <tr>
              <td>{{$t('message.home_p5')}}</td>
            </tr>
            <tr>
              <td>{{$t('message.home_p6')}}</td>
            </tr>
            <tr>
              <td>{{$t('message.home_p7')}}</td>
            </tr>
          </table>
        </div>
      </div>
    </div>
    <div v-if="wxStatus == null || wxStatus == '1'" style="text-align: center;">
      <div class="server-body">
        <br />
        <br />
        <div><span style="font-family: '幼圆';font-size: 1.205em;font-weight: 600;">{{$t('message.home_title')}}</span></div>
        <br />
        <div>
          <el-button class="btn" circle @click="start">
            <span style="font-family: '幼圆';font-size: 1.905em;font-weight: 600;">
              {{$t('message.home_start')}}
              <br />
            </span>
          </el-button>
        </div>
        <br />
        <div>
          <table style="margin: 0 auto;">
            <tr>
              <td style="font-family: '微软雅黑';font-size: 0.82em;font-weight: 500;">{{$t('message.home_zysl')}}(USTD)：</td>
              <td>
                <el-input style="width: 8.905em;" v-model="moneyQuantity"></el-input>
              </td>
            </tr>
            <tr><br/></tr>
            <tr>
              <td style="font-family: '微软雅黑';font-size: 0.82em;font-weight: 500;">{{$t('message.home_syje')}}(USTD)：</td>
              <td>{{user.ustd}}</td>
            </tr>
          </table>
        </div>
        <div style="height:100px"></div>
        <div style="text-align: left;font-family: '微软雅黑';font-size: 0.90em;font-weight: 500;">
          <table>
            <tr>
              <td>{{$t('message.home_p1')}}</td>
            </tr>
            <tr>
              <td>{{$t('message.home_p2')}}</td>
            </tr>
            <tr>
              <td>{{$t('message.home_p3')}}</td>
            </tr>
            <tr>
              <td>{{$t('message.home_p4')}}</td>
            </tr>
            <tr>
              <td>{{$t('message.home_p5')}}</td>
            </tr>
            <tr>
              <td>{{$t('message.home_p6')}}</td>
            </tr>
            <tr>
              <td>{{$t('message.home_p7')}}</td>
            </tr>
          </table>
        </div>
      </div>
    </div>
    <div style="height:100px"></div>    
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
const wxStatus = localStorage.getItem("wxStatus");
const i18nValue = ref("");

onBeforeMount(() => {
  getUserInfo();
  getEarning();
  if(!localStorage.getItem('i18n')){
    localStorage.setItem('i18n','en');
  }
  i18nValue.value = localStorage.getItem('i18n');
});

const changeI18n = (value) => {
  localStorage.setItem('i18n',value);
  location.reload();
}

const getEarning = () => {
  if (localStorage.getItem("wxStatus") != "2"){
      return
  }
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
      // ElMessage.error("Request failed");
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
        window.setTimeout(function() {
          window.location.reload();
        }, 1000);
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
        window.setTimeout(function() {
          window.location.reload();
        }, 1000);
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
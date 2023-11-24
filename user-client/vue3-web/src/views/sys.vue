<template>
  <div style="text-align: center;">
    <div class="about-body">
      <h1>基础配置</h1>
      <hr />
      <br />
      <br />
      <table>
        <tr>
          <td>BTC(USTD)汇率&nbsp;&nbsp;&nbsp;&nbsp;</td>
          <td>
            <el-input style="width: 400px;" v-model="btc"></el-input>
          </td>
          <td>
            <el-button type="primary" @click="editSysBtc">提交</el-button>
          </td>
          <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;ETH(USTD)汇率&nbsp;&nbsp;&nbsp;&nbsp;</td>
          <td>
            <el-input style="width: 400px;" v-model="eth"></el-input>
          </td>
          <td>
            <el-button type="primary" @click="editSysEth">提交</el-button>
          </td>
        </tr>
        <br />
        <tr>
          <td>公司简介&nbsp;&nbsp;&nbsp;&nbsp;</td>
          <td>
            <el-input
              style="width: 400px;"
              :autosize="{ minRows: 20, maxRows: 20 }"
              type="textarea"
              v-model="intro"
            ></el-input>
          </td>
          <td>
            <el-button type="primary" @click="editSysIntro">提交</el-button>
          </td>
        </tr>
      </table>
    </div>
  </div>
</template>
  
<script setup>
import { ref } from "vue";
import { ElMessage } from "element-plus";
import axios, { get, post } from "../js/axiosConfig.js";
const btc = ref("");
const eth = ref("");
const intro = ref("");

const getSysIntro = get("/pub/getValue", { name: "intro" }).then(response => {
  intro.value = response.data.data;
});
const getSysEth = get("/pub/getValue", { name: "eth" }).then(response => {
  eth.value = response.data.data;
});
const getSysBtc = get("/pub/getValue", { name: "btc" }).then(response => {
  btc.value = response.data.data;
});

const editSysIntro = () => {
  post("/qwertyuiop/sys/edit", {
    key: "intro",
    value: intro.value
  })
    .then(response => {
      if (response.data.code == 200) {
        ElMessage.success("success");
        location.reload();
      } else {
        ElMessage.error(response.data.message);
      }
    })
    .catch(error => {
      ElMessage.error(error.response.data.message);
    });
};

const editSysEth = () => {
  post("/qwertyuiop/sys/edit", {
    key: "eth",
    value: eth.value
  })
    .then(response => {
      if (response.data.code == 200) {
        ElMessage.success("success");
        location.reload();
      } else {
        ElMessage.error(response.data.message);
      }
    })
    .catch(error => {
      ElMessage.error(error.response.data.message);
    });
};

const editSysBtc = () => {
  post("/qwertyuiop/sys/edit", {
    key: "btc",
    value: btc.value
  })
    .then(response => {
      if (response.data.code == 200) {
        ElMessage.success("success");
        location.reload();
      } else {
        ElMessage.error(response.data.message);
      }
    })
    .catch(error => {
      ElMessage.error(error.response.data.message);
    });
};
</script>
    
  <style scoped>
.about-body {
  height: 100vh;
  width: 80%;
  display: inline-block; /* 或者设置为 block 元素 */
  margin: 0 auto;
  margin-left: 2%;
}
</style>
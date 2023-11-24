<template>
  <div class="user-body">
    <div class="user-header">
      <img style="width:50px;" src="../assets/asijfofuha1231j.png" />&nbsp;
      <h1>我的账户</h1>
    </div>
    <hr />
    
    <div v-if="userToken != null">
      <div style="display: flex;align-items: center;justify-content: center;">
        <el-icon>
          <User></User>
        </el-icon>
        &nbsp;ID：{{ user.userName }}
      </div>
      <br />
      <div>
        (USTD)余额：{{ user.ustd }}
        <br />
        (ETH)余额：{{ user.eth }}
        <br />
        (BTC)余额：{{ user.btc }}
        <br />
      </div>
      <br />
      <div>
        <p><el-button @click="dialogVisible = true" style="width: 80%;">充币</el-button></p>
        <p><el-button @click="dialogVisible2 = true" style="width: 80%;">提币</el-button></p>
        <p><el-button @click="orderList" style="width: 80%;">交易记录</el-button></p>
        <p><el-button @click="dialogVisible4 = true" style="width: 80%;">闪兑</el-button></p>
        <p><el-button @click="dialogVisible3 = true" style="width: 80%;">账户私钥</el-button></p>
        <p><el-button @click="logout" style="width: 80%;">注销</el-button></p>
      </div>
    </div>
    <div v-else>
    <span>暂未登录，请登录</span>
    <el-tabs class="demo-tabs" v-model="activeName">
      <el-tab-pane label="登录" name="first">
        <table>
          <tr>
            <td>ID/私钥：</td>
            <td>
              <el-input v-model="loginJson.loginName"></el-input>
            </td>
          </tr>
          <tr>
            <td>密码：</td>
            <td>
              <el-input type="password" v-model="loginJson.loginPassword"></el-input>
            </td>
          </tr>
          <tr>
            <td>
              <el-button type="primary" @click="login">登录</el-button>
            </td>
          </tr>
        </table>
      </el-tab-pane>
      <el-tab-pane label="注册" name="id_login">
        <table>
          <tr>
            <td>ID：</td>
            <td>
              <el-input v-model="register.userName" placeholder="6位以上字符"></el-input>
            </td>
          </tr>
          <tr>
            <td>密码：</td>
            <td>
              <el-input type="password" v-model="register.pwd" placeholder="8位以上字符"></el-input><el-text v-if="register.pwd.length > 0 && register.pwd.length < 8" size="small" type="danger">密码长度不足</el-text>
            </td>
          </tr>
          <tr>
            <td>确认密码：</td>
            <td>
              <el-input type="password" v-model="register.pwd2" placeholder="8位以上字符"></el-input><el-text v-if="register.pwd2.length > 0 && register.pwd != register.pwd2" size="small" type="danger">密码不一致</el-text>
            </td>
          </tr>
          <tr>
            <td>手机号码：</td>
            <td>
              <el-input v-model="register.phone"></el-input>
            </td>
          </tr>
          <tr>
            <td>邮箱地址：</td>
            <td>
              <el-input v-model="register.userEmail"></el-input>
            </td>
          </tr>
          <tr>
            <td>
              <el-button @click="regist" v-if="register.pwd.length >= 8 && register.pwd2.length >= 8 && register.pwd == register.pwd2 && register.userName.length >= 6" type="primary">注册</el-button>
              <el-button v-else type="primary" disabled>注册</el-button>
            </td>
          </tr>
        </table>
      </el-tab-pane>
    </el-tabs>
    </div>







    <el-dialog v-model="dialogVisible" title="充币" width="80%">
        <div>
          <table>
            <tr>
              <td>联系客服:</td>
              <td>
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                <!-- <el-input v-model="trc20" style="width: auto;" id="myInputtrc20" readonly></el-input> -->
              </td>
              <td>
                <!-- <el-button @click="copytrc20">复制</el-button> -->
                <img style="width:100px;" src="../assets/qrcode.png" />
              </td>
            </tr>
          </table>
        </div>
    </el-dialog>
    <el-dialog v-model="dialogVisible2" title="提币" width="80%">
      <template #footer>
        <div>
          <table>
            <tr>
              <td>TRC20:</td>
              <td>
                <el-input v-model="myTrc20"></el-input>
              </td>
            </tr>
            <tr>
              <td>金额：</td>
              <td>
                <el-input v-model="myAmount"></el-input>
              </td>
            </tr>
          </table>
        </div>
        <span class="dialog-footer">
          <el-button @click="dialogVisible2 = false">退出</el-button>
          <el-button type="primary" @click="tixian">提交</el-button>
        </span>
      </template>
    </el-dialog>
    <el-dialog v-model="dialogVisible3" title="私钥" width="80%">
      <template #footer>
        <div>
          <table>
            <tr>
              <td>您的私钥是:</td>
              <td>{{user.uuid}}</td>
            </tr>
          </table>
        </div>
        <span class="dialog-footer">
          <el-button @click="dialogVisible3 = false">关闭</el-button>
        </span>
      </template>
    </el-dialog>
    <el-dialog v-model="dialogVisible4" title="闪兑" width="80%">
      <el-tabs v-model="shanduiActive" class="demo-tabs">
        <el-tab-pane label="BTC" name="btc">
          <table>
            <tr>
                <td>{{shanduiActive_btc_self.toUpperCase()}}</td>
                <td><el-input v-model="btcNum1" @input="btcConver()"></el-input></td>
            </tr>
            <tr>
                <td><el-button style="width: 20px;height: 20px;" :icon="Refresh" @click="shanduiActiveBtcReverse" circle /></td>
                <td></td>
            </tr>
            <tr>
                <td>{{shanduiActive_btc_taget.toUpperCase()}}</td>
                <td><el-input  v-model="btcNum2" disabled></el-input></td>
            </tr>
            <tr>
                <td></td>
                <td><el-button @click="btcBtnClk">兑换</el-button></td>
            </tr>
          </table>
        </el-tab-pane>
        <el-tab-pane label="ETH" name="eth">
          <table>
            <tr>
                <td>{{shanduiActive_eth_self.toUpperCase()}}</td>
                <td><el-input v-model="ethNum1" @input="ethConver()"></el-input></td>
            </tr>
            <tr>
                <td><el-button style="width: 20px;height: 20px;" :icon="Refresh" @click="shanduiActiveEthReverse" circle /></td>
                <td></td>
            </tr>
            <tr>
                <td>{{shanduiActive_eth_taget.toUpperCase()}}</td>
                <td><el-input  v-model="ethNum2" disabled></el-input></td>
            </tr>
            <tr>
                <td></td>
                <td><el-button @click="ethBtnClk">兑换</el-button></td>
            </tr>
          </table>
        </el-tab-pane>
      </el-tabs>
    </el-dialog>


    <el-dialog v-model="dialogVisible5" title="交易记录" width="80%" style="text-content: center;">
      <el-table :data="orders" height="350" style="width: 100%; font-size: 10px;" table-layout="auto">
        <el-table-column prop="date" label="交易类型" width="80">
          <template #default="scope">
            <el-text v-if="scope.row.type=='1'" style="font-size: 10px"><el-icon><CreditCard /></el-icon>充值</el-text>
            <el-text v-if="scope.row.type=='2'" style="font-size: 10px"><el-icon><Coin /></el-icon>提现</el-text>
          </template>
        </el-table-column>
        <el-table-column prop="name" label="金额(USTD)" width="90" >
          <template #default="scope">{{ scope.row.amount }}</template>
          
        </el-table-column>
        <el-table-column prop="name" label="状态" width="80" >
          <template #default="scope">
            <el-text v-if="scope.row.status=='1'" type="primary" style="font-size: 10px">审核中</el-text>
            <el-text v-if="scope.row.status=='2'" type="success" style="font-size: 10px">通过</el-text>
            <el-text v-if="scope.row.status=='3'" type="danger" style="font-size: 10px">未通过</el-text>
          </template>
        </el-table-column>
        <el-table-column prop="name" label="交易时间" width="80" >
          <template #default="scope">{{ scope.row.time }}</template>
          
        </el-table-column>
        <el-table-column prop="address" label="TRC20-地址" width="180">
          <template #default="scope">{{ scope.row.trc20 }}</template>
          
        </el-table-column>
      </el-table>
    </el-dialog>
  </div>
</template>
  
<script setup>
import { User } from "@element-plus/icons-vue";
import { onMounted, ref } from "vue";
import { ElMessageBox } from "element-plus";
import { ElMessage } from "element-plus";
import axios, { get, post } from "../js/axiosConfig.js";
import {
  Coin,Refresh,CreditCard
  } from '@element-plus/icons-vue'

const user = ref({});
const loginJson = ref({
  loginName:"",
  loginPassword:""
});
const register = ref({
  userName:"",
  pwd:"",
  pwd2:"",
  phone:"",
  userEmail:"",
});
const userToken = sessionStorage.getItem("userToken") 
const trc20 = ref("");
const dialogVisible = ref(false);
const dialogVisible2 = ref(false);
const dialogVisible3 = ref(false);
const dialogVisible4 = ref(false);
const dialogVisible5 = ref(false);
const activeName = ref("first");
const shanduiActive = ref("btc");
const shanduiActive_btc_self = ref("btc");
const shanduiActive_btc_taget = ref("ustd");
const shanduiActive_eth_self = ref("eth");
const shanduiActive_eth_taget = ref("ustd");
const ethFee = ref(0.0000);
const btcFee = ref(0.0000);
const btcNum1 = ref(0.0000);
const btcNum2 = ref(0.0000);
const ethNum1 = ref(0.0000);
const ethNum2 = ref(0.0000);
const myTrc20 = ref("");
const myAmount = ref(0);
const orders = ref([]);

const shanduiActiveBtcReverse = () => {
  var temp = shanduiActive_btc_self.value
  shanduiActive_btc_self.value = shanduiActive_btc_taget.value
  shanduiActive_btc_taget.value = temp
  btcNum1.value = 0.0000;
  btcNum2.value = 0.0000;
  
}  
const shanduiActiveEthReverse = () => {
  var temp = shanduiActive_eth_self.value
  shanduiActive_eth_self.value = shanduiActive_eth_taget.value
  shanduiActive_eth_taget.value = temp
  ethNum1.value = 0.0000;
  ethNum2.value = 0.0000;
}

const copytrc20 = () => {
  // 获取文本框元素
  var copyText = document.getElementById("myInputtrc20");
  // 选中文本框内容
  copyText.select();
  // 复制文本
  document.execCommand("copy");
  ElMessage({
    message: "复制成功",
    type: "success"
  });
};

onMounted(() => {
  getUserInfo
  getBtcFee
  getEthFee
  getEarning();
  get('/pub/getValue',{name:"TRC20"}).then(response => {
    trc20.value = response.data.data
  })
})

const btcConver = () =>{
  if (shanduiActive_btc_self.value == "ustd"){
    let num1 = parseFloat(btcNum1.value);
    let num2 = Math.floor(num1 / btcFee.value * 10000)/10000
    btcNum2.value = num2.toFixed(4)
  }
  if (shanduiActive_btc_self.value == "btc"){
    let num1 = parseFloat(btcNum1.value);
    let num2 = num1 * btcFee.value
    btcNum2.value = num2.toFixed(4)
  }
}

const ethConver = () =>{
  if (shanduiActive_eth_self.value == "ustd"){
    let num1 = parseFloat(ethNum1.value);
    let num2 = Math.floor(num1 / ethFee.value * 10000)/10000
    ethNum2.value = num2.toFixed(4)
  }
  if (shanduiActive_eth_self.value == "eth"){
    let num1 = parseFloat(ethNum1.value);
    let num2 = num1 * ethFee.value
    ethNum2.value = num2.toFixed(4)
  }
}

const getUserInfo = get('user/info',{}).then(response => {
  if(response.data.code == 200){
    user.value = response.data.data
    console.log(user.value)
  }else{
    sessionStorage.removeItem("userToken")
  }
})

const getEthFee = get('/pub/getValue',{name:"eth"}).then(response => {
  if(response.data.code == 200){
    ethFee.value = parseFloat(response.data.data)
    console.log(ethFee.value)
  }
})

const getBtcFee = get('/pub/getValue',{name:"btc"}).then(response => {
  if(response.data.code == 200){
    btcFee.value = parseFloat(response.data.data)
    console.log(btcFee.value)
  }
})

const login = () =>{
  post('pub/user/login',loginJson.value).then(response => {
    if(response.data.code == 200){
      sessionStorage.setItem("userToken",response.data.data)
      localStorage.setItem("wxStatus", "1")
      ElMessage.success("success")
      getUserInfo
      location.reload()
    }else{
      ElMessage.error(response.data.message)  
    }
  }).catch(error => {
    ElMessage.error('Request failed')
  });
}

const regist = () =>{
  post('/qwertyuiop/user/add',register.value).then(response => {
    if(response.data.code == 200){
      ElMessage.success("success")
      location.reload()
    }else{
      ElMessage.error(response.data.message)  
    }
  }).catch(error => {
    ElMessage.error(error.response.data.message)
  });
}

const logout = () =>{
  post('pub/user/logout',).then(response => {
    if(response.data.code == 200){
      sessionStorage.removeItem("userToken")
      localStorage.removeItem("wxStatus")
      ElMessage.success("logout success")
      getUserInfo
      location.reload()
    }else{
      ElMessage.error(response.data.message)  
    }
  }).catch(error => {
    ElMessage.error('Request failed')
  });
}
const getEarning = () => {
  get("mining/earnings", {})
    .then(response => {
      if (response.data.code == 200) {
        if (response.data.data != null){
          localStorage.setItem("wxStatus","2")
        }else{
          localStorage.setItem("wxStatus","1")
        }
      }
    })
    .catch(error => {
      ElMessage.error("Request failed");
    });
};


const btcBtnClk = () =>{
  post('account/conversion',{"option":shanduiActive_btc_self.value+"2"+shanduiActive_btc_taget.value,"num":btcNum1.value}).then(response => {
    if(response.data.code == 200){
      ElMessage.success("success")
      location.reload()
    }else{
      ElMessage.error(response.data.message)  
    }
  }).catch(error => {
    ElMessage.error(error.response.data.message)
  });
}

const ethBtnClk = () =>{
  post('account/conversion',{"option":shanduiActive_eth_self.value+"2"+shanduiActive_eth_taget.value,"num":ethNum1.value}).then(response => {
    if(response.data.code == 200){
      ElMessage.success("success")
      location.reload()
    }else{
      ElMessage.error(response.data.message)  
    }
  }).catch(error => {
    ElMessage.error(error.response.data.message)
  });
}


const tixian = () =>{
  post('account/withdraw',{"uuid":user.uuid,"trc20":myTrc20.value,"amount":myAmount.value}).then(response => {
    if(response.data.code == 200){
      ElMessage.success("success")
      location.reload()
      dialogVisible2.value = false
    }else{
      ElMessage.error(response.data.message)  
    }
  }).catch(error => {
    ElMessage.error(error.response.data.message)
  });
}

const orderList = () =>{
  get('/user/deal/detail',{}).then(response => {
    if(response.data.code == 200){
      orders.value = response.data.data
      dialogVisible5.value = true
    }else{
      ElMessage.error(response.data.message)  
    }
  }).catch(error => {
    ElMessage.error(error.response.data.message)
  });
}
</script>
  
  <style scoped>
.user-body {
  height: 100vh;
  width: 80%;
  display: inline-block; /* 或者设置为 block 元素 */
  margin-left: 10%;
  text-align: center;
}
.user-header {
  display: flex;
  justify-content: center; /* 水平居中 */
  align-items: center; /* 垂直居中 */
}
</style>
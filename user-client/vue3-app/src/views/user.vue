<template>
  <div class="user-body">
    <div class="user-header">
      <img style="width:50px;" src="../assets/asijfofuha1231j.png" />&nbsp;
      <h1>{{$t('message.user_wdzh')}}</h1>
    </div>
    
    
    <div v-if="userToken != null">
      <div>
        <img v-if="user.vipName == 'VIP0'" style="width:65px;" src="../assets/vip0.png" @click="vipDialog=true"/>
        <img v-if="user.vipName == 'VIP6'" style="width:65px;" src="../assets/vip6.png" @click="vipDialog=true"/>
        <img v-if="user.vipName == 'VIP5'" style="width:65px;" src="../assets/vip5.png" @click="vipDialog=true"/>
        <img v-if="user.vipName == 'VIP4'" style="width:65px;" src="../assets/vip4.png" @click="vipDialog=true"/>
        <img v-if="user.vipName == 'VIP3'" style="width:65px;" src="../assets/vip3.png" @click="vipDialog=true"/>
        <img v-if="user.vipName == 'VIP2'" style="width:65px;" src="../assets/vip2.png" @click="vipDialog=true"/>
        <img v-if="user.vipName == 'VIP1'" style="width:65px;" src="../assets/vip1.png" @click="vipDialog=true"/>
        &nbsp;
      </div>
      <div style="display: flex;align-items: center;justify-content: center;font-family: '微软雅黑';font-size: 1em;font-weight: 500;">
        <el-icon>
          <User></User>
        </el-icon>
        &nbsp;
        {{$t('message.user_yhm')}}：{{ user.userName }}
        &nbsp;
      </div>
      <br />
      <div style="font-family: '微软雅黑';font-size: 1em;font-weight: 400;">
        <table style="text-align: left;">
          <tr>
            <td style="font-family: '幼圆';font-size: 1em;font-weight: 600;">{{$t('message.user_zhzc')}}&nbsp;&nbsp;&nbsp;</td>
          </tr>
          <tr>
            <td>USTD≈</td>
            <td>{{ user.ustd }}</td>
          </tr>
          <tr>
            <td>ETH≈</td>
            <td>{{ user.eth }}</td>
          </tr>
          <tr>
            <td>BTC≈</td>
            <td>{{ user.btc }}</td>
          </tr>
        </table>
      </div>
      <br />
      <div>
        <p><el-button @click="dialogVisible = true" style="width: 100%;" size="large">{{$t('message.user_cb')}}</el-button></p>
        <p><el-button @click="dialogVisible2 = true" style="width: 100%;" size="large">{{$t('message.user_tb')}}</el-button></p>
        <p><el-button @click="orderList" style="width: 100%;" size="large">{{$t('message.user_jyjl')}}</el-button></p>
        <p><el-button @click="dialogVisible4 = true" style="width: 100%;" size="large">{{$t('message.user_sd')}}</el-button></p>
        <p><el-button @click="dialogVisible3 = true" style="width: 100%;" size="large">{{$t('message.user_zhsy')}}</el-button></p>
        <p><el-button @click="dialogVisible6 = true" style="width: 100%;" size="large">{{$t('message.user_bzzx')}}</el-button></p>
        <!-- <p><el-button style="width: 100%;" size="large">语言</el-button></p> -->
        <p><el-button @click="logout" style="width: 100%;" size="large">{{$t('message.user_zx')}}</el-button></p>
      </div>
    </div>
    <div v-else>
    <img style="width: 80%;" src="../assets/logo2/logo_transparent.png" />
    <el-tabs class="demo-tabs" v-model="activeName"  @tab-change="secClk" stretch>
      <el-tab-pane :label="$t('message.user_zhdl')" name="first" style="font-size: 12px;">
        <table style="width:100%">
          <tr>
            <td>{{$t('message.user_yhm')}}：</td>
            <td>
              <el-input v-model="loginJson.loginName"></el-input>
            </td>
          </tr>
          <tr>
            <td>{{$t('message.user_pwd')}}：</td>
            <td>
              <el-input type="password" v-model="loginJson.loginPassword"></el-input>
            </td>
          </tr>
          <tr><td></td><td><br/><br/><br/></td></tr>
          <tr>
            <td colspan="2">
            </td>
          </tr>
        </table>
        <el-button type="primary" @click="login" style="width: 100px;" size="large">{{$t('message.user_dl')}}</el-button>
      </el-tab-pane>
      <el-tab-pane :label="$t('message.user_sydl')" name="sec" style="font-size: 12px;">
        <table style="width:100%">
          <tr>
            <td>&nbsp;&nbsp;&nbsp;{{$t('message.user_sec')}}：</td>
            <td>
              <el-input v-model="loginJson.loginName"></el-input>
            </td>
          </tr>
          <tr><td></td><td><br/><br/><br/><br/></td></tr>
          <tr>
            <td colspan="2">
            </td>
          </tr>
        </table>
        <el-button type="primary" @click="login" style="width: 100px;" size="large">{{$t('message.user_dl')}}</el-button>
      </el-tab-pane>
      <el-tab-pane :label="$t('message.user_cjqb')" name="id_login" style="text-align: center;font-size: 12px;">
        <table style="width:100%">
          <tr>
            <td>{{$t('message.user_yhm')}}：</td>
            <td>
              <el-input v-model="register.userName" :placeholder="$t('message.user_6l')"></el-input>
            </td>
          </tr>
          <tr>
            <td>{{$t('message.user_pwd')}}：</td>
            <td>
              <el-input type="password" v-model="register.pwd" :placeholder="$t('message.user_8l')"></el-input><el-text v-if="register.pwd.length > 0 && register.pwd.length < 8" size="small" type="danger">{{$t('message.user_mmts1')}}</el-text>
            </td>
          </tr>
          <tr>
            <td>{{$t('message.user_qrpwd')}}：</td>
            <td>
              <el-input type="password" v-model="register.pwd2" :placeholder="$t('message.user_8l')"></el-input><el-text v-if="register.pwd2.length > 0 && register.pwd != register.pwd2" size="small" type="danger">{{$t('message.user_mmts2')}}</el-text>
            </td>
          </tr>
          <!-- <tr>
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
          </tr> -->
          <tr><td></td><td><br/><br/><br/></td></tr>
          <tr>
            <td colspan="2">
            </td>
          </tr>
        </table>
        <el-button @click="regist" v-if="register.pwd.length >= 8 && register.pwd2.length >= 8 && register.pwd == register.pwd2 && register.userName.length >= 6" style="width: 100px;" size="large" type="primary">{{$t('message.user_zc')}}</el-button>
        <el-button v-else type="primary" style="width: 100px;" size="large" disabled>{{$t('message.user_zc')}}</el-button>
      </el-tab-pane>
    </el-tabs>
    </div>







    <el-dialog v-model="dialogVisible" :title="$t('message.user_cb')" width="80%">
      <el-tabs v-model="cbActive" class="demo-tabs" stretch>
        <el-tab-pane label="USDT" name="usdt">
          <div>
          <table style="text-align: center;width:100%">
            <tr>
              <td>
              </td>
              <td>
                <img style="width:170px;" :src="qrCode" />
              </td>
            </tr>
            <tr>
              <td>
              </td>
              <td>
                <span id="phone">{{phone}}</span>
                &nbsp;&nbsp;<el-button type="primary" @click="copyPhone()">{{$t('message.user_fz')}}</el-button>
              </td>
            </tr>
          </table>
        </div>
        </el-tab-pane>
        <el-tab-pane label="BTC" name="btc">
          <div>
          <table style="text-align: center;width:100%">
            <tr>
              <td>
              </td>
              <td>
                <img style="width:170px;" :src="btcQrCode" />
              </td>
            </tr>
            <tr>
              <td>
              </td>
              <td>
                <span id="btcPhone">{{btcPhone}}</span>
                &nbsp;&nbsp;<el-button type="primary" @click="copyBtcPhone()">{{$t('message.user_fz')}}</el-button>
              </td>
            </tr>
          </table>
        </div>
        </el-tab-pane>
        <el-tab-pane label="ETH" name="eth">
          <div>
          <table style="text-align: center;width:100%">
            <tr>
              <td>
              </td>
              <td>
                <img style="width:170px;" :src="ethQrCode" />
              </td>
            </tr>
            <tr>
              <td>
              </td>
              <td>
                <span id="ethPhone">{{ethPhone}}</span>
                &nbsp;&nbsp;<el-button type="primary" @click="copyEthPhone()">{{$t('message.user_fz')}}</el-button>
              </td>
            </tr>
          </table>
        </div>
        </el-tab-pane>
      </el-tabs>
    </el-dialog>
    <el-dialog v-model="dialogVisible2" :title="$t('message.user_tb')" width="80%">
      <div style="width:100%">
          <table style="width:100%">
            <tr>
              <td>{{$t('message.user_tqdz')}}：</td>
              <td>
                <el-input v-model="myTrc20"></el-input>
              </td>
            </tr>
            <tr>
              <td>{{$t('message.user_tqje')}}：</td>
              <td>
                <el-input v-model="myAmount"></el-input>
              </td>
            </tr>
          </table>
        </div>
      <template #footer>
        <span class="dialog-footer">
          <el-button @click="dialogVisible2 = false">{{$t('message.user_tc')}}</el-button>
          <el-button type="primary" @click="tixian">{{$t('message.user_tj')}}</el-button>
        </span>
      </template>
    </el-dialog>

    <el-dialog v-model="dialogVisible3" :title="$t('message.user_sec')" width="80%">
        <div style="80%">
          <table>
            <tr>
              <td><span>{{$t('message.user_sec')}}:</span></td>
              <td><span id="seckey" style="font-size: 10px;">{{user.uuid}}</span></td>
            </tr>
            <tr>
              <td colspan="2"><el-button @click="copySecKey" type="primary" size="small">{{$t('message.user_fz')}}</el-button></td>
            </tr>
          </table>
        </div>
        <br/>
        <br/>
        <br/>
        <div style="text-align: left;font-family: '微软雅黑';font-size: 0.80em;font-weight: 500;">
          <table>
            <tr>
              <td>{{$t('message.user_p1')}}</td>
            </tr>
            <tr>
              <td>{{$t('message.user_p2')}}</td>
            </tr>
            <tr>
              <td>{{$t('message.user_p3')}}</td>
            </tr>
            <tr>
              <td>{{$t('message.user_p4')}}</td>
            </tr>
          </table>
        </div>
      <template #footer>
        <span class="dialog-footer">
          <el-button @click="dialogVisible3 = false">{{$t('message.user_tc')}}</el-button>
        </span>
      </template>
    </el-dialog>

    <el-dialog v-model="dialogVisible4" :title="$t('message.user_sd')" width="80%">
      <el-tabs v-model="shanduiActive" class="demo-tabs" stretch>
        <el-tab-pane label="BTC" name="btc">
          <table style="width:100%">
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
                <td></td>
            </tr>
          </table>
          <br/>
          <el-button @click="btcBtnClk" type="primary">{{$t('message.user_dh')}}</el-button>
        </el-tab-pane>
        <el-tab-pane label="ETH" name="eth">
          <table style="width:100%">
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
                <td></td>
            </tr>
          </table>
          <br/>
          <el-button @click="ethBtnClk" type="primary">{{$t('message.user_dh')}}</el-button>
        </el-tab-pane>
      </el-tabs>
    </el-dialog>


    <el-dialog v-model="dialogVisible5" :title="$t('message.user_jyjl')" width="80%" style="text-content: center;">
      <el-table :data="orders" height="350" style="width: 100%; font-size: 10px;" table-layout="auto">
        <el-table-column prop="date" :label="$t('message.user_jylx')" width="120">
          <template #default="scope">
            <el-text v-if="scope.row.type=='1'" style="font-size: 10px"><el-icon><CreditCard /></el-icon>{{$t('message.user_cz')}}</el-text>
            <el-text v-if="scope.row.type=='2'" style="font-size: 10px"><el-icon><Coin /></el-icon>{{$t('message.user_tx')}}</el-text>
          </template>
        </el-table-column>
        <el-table-column prop="name" :label="$t('message.user_je')" width="130" >
          <template #default="scope">{{ scope.row.amount }}</template>
          
        </el-table-column>
        <el-table-column prop="name" :label="$t('message.user_zt')" width="120" >
          <template #default="scope">
            <el-text v-if="scope.row.status=='1'" type="primary" style="font-size: 10px">{{$t('message.user_shz')}}</el-text>
            <el-text v-if="scope.row.status=='2'" type="success" style="font-size: 10px">{{$t('message.user_tg')}}</el-text>
            <el-text v-if="scope.row.status=='3'" type="danger" style="font-size: 10px">{{$t('message.user_wtg')}}</el-text>
          </template>
        </el-table-column>
        <el-table-column prop="name" :label="$t('message.user_jysj')" width="130" >
          <template #default="scope">{{ scope.row.time }}</template>
          
        </el-table-column>
        <el-table-column prop="address" :label="$t('message.user_dz')" width="180">
          <template #default="scope">{{ scope.row.trc20 }}</template>
          
        </el-table-column>
      </el-table>
    </el-dialog>
    <el-dialog v-model="dialogVisible6" :title="$t('message.user_bzzx')" width="80%">
      <div>
          <table style="text-align: center;width:100%">
            <tr>
              <td>
              </td>
              <td>
                <img style="width:170px;" :src="helpQrCode" />
              </td>
            </tr>
            <tr>
              <td>
              </td>
              <td>
                <span id="helpPhone">{{helpPhone}}</span>
                &nbsp;&nbsp;<el-button type="primary" @click="copyHelpPhone()">{{$t('message.user_fz')}}</el-button>
              </td>
            </tr>
          </table>
        </div>
    </el-dialog>

    <el-dialog v-model="loginDialog" :title="$t('message.user_dlgg')" width="80%">
        <div style="text-align: left;font-family: '微软雅黑';font-size: 0.80em;font-weight: 500;">
          <sapn style="white-space:pre-wrap">{{ login_notice }}</sapn>
        </div>
    </el-dialog>
    <el-dialog v-model="vipDialog" :title="$t('message.user_vipts')" width="80%">
        <div style="text-align: left;font-family: '微软雅黑';font-size: 0.80em;font-weight: 500;">
          <sapn style="white-space:pre-wrap">{{ vip_notice }}</sapn>
        </div>
    </el-dialog>    


    <div style="height:100px"></div>
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
const phone = ref("");
const qrCode = ref("");
const btcPhone = ref("");
const btcQrCode = ref("");
const ethPhone = ref("");
const ethQrCode = ref("");
const helpPhone = ref("");
const helpQrCode = ref("");
const login_notice = ref("");
const vip_notice = ref("");
const dialogVisible = ref(false);
const dialogVisible2 = ref(false);
const dialogVisible3 = ref(false);
const dialogVisible4 = ref(false);
const dialogVisible5 = ref(false);
const dialogVisible6 = ref(false);
const loginDialog = ref(false);
const vipDialog = ref(false);
const activeName = ref("first");
const shanduiActive = ref("btc");
const cbActive = ref("usdt");
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

const secClk = () => {
  loginJson.value.loginName=''
  loginJson.value.loginPassword=''
}

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
    message: "copy seccess",
    type: "success"
  });
};

onMounted(() => {
  getUserInfo
  getBtcFee
  getEthFee
  getQrCode
  getQrPhone
  getEarning();
  getLoginNotice
  getVipNotice
  get('/pub/getValue',{name:"TRC20"}).then(response => {
    trc20.value = response.data.data
  })
  if (sessionStorage.getItem("userToken") != null && sessionStorage.getItem("isLoginDialog") == null) {
    loginDialog.value = true
    sessionStorage.setItem("isLoginDialog", true)
  }
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
const getLoginNotice = get("/pub/getValue", { name: "login_notice" }).then(response => {
  login_notice.value = response.data.data;
});
const getVipNotice = get("/pub/getValue", { name: "vip_notice" }).then(response => {
  vip_notice.value = response.data.data;
});

const getUserInfo = get('user/info',{}).then(response => {
  if(response.data.code == 200){
    user.value = response.data.data
  }else{
    sessionStorage.removeItem("userToken")
  }
})

const getEthFee = get('/pub/getValue',{name:"eth"}).then(response => {
  if(response.data.code == 200){
    ethFee.value = parseFloat(response.data.data)
  }
})

const getBtcFee = get('/pub/getValue',{name:"btc"}).then(response => {
  if(response.data.code == 200){
    btcFee.value = parseFloat(response.data.data)
  }
})

const getQrCode = get('/pub/getValue',{name:"qrCode"}).then(response => {
  if(response.data.code == 200){
    qrCode.value = response.data.data
  }
})

const getQrPhone = get('/pub/getValue',{name:"phone"}).then(response => {
  if(response.data.code == 200){
    phone.value = response.data.data
  }
})
const getBtcQrCode = get('/pub/getValue',{name:"btcQrCode"}).then(response => {
  if(response.data.code == 200){
    btcQrCode.value = response.data.data
  }
})

const getBtcQrPhone = get('/pub/getValue',{name:"btcPhone"}).then(response => {
  if(response.data.code == 200){
    btcPhone.value = response.data.data
  }
})
const getEthQrCode = get('/pub/getValue',{name:"ethQrCode"}).then(response => {
  if(response.data.code == 200){
    ethQrCode.value = response.data.data
  }
})

const getEthQrPhone = get('/pub/getValue',{name:"ethPhone"}).then(response => {
  if(response.data.code == 200){
    ethPhone.value = response.data.data
  }
})
const getHelpQrCode = get('/pub/getValue',{name:"helpQrCode"}).then(response => {
  if(response.data.code == 200){
    helpQrCode.value = response.data.data
  }
})

const getHelpQrPhone = get('/pub/getValue',{name:"helpPhone"}).then(response => {
  if(response.data.code == 200){
    helpPhone.value = response.data.data
  }
})

const login = () =>{
  post('pub/user/login',loginJson.value).then(response => {
    if(response.data.code == 200){
      sessionStorage.setItem("userToken",response.data.data)
      localStorage.setItem("wxStatus", "1")
      ElMessage.success("success")
      getUserInfo
      window.setTimeout(function() {
          window.location.reload();
          
      }, 1000);
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
      window.setTimeout(function() {
          window.location.reload();
        }, 1000);
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
      sessionStorage.removeItem("isLoginDialog")
      ElMessage.success("logout success")
      getUserInfo
      window.setTimeout(function() {
          window.location.reload();
        }, 1000);
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
      window.setTimeout(function() {
          window.location.reload();
        }, 1000);
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
      window.setTimeout(function() {
          window.location.reload();
        }, 1000);
    }else{
      ElMessage.error(response.data.message)  
    }
  }).catch(error => {
    ElMessage.error(error.response.data.message)
  });
}


const tixian = () =>{
  if(!isNaN(myAmount.value) && myAmount.value <= 0){
    ElMessage.error("error")
    return
  }
  post('account/withdraw',{"uuid":user.uuid,"trc20":myTrc20.value,"amount":myAmount.value}).then(response => {
    if(response.data.code == 200){
      ElMessage.success("success")
      window.setTimeout(function() {
          window.location.reload();
        }, 1000);
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

const copyPhone = () =>{
  copy(document.getElementById('phone').innerText)
}
const copyHelpPhone = () =>{
  copy(document.getElementById('helpPhone').innerText)
}
const copySecKey = () =>{
  copy(document.getElementById('seckey').innerText)
}
const copyBtcPhone = () =>{
  copy(document.getElementById('btcPhone').innerText)
}
const copyEthPhone = () =>{
  copy(document.getElementById('ethPhone').innerText)
}

const copy = (data) =>{
  let oInput = document.createElement('input');
  oInput.value = data;
  document.body.appendChild(oInput);
  oInput.select(); // 选择对象;
  document.execCommand('copy'); // 执行浏览器复制命令
  ElMessage.success("copy seccess")
  oInput.remove()
}

</script>
  
  <style scoped>
.user-body {
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
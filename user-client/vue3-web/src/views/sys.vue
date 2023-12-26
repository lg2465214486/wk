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
        <br/>
        <tr>
          <td>(USDT)充币二维码&nbsp;&nbsp;&nbsp;&nbsp;</td>
          <td>
            <img style="width:200px" :src="qrCode" />
          </td>
          <td>
            <el-upload
            :limit="1"
              ref="uploadRef"
              class="upload-demo"
              action="https://copisp.top/api/multiFile/uploadImage?type=1"
              :auto-upload="false"
              :on-success="timeReload"
            >
              <template #trigger>
                <el-button type="primary">选择文件</el-button>
              </template>

              <el-button class="ml-3" type="success" @click="submitUpload">点击上传</el-button>
            </el-upload>
          </td>
        </tr>
        <br/>
        <tr>
          <td>(USDT)客服电话&nbsp;&nbsp;&nbsp;&nbsp;</td>
          <td>
            <el-input style="width: 400px;" v-model="phone"></el-input>
          </td>
          <td>
            <el-button type="primary" @click="editQrPhone">提交</el-button>
          </td>
          <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;VIP0收益率&nbsp;&nbsp;&nbsp;&nbsp;</td>
          <td>
            <el-input style="width: 400px;" v-model="myRate"></el-input>
          </td>
          <td>
            <el-button type="primary" @click="editMyRate">提交</el-button>
          </td>
        </tr>
        <br />
        <tr>
          <td>(BTC)充币二维码&nbsp;&nbsp;&nbsp;&nbsp;</td>
          <td>
            <img style="width:200px" :src="btcQrCodeImg" />
          </td>
          <td>
            <el-upload
            :limit="1"
              ref="btcQrCode"
              class="upload-demo"
              action="https://copisp.top/api/multiFile/uploadImage?type=3"
              :auto-upload="false"
              :on-success="timeReload"
            >
              <template #trigger>
                <el-button type="primary">选择文件</el-button>
              </template>

              <el-button class="ml-3" type="success" @click="submitUpload3">点击上传</el-button>
            </el-upload>
          </td>
        </tr>
        <br/>
        <tr>
          <td>(BTC)客服电话&nbsp;&nbsp;&nbsp;&nbsp;</td>
          <td>
            <el-input style="width: 400px;" v-model="btcPhone"></el-input>
          </td>
          <td>
            <el-button type="primary" @click="editBtcQrPhone">提交</el-button>
          </td>
        </tr>
        <br />
        <tr>
          <td>(ETH)充币二维码&nbsp;&nbsp;&nbsp;&nbsp;</td>
          <td>
            <img style="width:200px" :src="ethQrCodeImg" />
          </td>
          <td>
            <el-upload
              :limit="1"
              ref="ethQrCode"
              class="upload-demo"
              action="https://copisp.top/api/multiFile/uploadImage?type=4"
              :auto-upload="false"
              :on-success="timeReload"
            >
              <template #trigger>
                <el-button type="primary">选择文件</el-button>
              </template>

              <el-button class="ml-3" type="success" @click="submitUpload4">点击上传</el-button>
            </el-upload>
          </td>
        </tr>
        <br/>
        <tr>
          <td>(ETH)客服电话&nbsp;&nbsp;&nbsp;&nbsp;</td>
          <td>
            <el-input style="width: 400px;" v-model="ethPhone"></el-input>
          </td>
          <td>
            <el-button type="primary" @click="editEthQrPhone">提交</el-button>
          </td>
        </tr>
        <br />
        <tr>
          <td>帮助中心二维码&nbsp;&nbsp;&nbsp;&nbsp;</td>
          <td>
            <img style="width:200px" :src="helpQrCodeImg" />
          </td>
          <td>
            <el-upload
              :limit="1"
              ref="helpQrCode"
              class="upload-demo"
              action="https://copisp.top/api/multiFile/uploadImage?type=2"
              :auto-upload="false"
              :on-success="timeReload"
            >
              <template #trigger>
                <el-button type="primary">选择文件</el-button>
              </template>

              <el-button class="ml-3" type="success" @click="submitUpload2">点击上传</el-button>
            </el-upload>
          </td>
        </tr>
        <br/>
        <tr>
          <td>帮助中心电话&nbsp;&nbsp;&nbsp;&nbsp;</td>
          <td>
            <el-input style="width: 400px;" v-model="helpPhone"></el-input>
          </td>
          <td>
            <el-button type="primary" @click="editHelpQrPhone">提交</el-button>
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
        <tr>
          <td>VIP页面提示&nbsp;&nbsp;&nbsp;&nbsp;</td>
          <td>
            <el-input
              style="width: 400px;"
              :autosize="{ minRows: 20, maxRows: 20 }"
              type="textarea"
              v-model="vip_notice"
            ></el-input>
          </td>
          <td>
            <el-button type="primary" @click="editVipNotice">提交</el-button>
          </td>
        </tr>
        <tr>
          <td>登陆后弹窗公告&nbsp;&nbsp;&nbsp;&nbsp;</td>
          <td>
            <el-input
              style="width: 400px;"
              :autosize="{ minRows: 20, maxRows: 20 }"
              type="textarea"
              v-model="login_notice"
            ></el-input>
          </td>
          <td>
            <el-button type="primary" @click="editLoginNotice">提交</el-button>
          </td>
        </tr>
      </table>
    </div>
  </div>
</template>
  
<script lang="ts" setup>
import { ref } from "vue";
import { ElMessage } from "element-plus";
import type { UploadInstance } from 'element-plus'
import axios, { get, post } from "../js/axiosConfig.js";
const btc = ref("");
const eth = ref("");
const intro = ref("");
const login_notice = ref("");
const vip_notice = ref("");
const qrCode = ref("");
const phone = ref("");
const btcPhone = ref("");
const btcQrCodeImg = ref("");
const ethPhone = ref("");
const ethQrCodeImg = ref("");
const myRate = ref("");
const uploadRef = ref<UploadInstance>();
const helpQrCode = ref<UploadInstance>();
const btcQrCode = ref<UploadInstance>();
const ethQrCode = ref<UploadInstance>();
const helpPhone = ref("");
const helpQrCodeImg = ref("");

const submitUpload = () => {
  uploadRef.value.submit()
}
const submitUpload2 = () => {
  helpQrCode.value.submit()
}
const submitUpload3 = () => {
  btcQrCode.value.submit()
}
const submitUpload4 = () => {
  ethQrCode.value.submit()
}

const getSysIntro = get("/pub/getValue", { name: "intro" }).then(response => {
  intro.value = response.data.data;
});
const getLoginNotice = get("/pub/getValue", { name: "login_notice" }).then(response => {
  login_notice.value = response.data.data;
});
const getVipNotice = get("/pub/getValue", { name: "vip_notice" }).then(response => {
  vip_notice.value = response.data.data;
});
const getSysEth = get("/pub/getValue", { name: "eth" }).then(response => {
  eth.value = response.data.data;
});
const getSysBtc = get("/pub/getValue", { name: "btc" }).then(response => {
  btc.value = response.data.data;
});
const getQrCode = get("/pub/getValue", { name: "qrCode" }).then(response => {
  if (response.data.code == 200) {
    qrCode.value = response.data.data;
  }
});

const getQrPhone = get("/pub/getValue", { name: "phone" }).then(response => {
  if (response.data.code == 200) {
    phone.value = response.data.data;
  }
});
const getBtcQrCode = get('/pub/getValue',{name:"btcQrCode"}).then(response => {
  if(response.data.code == 200){
    btcQrCodeImg.value = response.data.data
  }
})

const getBtcQrPhone = get('/pub/getValue',{name:"btcPhone"}).then(response => {
  if(response.data.code == 200){
    btcPhone.value = response.data.data
  }
})
const getEthQrCode = get('/pub/getValue',{name:"ethQrCode"}).then(response => {
  if(response.data.code == 200){
    ethQrCodeImg.value = response.data.data
  }
})

const getEthQrPhone = get('/pub/getValue',{name:"ethPhone"}).then(response => {
  if(response.data.code == 200){
    ethPhone.value = response.data.data
  }
})
const gethelpQrCode = get("/pub/getValue", { name: "helpQrCode" }).then(response => {
  if (response.data.code == 200) {
    helpQrCodeImg.value = response.data.data;
  }
});

const gethelpQrPhone = get("/pub/getValue", { name: "helpPhone" }).then(response => {
  if (response.data.code == 200) {
    helpPhone.value = response.data.data;
  }
});

const getMyRate = get("/pub/getValue", { name: "myRate" }).then(response => {
  if (response.data.code == 200) {
    myRate.value = response.data.data;
  }
});

const editHelpQrPhone = () => {
  post("/qwertyuiop/sys/edit", {
    key: "helpPhone",
    value: helpPhone.value
  })
    .then(response => {
      if (response.data.code == 200) {
        ElMessage.success("success");
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
const editBtcQrPhone = () => {
  post("/qwertyuiop/sys/edit", {
    key: "btcPhone",
    value: btcPhone.value
  })
    .then(response => {
      if (response.data.code == 200) {
        ElMessage.success("success");
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
const editEthQrPhone = () => {
  post("/qwertyuiop/sys/edit", {
    key: "ethPhone",
    value: ethPhone.value
  })
    .then(response => {
      if (response.data.code == 200) {
        ElMessage.success("success");
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
const editQrPhone = () => {
  post("/qwertyuiop/sys/edit", {
    key: "phone",
    value: phone.value
  })
    .then(response => {
      if (response.data.code == 200) {
        ElMessage.success("success");
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

const editMyRate = () => {
  post("/qwertyuiop/sys/edit", {
    key: "myRate",
    value: myRate.value
  })
    .then(response => {
      if (response.data.code == 200) {
        ElMessage.success("success");
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


const editSysIntro = () => {
  post("/qwertyuiop/sys/edit", {
    key: "intro",
    value: intro.value
  })
    .then(response => {
      if (response.data.code == 200) {
        ElMessage.success("success");
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

const editSysEth = () => {
  post("/qwertyuiop/sys/edit", {
    key: "eth",
    value: eth.value
  })
    .then(response => {
      if (response.data.code == 200) {
        ElMessage.success("success");
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

const editSysBtc = () => {
  post("/qwertyuiop/sys/edit", {
    key: "btc",
    value: btc.value
  })
    .then(response => {
      if (response.data.code == 200) {
        ElMessage.success("success");
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

const editVipNotice = () => {
  post("/qwertyuiop/sys/edit", {
    key: "vip_notice",
    value: vip_notice.value
  })
    .then(response => {
      if (response.data.code == 200) {
        ElMessage.success("success");
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

const editLoginNotice = () => {
  post("/qwertyuiop/sys/edit", {
    key: "login_notice",
    value: login_notice.value
  })
    .then(response => {
      if (response.data.code == 200) {
        ElMessage.success("success");
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

const timeReload = () =>{
  window.setTimeout(function() {
          window.location.reload();
        }, 1000);
}
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
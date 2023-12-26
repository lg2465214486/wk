<template>
  <div style="text-align: center;">
      <div class="user-body">
          <h1>用户列表</h1>
          <hr>
          <table>
            <tr>
              <td><el-input placeholder="私钥" v-model="listJson.firstKeywords" clearable></el-input></td>
              <td><el-input placeholder="账户" v-model="listJson.lastKeywords" clearable></el-input></td>
              <td><el-button type="primary" @click="getUserList">搜索</el-button></td>
            </tr>
          </table>
          <el-table :data="userList" border style="width: 100%">
            <el-table-column prop="uuid" label="私钥" width="150" >
              <template #default="scope">{{ scope.row.uuid }}</template>
            </el-table-column>
            <el-table-column prop="user" label="账户名" width="150" >
              <template #default="scope">{{ scope.row.userName }}</template>
            </el-table-column>
            <el-table-column prop="name" label="密码" width="150" >
              <template #default="scope">{{ scope.row.pwd }}</template>
            </el-table-column>
            <el-table-column prop="name" label="vip等级" width="150" >
              <template #default="scope">{{ scope.row.vipName }}</template>
            </el-table-column>
            <el-table-column prop="name" label="ustd" width="150" >
              <template #default="scope">{{ scope.row.ustd }}</template>
            </el-table-column>
            <el-table-column prop="name" label="eth" width="150" >
              <template #default="scope">{{ scope.row.eth }}</template>
            </el-table-column>
            <el-table-column prop="name" label="btc" width="150" >
              <template #default="scope">{{ scope.row.btc }}</template>
            </el-table-column>
            <el-table-column prop="name" label="状态" width="150" >
              <template #default="scope">
                <el-text v-if="scope.row.isStop" type="primary">已禁用</el-text>
                <el-text v-if="!scope.row.isStop" type="success">正常</el-text>
              </template>
            </el-table-column>
            <el-table-column prop="name" label="创建时间" width="200" >
              <template #default="scope">{{ scope.row.createdDate }}</template>
            </el-table-column>

            <el-table-column prop="name" label="专属ustd二维码" width="150" >
              <template #default="scope">
                <img v-if="scope.row.ustdQrCode != null && scope.row.ustdQrCode != ''" style="width: 100px;" :src="scope.row.ustdQrCode"/>
              </template>
            </el-table-column>
            <el-table-column prop="name" label="专属ustd充值地址" width="150" >
              <template #default="scope">
                <span v-if="scope.row.ustdAds != null && scope.row.ustdAds != ''">{{ scope.row.ustdAds }}</span>
              </template>
            </el-table-column>

            <el-table-column prop="name" label="专属btc二维码" width="150" >
              <template #default="scope">
                <img v-if="scope.row.btcQrCode != null && scope.row.btcQrCode != ''" style="width: 100px;" :src="scope.row.btcQrCode"/>
              </template>
            </el-table-column>
            <el-table-column prop="name" label="专属btc充值地址" width="150" >
              <template #default="scope">
                <span v-if="scope.row.btcAds != null && scope.row.btcAds != ''">{{ scope.row.btcAds }}</span>
              </template>
            </el-table-column>   

            <el-table-column prop="name" label="专属eth二维码" width="150" >
              <template #default="scope">
                <img v-if="scope.row.ethQrCode != null && scope.row.ethQrCode != ''" style="width: 100px;" :src="scope.row.ethQrCode"/>
              </template>
            </el-table-column>
            <el-table-column prop="name" label="专属eth充值地址" width="150" >
              <template #default="scope">
                <span v-if="scope.row.ethAds != null && scope.row.ethAds != ''">{{ scope.row.ethAds }}</span>
              </template>
            </el-table-column>      

            <el-table-column label="操作" width="450">
              <template #default="scope">
                <el-button size="small" @click="userStart(scope.$index, scope.row)"
                  >启用</el-button
                >
                <el-button
                  size="small"
                  type="danger"
                  @click="userStop(scope.$index, scope.row)"
                  >禁用</el-button
                >
                <el-button
                  size="small"
                  type="success"
                  @click="topUpDialoag(scope.$index, scope.row)"
                  >充值</el-button
                >
                <el-button
                  size="small"
                  type="primary"
                  @click="editUserDailoag(scope.$index, scope.row)"
                  >修改金额和充值通道</el-button
                >
                <el-button
                  size="small"
                  type="primary"
                  @click="editVipDailoag(scope.$index, scope.row)"
                  >修改VIP等级</el-button
                >
              </template>
          </el-table-column>
          </el-table>
          <el-pagination layout="prev, pager, next" :total="total" @current-change="handlePageChange" :current-page="listJson.pageNo" :page-size="listJson.pageSize"/>
      </div>









      <el-dialog v-model="dialogVisible1" title="充值" width="50%">
      <template #footer>
        <div>
          <table>
            <tr>
              <td>币种：</td>
              <td>
                <el-radio-group v-model="topUoJson.bz" class="ml-4">
                <el-radio label="ustd" size="large"></el-radio>
                <el-radio label="btc" size="large"></el-radio>
                <el-radio label="eth" size="large"></el-radio>
                </el-radio-group>
              </td>
            </tr>
            <tr>
              <td>金额：</td>
              <td>
                <el-input v-model="topUoJson.amount"></el-input>
              </td>
            </tr>
          </table>
        </div>
        <span class="dialog-footer">
          <el-button @click="dialogVisible1 = false">退出</el-button>
          <el-button type="primary" @click="topUp">提交</el-button>
        </span>
      </template>
      </el-dialog>


      <el-dialog v-model="dialogVisible2" title="修改金额" width="50%">
      <template #footer>
        <div>
          <table>
            <tr>
              <td>ustd：</td>
              <td>
                <el-input v-model="editUserJson.ustd"></el-input>
              </td>
            </tr>
            <tr>
              <td>btc：</td>
              <td>
                <el-input v-model="editUserJson.btc"></el-input>
              </td>
            </tr>
            <tr>
              <td>eth：</td>
              <td>
                <el-input v-model="editUserJson.eth"></el-input>
              </td>
            </tr>

            <tr>
              <td>专属ustd二维码：</td>
              <td>
                <img  style="width: 150px;" :src="editUserJson.ustdQrCode"/>
                <el-upload
              :limit="1"
              ref="ustdQrCode"
              class="upload-demo"
              action="https://copisp.top/api/multiFile/uploadImage?type=5"
              :auto-upload="false"
              :on-success="ustdUploadSuccess"
            >
              <template #trigger>
                <el-button type="primary">选择文件</el-button>
              </template>

              <el-button class="ml-3" type="success" @click="ustdUpload">点击上传</el-button>
            </el-upload>
              </td>
            </tr>
            <tr>
              <td>专属ustd地址：</td>
              <td>
                <el-input v-model="editUserJson.ustdAds"></el-input>
              </td>
            </tr>


            <tr>
              <td>专属btc二维码：</td>
              <td>
                <img  style="width: 150px;" :src="editUserJson.btcQrCode"/>
                <el-upload
              :limit="1"
              ref="btcQrCode"
              class="upload-demo"
              action="https://copisp.top/api/multiFile/uploadImage?type=5"
              :auto-upload="false"
              :on-success="btcUploadSuccess"
            >
              <template #trigger>
                <el-button type="primary">选择文件</el-button>
              </template>

              <el-button class="ml-3" type="success" @click="btcUpload">点击上传</el-button>
            </el-upload>
              </td>
            </tr>
            <tr>
              <td>专属btc地址：</td>
              <td>
                <el-input v-model="editUserJson.btcAds"></el-input>
              </td>
            </tr>

            <tr>
              <td>专属eth二维码：</td>
              <td>
                <img  style="width: 150px;" :src="editUserJson.ethQrCode"/>
                <el-upload
              :limit="1"
              ref="ethQrCode"
              class="upload-demo"
              action="https://copisp.top/api/multiFile/uploadImage?type=5"
              :auto-upload="false"
              :on-success="ethUploadSuccess"
            >
              <template #trigger>
                <el-button type="primary">选择文件</el-button>
              </template>

              <el-button class="ml-3" type="success" @click="ethUpload">点击上传</el-button>
            </el-upload>
              </td>
            </tr>
            <tr>
              <td>专属eth地址：</td>
              <td>
                <el-input v-model="editUserJson.ethAds"></el-input>
              </td>
            </tr>
          </table>
        </div>
        <span class="dialog-footer">
          <el-button @click="dialogVisible2 = false">退出</el-button>
          <el-button type="primary" @click="editUser">提交</el-button>
        </span>
      </template>
    </el-dialog>

    <el-dialog v-model="userVipDialog" title="修改VIP等级" width="50%">
      <template #footer>
        <div>
          <table>
            <tr>
              <td>等级：</td>
              <td>
                <el-select v-model="editUserJson.vipGrade" placeholder="Select">
                  <el-option
                  v-for="item in vipList"
                    :key="item.id"
                    :label="item.vipName"
                    :value="item.id"
                />
                </el-select>
              </td>
            </tr>
          </table>
        </div>
        <span class="dialog-footer">
          <el-button @click="userVipDialog = false">退出</el-button>
          <el-button type="primary" @click="editUser">提交</el-button>
        </span>
      </template>
    </el-dialog>
    </div>
</template>
  
<script lang="ts" setup>
import { User } from "@element-plus/icons-vue";
import { ref,onMounted } from "vue";
import type { UploadInstance } from 'element-plus'
import { ElMessage } from "element-plus";
import axios, { get, post } from "../js/axiosConfig.js";
const dialogVisible1 = ref(false);
const dialogVisible2 = ref(false);
const userVipDialog = ref(false);
const userList = ref([]);
const vipList = ref([]);
const total = ref(10);
const ustdQrCode = ref<UploadInstance>();
const btcQrCode = ref<UploadInstance>();
const ethQrCode = ref<UploadInstance>();
const listJson = ref({
  firstKeywords:"",
  lastKeywords:"",
  pageNo:1,
  pageSize:10,
});
const topUoJson = ref({
  uuid:"",
  amount:"",
  bz:"ustd"
});
const editUserJson = ref({
  uuid:"",
  ustd:"",
  btc:"",
  eth:"",
  vipGrade:null,
  ustdAds:"",
  ustdQrCode:"",
  btcAds:"",
  btcQrCode:"",
  ethAds:"",
  ethQrCode:""
});

onMounted(() => {
  getUserList();
  getVipList();
})

const ustdUpload = () => {
  if (ustdQrCode.value != null){
    ustdQrCode.value.submit()
  }
}
const ustdUploadSuccess = (response, file, fileList) => {
  editUserJson.value.ustdQrCode = response.data[0]
}

const btcUpload = () => {
  if (btcQrCode.value != null){
    btcQrCode.value.submit()
  }
}
const btcUploadSuccess = (response, file, fileList) => {
  editUserJson.value.btcQrCode = response.data[0]
}

const ethUpload = () => {
  if (ethQrCode.value != null){
    ethQrCode.value.submit()
  }
}
const ethUploadSuccess = (response, file, fileList) => {
  editUserJson.value.ethQrCode = response.data[0]
}




const topUpDialoag = (index, row) => {
  topUoJson.value.uuid = row.uuid
  topUoJson.value.amount = ""
  dialogVisible1.value = true
}

const editVipDailoag = (index, row) => {
  editUserJson.value.uuid = row.uuid
  editUserJson.value.ustd = row.ustd
  editUserJson.value.btc = row.btc
  editUserJson.value.eth = row.eth
  editUserJson.value.vipGrade = row.vipGrade
  editUserJson.value.ustdAds = row.ustdAds
  editUserJson.value.ustdQrCode = row.ustdQrCode
  editUserJson.value.btcAds = row.btcAds
  editUserJson.value.btcQrCode = row.btcQrCode
  editUserJson.value.ethAds = row.ethAds
  editUserJson.value.ethQrCode = row.ethQrCode
  userVipDialog.value = true
}

const editUserDailoag = (index, row) => {
  editUserJson.value.uuid = row.uuid
  editUserJson.value.ustd = row.ustd
  editUserJson.value.btc = row.btc
  editUserJson.value.eth = row.eth
  editUserJson.value.vipGrade = null
  editUserJson.value.ustdAds = row.ustdAds
  editUserJson.value.ustdQrCode = row.ustdQrCode
  editUserJson.value.btcAds = row.btcAds
  editUserJson.value.btcQrCode = row.btcQrCode
  editUserJson.value.ethAds = row.ethAds
  editUserJson.value.ethQrCode = row.ethQrCode
  dialogVisible2.value = true
}

const getVipList = () => {
  get("/qwertyuiop/user/vipList",null)
    .then(response => {
      if (response.data.code == 200) {
        
        vipList.value = response.data.data
        vipList.value.push({
          "id": 0,
          "vipName":"VIP0"
        })
        console.log(vipList.value)
      } else {
        ElMessage.error(response.data.message);
      }
    })
    .catch(error => {
      ElMessage.error(error.response.data.message);
    });
};

const topUp = () => {
  post("/account/topUp", topUoJson.value)
    .then(response => {
      if (response.data.code == 200) {
        ElMessage.success("success");
        getUserList();
        dialogVisible1.value=false
      } else {
        ElMessage.error(response.data.message);
      }
    })
    .catch(error => {
      ElMessage.error(error.response.data.message);
    });
}

const editUser = () => {
  post("/qwertyuiop/user/edit", editUserJson.value)
    .then(response => {
      if (response.data.code == 200) {
        ElMessage.success("success");
        getUserList();
        dialogVisible2.value=false
        userVipDialog.value=false
      } else {
        ElMessage.error(response.data.message);
      }
    })
    .catch(error => {
      ElMessage.error(error.response.data.message);
    });
}

const userStart = (index, row) => {
  get("/qwertyuiop/user/stop", {"uuid" : row.uuid, "isStop" : 0})
    .then(response => {
      if (response.data.code == 200) {
        ElMessage.success("success");
        getUserList();
      } else {
        ElMessage.error(response.data.message);
      }
    })
    .catch(error => {
      ElMessage.error(error.response.data.message);
    });
}

const userStop = (index, row) => {
  get("/qwertyuiop/user/stop", {"uuid" : row.uuid, "isStop" : 1})
    .then(response => {
      if (response.data.code == 200) {
        ElMessage.success("success");
        getUserList();
      } else {
        ElMessage.error(response.data.message);
      }
    })
    .catch(error => {
      ElMessage.error(error.response.data.message);
    });
}

const getUserList = () => {
  listJson.value.firstKeywords = listJson.value.firstKeywords === "" ? null : listJson.value.firstKeywords
  listJson.value.lastKeywords = listJson.value.lastKeywords === "" ? null : listJson.value.lastKeywords
  get("/qwertyuiop/user/all",listJson.value)
    .then(response => {
      if (response.data.code == 200) {
        userList.value = response.data.data.records
        total.value = response.data.data.total
        listJson.value.pageNo = response.data.data.current
      } else {
        ElMessage.error(response.data.message);
      }
    })
    .catch(error => {
      ElMessage.error(error.response.data.message);
    });
};

const handlePageChange = (newPage)=>{
  console.log(newPage);
  listJson.value.pageNo = newPage;
  getUserList();
}
</script>
  
  <style scoped>
  .user-body {
    height: 100vh;
    width: 80%;
    display: inline-block; /* 或者设置为 block 元素 */
    margin: 0 auto;
    margin-left: 2%;
}
</style>
<template>
  <div style="text-align: center;">
      <div class="user-body">
          <h1>用户列表</h1>
          <hr>
          <table>
            <tr>
              <td><el-input placeholder="uuid" v-model="listJson.firstKeywords" clearable></el-input></td>
              <td><el-input placeholder="账户" v-model="listJson.lastKeywords" clearable></el-input></td>
              <td><el-button type="primary" @click="getUserList">搜索</el-button></td>
            </tr>
          </table>
          <el-table :data="userList" border style="width: 100%">
            <el-table-column prop="uuid" label="uuid" width="150" >
              <template #default="scope">{{ scope.row.uuid }}</template>
            </el-table-column>
            <el-table-column prop="user" label="账户名" width="150" >
              <template #default="scope">{{ scope.row.userName }}</template>
            </el-table-column>
            <el-table-column prop="name" label="密码" width="150" >
              <template #default="scope">{{ scope.row.pwd }}</template>
            </el-table-column>
            <el-table-column prop="name" label="手机号" width="150" >
              <template #default="scope">{{ scope.row.phone }}</template>
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
            <el-table-column prop="name" label="邮箱" width="250" >
              <template #default="scope">{{ scope.row.userEmail }}</template>
            </el-table-column>
            <el-table-column label="操作" width="300">
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
                  >修改金额</el-button
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
              <td>金额(USTD)：</td>
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
          </table>
        </div>
        <span class="dialog-footer">
          <el-button @click="dialogVisible2 = false">退出</el-button>
          <el-button type="primary" @click="editUser">提交</el-button>
        </span>
      </template>
    </el-dialog>
    </div>
</template>
  
<script setup>
import { User } from "@element-plus/icons-vue";
import { onMounted,ref } from "vue";
import { ElMessage } from "element-plus";
import axios, { get, post } from "../js/axiosConfig.js";
const dialogVisible1 = ref(false);
const dialogVisible2 = ref(false);
const userList = ref([]);
const total = ref(10);
const listJson = ref({
  firstKeywords:"",
  lastKeywords:"",
  pageNo:1,
  pageSize:10,
});
const topUoJson = ref({
  uuid:"",
  amount:""
});
const editUserJson = ref({
  uuid:"",
  ustd:"",
  btc:"",
  eth:"",
});

onMounted(() => {
  getUserList();
})

const topUpDialoag = (index, row) => {
  topUoJson.value.uuid = row.uuid
  topUoJson.value.amount = ""
  dialogVisible1.value = true
}

const editUserDailoag = (index, row) => {
  editUserJson.value.uuid = row.uuid
  editUserJson.value.ustd = row.ustd
  editUserJson.value.btc = row.btc
  editUserJson.value.eth = row.eth
  dialogVisible2.value = true
}

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
<template>
  <div style="text-align: center;">
    <div class="vip-body">
        <h1>VIP列表</h1>
        <hr>
          <el-table :data="vipList" border style="width: 100%" table-layout="auto">
            <el-table-column prop="vipName" label="VIP等级" width="auto">
              <template #default="scope">{{ scope.row.vipName }}</template>
            </el-table-column>
            <el-table-column prop="wkRate" label="VIP收益率" width="auto" >
              <template #default="scope">{{ scope.row.wkRate }}</template>
            </el-table-column>
            <el-table-column label="操作" width="300">
              <template #default="scope">
                <el-button
                  size="small"
                  type="primary"
                  @click="vipDialoag(scope.$index, scope.row)"
                  >修改费率</el-button
                >
              </template>
          </el-table-column>
          </el-table>
    </div>


    <el-dialog v-model="vipDialog" title="修改VIP收益率" width="50%">
      <template #footer>
        <div>
          <table>
            <tr>
              <td>收益率：</td>
              <td>
                <el-input v-model="vipEdit.vipRate"></el-input>
              </td>
            </tr>
          </table>
        </div>
        <span class="dialog-footer">
          <el-button @click="vipDialog = false">退出</el-button>
          <el-button type="primary" @click="editVip">提交</el-button>
        </span>
      </template>
    </el-dialog>
  </div>
</template>

<script setup>
import { onMounted,ref } from "vue";
import { ElMessage } from "element-plus";
import axios, { get, post } from "../js/axiosConfig.js";
const vipDialog = ref(false);
const vipList = ref([]);

const vipEdit = ref({
  id:0,
  vipRate:0.00,
});

onMounted(()=>{
    getVipList()
})

const vipDialoag = (index, row) => {
    vipEdit.value.id = row.id
    vipEdit.value.vipRate = row.wkRate
    vipDialog.value = true
}

const getVipList = () => {
  get("/qwertyuiop/user/vipList",null)
    .then(response => {
      if (response.data.code == 200) {
        
        vipList.value = response.data.data
        console.log(vipList.value)
      } else {
        ElMessage.error(response.data.message);
      }
    })
    .catch(error => {
      ElMessage.error(error.response.data.message);
    });
};


const editVip = () => {
  post("/qwertyuiop/user/vipEdit", vipEdit.value)
    .then(response => {
      if (response.data.code == 200) {
        ElMessage.success("success");
        getVipList();
        vipDialog.value=false
      } else {
        ElMessage.error(response.data.message);
      }
    })
    .catch(error => {
      ElMessage.error(error.response.data.message);
    });
}
</script>

<style scoped>
.vip-body {
  height: 100vh;
  width: 80%;
  display: inline-block; /* 或者设置为 block 元素 */
  margin: 0 auto;
  margin-left: 2%;
}
</style>
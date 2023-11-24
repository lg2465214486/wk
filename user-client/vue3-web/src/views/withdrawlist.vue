<template>
  <div style="text-align: center;">
    <div class="withdraw-body">
      <h1>提现列表</h1>
      <hr />
      <table>
        <tr>
          <td>
            <el-input placeholder="uuid" v-model="listJson.firstKeywords" clearable></el-input>
          </td>
          <td>
            <el-input placeholder="账户" v-model="listJson.lastKeywords" clearable></el-input>
          </td>
          <td>
            <el-button type="primary" @click="getWithList">搜索</el-button>
          </td>
        </tr>
      </table>
      <el-table :data="withList" border style="width: 100%">
        <el-table-column prop="date" label="uuid" width="auto">
          <template #default="scope">{{ scope.row.uuid }}</template>
        </el-table-column>
        <el-table-column prop="name" label="提现金额" width="auto">
          <template #default="scope">{{ scope.row.sales }}</template>
        </el-table-column>
        <el-table-column prop="date" label="提现TRC20地址" width="auto">
          <template #default="scope">{{ scope.row.trc20Address }}</template>
        </el-table-column>
        <el-table-column prop="date" label="提交时间" width="auto">
          <template #default="scope">{{ scope.row.createdDate }}</template>
        </el-table-column>
        <el-table-column prop="name" label="审核状态" width="auto">
              <template #default="scope">
                <el-text v-if="scope.row.status=='1'" type="primary">审核中</el-text>
                <el-text v-if="scope.row.status=='2'" type="success">通过</el-text>
                <el-text v-if="scope.row.status=='3'" type="danger">未通过</el-text>
              </template>
        </el-table-column>
        <el-table-column label="Operations">
          <template #default="scope">
              <el-button v-if="scope.row.status=='1'" size="small" type="primary" @click="examine(scope.$index, scope.row, 2)">通过</el-button>
              <el-button v-if="scope.row.status=='1'" size="small" type="danger" @click="examine(scope.$index, scope.row, 3)">不通过</el-button>
          </template>
        </el-table-column>
      </el-table>
      <el-pagination layout="prev, pager, next" :total="total" @current-change="handlePageChange" :current-page="listJson.pageNo" :page-size="listJson.pageSize"/>
    </div>
  </div>
</template>
  
<script setup>
import { User } from "@element-plus/icons-vue";
import { onMounted, ref } from "vue";
import { ElMessage } from "element-plus";
import axios, { get, post } from "../js/axiosConfig.js";
const withList = ref([]);
const total = ref(10);
const listJson = ref({
  firstKeywords: "",
  lastKeywords: "",
  pageNo: 1,
  pageSize: 10
});

onMounted(()=>{
  getWithList();
})

const examine = (index, row, status) => {
  get("/qwertyuiop/withdraw/examine", {"id" : row.id, "status" : status})
    .then(response => {
      if (response.data.code == 200) {
        ElMessage.success("success");
        getWithList();
      } else {
        ElMessage.error(response.data.message);
      }
    })
    .catch(error => {
      ElMessage.error(error.response.data.message);
    });
}

const getWithList = () => {
  listJson.value.firstKeywords = listJson.value.firstKeywords === "" ? null : listJson.value.firstKeywords
  listJson.value.lastKeywords = listJson.value.lastKeywords === "" ? null : listJson.value.lastKeywords
  get("/qwertyuiop/withdraw/all",listJson.value)
    .then(response => {
      if (response.data.code == 200) {
        withList.value = response.data.data.records
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
  listJson.value.pageNo = newPage;
  getWithList();
}
</script>
  
  <style scoped>
.withdraw-body {
  height: 100vh;
  width: 80%;
  display: inline-block; /* 或者设置为 block 元素 */
  margin: 0 auto;
  margin-left: 2%;
}
</style>
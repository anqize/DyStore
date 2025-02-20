<script lang="ts" setup>
import { ElMessage, ElMessageBox, ElTable } from 'element-plus'
import { CartApi, deleteApi } from '../../api/loginApi'
import { onMounted, ref } from 'vue';

const tableData = ref([
    {
        id: '',
        name: '',
        description: '',
        price: '',
        createTime: '',
        updateTime: '',
        image: ''
    }
])
const search = async() => {
    const result = await CartApi();
    if(result.code){
        tableData.value = result.data;
    }
}

//未开发
const unopen = () => {
    ElMessage('该功能正在开发中...')
}

//删除购物车中的商品
const deleteCart = async(id) => {
    ElMessageBox.confirm(
    '你确认删除该商品吗',
    '提示',
    {
      confirmButtonText: '确认',
      cancelButtonText: '取消',
      type: 'warning',
    }
  ).then(async () => {
    const result = await deleteApi(id);
    if(result.code){
        ElMessage.success('删除成功');
        search();
    }
    else{
        ElMessage.error(result.msg);
    }
    })
    .catch(() => {
      ElMessage.info('您已取消删除');
    })


}

//选中对象的id
const selectIds = ref([]);

const handleSelectionChange = (selection) => {
  selectIds.value = selection.map(item => item.id);
}

//批量删除
const deleteByIds = async() => {
    if(selectIds.value.length > 0){
        ElMessageBox.confirm(
        '你确认删除这些商品吗',
        '提示',
        {
        confirmButtonText: '确认',
        cancelButtonText: '取消',
        type: 'warning',
        }
    ).then(async () => {
        const result = await deleteApi(selectIds.value);
        if(result.code){
            ElMessage.success('删除成功');
            search();
        }
        else{
            ElMessage.error(result.msg);
        }
        })
        .catch(() => {
        ElMessage.info('您已取消删除');
        })
    }
    else{
        ElMessage.info('请选中商品后再删除')
    }
}

//结算
const pay = () => {
    if(selectIds.value.length > 0){
        ElMessageBox.confirm(
        '你确认结算这些商品吗',
        '提示',
        {
        confirmButtonText: '确认',
        cancelButtonText: '取消',
        type: 'warning',
        }
    ).then(async () => {
        const result = await deleteApi(selectIds.value);
        if(result.code){
            ElMessage.success('结算成功');
            search();
        }
        else{
            ElMessage.error(result.msg);
        }
        })
        .catch(() => {
        ElMessage.info('您已取消结算');
        })
    }
    else{
        ElMessage.info('请选择商品后再进行结算')
    }
}

onMounted(() => {
    search();
})
</script>

<template >
<div style="width: 100%; height: 600px; text-align: left;">
    <el-table :data="tableData" style="width: 80%; margin: 0 auto;" @selection-change="handleSelectionChange" >
        <el-table-column type="selection" width="55" />
        <el-table-column label=" " width="200" >
            <template #default="scope">
                <img :src="scope.row.image" alt="" width="50px" height="50px">
            </template>
        </el-table-column>
        <el-table-column property="name" label="商品名称" width="200" />
        <el-table-column property="description" label="商品描述" width="200" />
        <el-table-column property="createTime" label="加入购物车时间" width="200" />
        <!-- <el-table-column property="updateTime" label="更新时间" width="200" /> -->
        <el-table-column property="price" label="价格" width="120" />
        <el-table-column label="操作" width="200">
            <template #default="scope" style="width: 100%;">
                <el-button type="primary" size="small" @click="unopen">
                    编辑
                </el-button>
                <el-button type="danger" size="small" @click="deleteCart(scope.row.id)">
                    删除
                </el-button>
            </template>
        </el-table-column>
    </el-table>
    <div style="width: 100%; padding-left: 1160px;">
        <el-button @click="deleteByIds" type="danger" plain>批量删除</el-button>
        <el-button @click="pay" type="primary" plain>结算</el-button>
    </div>

</div>
</template>

<style scoped>

</style>

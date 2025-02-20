<script lang="ts" setup>
import { onMounted, ref } from 'vue'
import { addProductApi, PorductApi, PorductCountApi, PorductMsgApi } from '../../api/loginApi'
import { ElMessage } from 'element-plus'


//宣传图
const imgs = ref([
    "src/assets/image/1.png",
    "src/assets/image/2.png"
])
const i = ref(0)
const next = () => {
    if(i.value < imgs.value.length-1){
        i.value++;
    }
}
const pre = () => {
    if(i.value > 0){
        i.value--;
    }
}

//商品
const products = ref([
    {
        id: '',
        name: '',
        description: '',
        categoryId: '',
        price: '',
        stockQuantity: '',
        status: '',
        createTime: '',
        updateTime: '',
        image: ''
    }
])
//加载商品信息
const pMsg = async() =>{
    const result = await PorductMsgApi(currentPage.value-1, pageSize.value);
    if(result.code){
        products.value = result.data;
        total.value = (await PorductCountApi()).data;
    }
}

//分页条
const currentPage = ref(1)
const pageSize = ref(10)
const background = ref(true)

const total = ref(0)

const handleSizeChange = (val) => {
  console.log(`每页展示${val}条记录 `)
  pMsg();
}
const handleCurrentChange = (val) => {
  console.log(`当前页码: ${val}`)
  pMsg();
}

//加入购物车
const addProduct = async(id) => {
  const result = await PorductApi(id);
  if(result.code){
    const p = result.data;
    const r = await addProductApi(p);
    if(r.code){
      ElMessage.success('商品已加入购物车')
    }
    else{
      ElMessage.error(r.msg);
    }
  }
  else{
    ElMessage.error(result.msg);
  }
}

onMounted(() => {
    pMsg();
})
</script>

<template>
  <div style="overflow: hidden;">
      <el-button @click="pre" style="border: none; background: none; opacity:0.5; display: inline-block; position: relative; left: -120px; bottom: 300px;">
          <el-icon size="100px"><ArrowLeft /></el-icon>
      </el-button>
      <img :src="imgs[i]" alt="">
      <el-button @click="next" style="border: none; background: none; opacity:0.5; display: inline-block; position: relative; right: -120px; bottom: 300px;">
          <el-icon size="100px"><ArrowRight /></el-icon>
      </el-button>
  </div>

  <div>
      <el-menu style="display: flex; justify-content: center; width: 100%;">
          <el-menu-item index="1">
            <el-icon><Iphone /></el-icon>
            电子产品
          </el-menu-item>
          <el-menu-item index="2">
            <el-icon><Headset /></el-icon>
            音响与耳机
          </el-menu-item>
          <el-menu-item index="3">
            <el-icon><CameraFilled /></el-icon>
            相机与摄影
          </el-menu-item>
          <el-menu-item index="4">
            <el-icon><Platform /></el-icon>
            电视与家电
          </el-menu-item>
          <el-menu-item index="5">
            <el-icon><Cherry /></el-icon>
            运动与鞋类
          </el-menu-item>
          <el-menu-item index="6">
            <el-icon><Watch /></el-icon>
            手表与配饰
          </el-menu-item>
          <el-menu-item index="6">
            <el-icon><Goods /></el-icon>
            更多分类
          </el-menu-item>
      </el-menu>
      <!-- 商品 -->
      <div style=" padding: 0 120px; display: flex; justify-content: center;flex-wrap: wrap;">
        <el-card shadow="hover" style="margin: 6px 6px;width: 230px;
        height: 305px;" v-for="(p,index) in products" :key="p.id">
          <img :src="p.image" alt="" width="180px" height="180px">
          <h4 style="font-size: 15px; margin-top: 16px;">{{ p.name }}</h4>
          <p style="font-size: 12px; overflow: hidden;">{{ p.description }}</p>
          <h4 style="font-size: 16px; color: red;padding-left: 30px;">￥{{ p.price }}
            <el-button type="primary" style="margin-left: 20px;" @click="addProduct(p.id)">
              <el-icon><ShoppingCart /></el-icon>
            </el-button>
          </h4>
        </el-card>

          <!-- 分页条 -->
          <el-pagination
              v-model:current-page="currentPage"
              v-model:page-size="pageSize"
              :page-sizes="[5,10, 20, 30, 40]"
              :background="background"
              layout="total, sizes, prev, pager, next, jumper"
              :total="total"
              @size-change="handleSizeChange"
              @current-change="handleCurrentChange"
              />
      </div>
  </div>
</template>

<style scoped>
.demo-pagination-block + .demo-pagination-block {
  margin-top: 10px;
}
.demo-pagination-block .demonstration {
  margin-bottom: 16px;
}
</style>

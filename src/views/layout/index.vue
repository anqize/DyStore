<script lang="ts" setup>
import { onMounted, ref } from 'vue'
import { RouterView, useRouter } from 'vue-router'
import { ElMessage } from 'element-plus'
import { LogoutApi } from '../../api/loginApi'

const user = ref({username: '', name: '', token: ''});
const router = useRouter();

//导航
const activeIndex = ref('1')
const handleSelect = (key: string, keyPath: string[]) => {
  console.log(key, keyPath)
}

//退出登录
const logout = async() => {
    const result = await LogoutApi(user)
    router.push('/login')
}

//未开发
const unopen = () => {
    ElMessage('该功能正在开发中...')
}

//跳转到首页
const ToIndex = () => {
    router.push('/index');
}
//跳转到购物车
const ToCart = () => {
    router.push('/cart');
}
</script>

<template>
  <div class="common-layout">
    <el-container>
      <el-header>
        <el-menu
            :default-active="activeIndex"
            class="el-menu-demo"
            mode="horizontal"
            :ellipsis="false"
            @select="handleSelect"
        >
            <el-menu-item index="0">
            <img
                style="width: 100px;height: 100%; width: 100%;"
                src="@/assets/image/Logo.png"
                alt="Element logo"
            />
            </el-menu-item>
            <el-menu-item index="1" @click="ToIndex">首页</el-menu-item>
            <el-menu-item index="2" @click="unopen">社区精选</el-menu-item>
            <el-menu-item index="3" @click="unopen">商家入驻</el-menu-item>
            <el-menu-item index="4" @click="unopen">创作者服务</el-menu-item>
            <el-menu-item index="5" @click="unopen">关于我们</el-menu-item>
            <el-menu-item index="7" @click="ToCart">购物车</el-menu-item>
            <el-sub-menu index="6">
            <template #title>
                <el-avatar> user </el-avatar>
            </template>
            <el-menu-item index="6-1" @click="unopen">个人中心</el-menu-item>
            <el-menu-item index="6-2" @click="unopen">设置</el-menu-item>
            <el-menu-item index="6-3" @click="unopen">我的订单</el-menu-item>
            <el-menu-item index="6-4" @click="unopen">消息</el-menu-item>
            <el-menu-item index="6-5" @click="logout">退出登录</el-menu-item>
            </el-sub-menu>
        </el-menu>
      </el-header>
      
      <el-main style="background-color: #F5F5F5; text-align: center;" >
        <RouterView></RouterView>
      </el-main>

      <el-footer style="background-color: #272727; color: #ffffff;">Footer</el-footer>
    </el-container>
  </div>
</template>

<style scoped>
.el-menu--horizontal > .el-menu-item:nth-child(1) {
  margin-right: auto;
}
</style>

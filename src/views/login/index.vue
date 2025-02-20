<script lang="ts" setup>
import { onMounted, ref, reactive } from "vue";
import { LoginSubmitApi, SignupSubmitApi } from "../../api/loginApi";
import { ElMessage, ElMessageBox,  FormInstance, FormRules } from 'element-plus';
import { useRouter } from "vue-router";

const user = reactive({username: '', password: '', errorMessage: '' });
const router = useRouter();


const checkName = (rule: any, value: any, callback: any) => {
  if (!value || value === '') {
    return callback(new Error('此栏不能为空'))
  }
  else{
    return callback();
  }
}
const checkPhone = (rule: any, value: any, callback: any) => {
  if (!value || value === '') {
    return callback(new Error('此栏不能为空'))
  }
  else{
    return callback();
  }
}
const checkCode = (rule: any, value: any, callback: any) => {
  if (!value || value === '') {
    return callback(new Error('此栏不能为空'))
  }
  else{
    return callback();
  }
}

const validatePass = (rule: any, value: any, callback: any) => {
  if (!value || value === '') {
    callback(new Error('此栏不能为空'))
  } 
  else {
    callback()
  }
}
const validatePass2 = (rule: any, value: any, callback: any) => {
  if (value === '' && ruleForm.password) {
    callback(new Error('请确认密码'))
  }else if(value === '' && ruleForm.password === ''){
    callback(new Error('此栏不能为空'))
  }
   else if (value !== ruleForm.password) {
    callback(new Error("两次输入的密码不一致"))
  } else {
    callback()
  }
}

const ruleForm = reactive({
  username: '',
  password: '',
  checkPass: '',
  phone: '',
  code: ''
})

const rules = reactive<FormRules<typeof ruleForm>>({
  password: [{ validator: validatePass, trigger: 'blur' }],
  checkPass: [{ validator: validatePass2, trigger: 'blur' }],
  username: [{ validator: checkName, trigger: 'blur' }],
  phone: [{ validator: checkPhone, trigger: 'blur' }],
  code: [{ validator: checkCode, trigger: 'blur' }],
})

// 提交登录请求
const Loginsubmit = async() => {
  const result = await LoginSubmitApi(user);
  if(result.code){
    ElMessage.success('登录成功');
    router.push('/index');
  }
  else{
    ElMessage.error(result.msg);
  }
}

//提交注册表单
const submitForm = async() => {
  if(ruleForm.checkPass && ruleForm.code && ruleForm.username && ruleForm.password && ruleForm.phone){
    const result = await SignupSubmitApi(ruleForm);
    if(result.code){
      ElMessage.success('注册成功');
      container.classList.remove("sign-up-mode");
    }
    else{
      ElMessage.error(result.msg);
    }
  }
  else{
    ElMessage.error("请将信息填写完整")
  }
}
//清空注册表单
const resetForm = () => {
  ruleForm.checkPass = '';
  ruleForm.code = '';
  ruleForm.username = '';
  ruleForm.password = '';
  ruleForm.phone = '';
}

// 定义倒计时状态
const isCounting = ref(false); // 判断是否处于倒计时状态
const countdown = ref(60); // 初始倒计时为60秒

// 启动倒计时
const startCountdown = () => {
  isCounting.value = true; // 开始倒计时
  const interval = setInterval(() => {
    countdown.value--; // 每秒减1
    if (countdown.value <= 0) {
      clearInterval(interval); // 停止倒计时
      isCounting.value = false; // 恢复按钮显示
      countdown.value = 60; // 重置倒计时
    }
  }, 1000);
}

onMounted(() => {
  const sign_in_btn = document.querySelector("#sign-in-btn");
    const sign_up_btn = document.querySelector("#sign-up-btn");
    const container = document.querySelector(".container");
    const phone_number = document.querySelector("#phone-number");

    sign_up_btn.addEventListener('click', () => {
      resetForm()
      container.classList.add("sign-up-mode");
    });
    sign_in_btn.addEventListener('click', () => {
      resetForm()
      container.classList.remove("sign-up-mode");
    });

    });
</script>

<template>
  <div class="container">
    <div class="forms-container">
      <div class="signin-signup" id="app">
        <form action="" class="sign-in-form" :v-model="user">
          <b style="margin-top: 20px;">Welcome To DyStore</b>
          <!-- 登录表单 -->
          <h2 class="title">Login</h2>
          <!-- {{ user }} -->
          <el-form
            ref="ruleFormRef"
            style="max-width: 800px"
            :model="user"
            status-icon
            label-width="auto"
            class="demo-ruleForm"
          >
              <!-- 用户名 -->
              <el-form-item label="UserName" prop="username">
                <el-input v-model="user.username" style="width: 300px;"/>
              </el-form-item>
              
              <!-- 密码 -->
              <el-form-item label="Password" prop="password">
                <el-input v-model="user.password" type="password" style="width: 300px;" />
              </el-form-item>
            <el-button type="primary" @click="Loginsubmit" style="width: 200px; margin: 20px 0;">Login</el-button>
          </el-form>


 
          <!-- 使用第三方账号登录 -->
          <p class="social-text">Or Sign in with social platforms</p>
 
          <br>
          <div class="social-media">
            <a href="https://im.qq.com/index/" class="social-icon">
              <img  src="@\assets\image\qq.jpg" STYLE="height: 40px;width:40px">
            </a>
            <a href="https://wx.qq.com/" class="social-icon">
              <img src="@\assets\image\wechat.jpg" STYLE="height: 40px;width:40px">
            </a>
            <a href="https://auth.alipay.com/login/index.htm" class="social-icon">
              <img src="src\assets\image\zhifubao.jpg" STYLE="height: 40px;width:40px">
            </a>
            <a href="https://weibo.com/newlogin?tabtype=weibo&gid=102803&openLoginLayer=0&url=https%3A%2F%2Fwww.weibo.com%2F" class="social-icon">
              <img src="src\assets\image\weibo.jpg" STYLE="height: 40px;width:40px">
            </a>
 
          </div>
        </form>
        <form action="" class="sign-up-form">
          <b style="margin-top: 100px;">Welcome To DyStore</b>
          <!-- 注册表单 -->
          <h2 class="title"> Sign up</h2>
          <el-form
            ref="ruleFormRef"
            style="max-width: 800px"
            :model="ruleForm"
            status-icon
            :rules="rules"
            label-width="auto"
            class="demo-ruleForm"
          >
            <!-- 用户名 -->
            <el-form-item label="UserName" prop="username">
              <el-input v-model="ruleForm.username" style="width: 300px;"/>
            </el-form-item>
            
            <!-- 密码 -->
            <el-form-item label="Password" prop="password">
              <el-input v-model="ruleForm.password" type="password" style="width: 300px;" />
            </el-form-item>

            <!-- 确认密码 -->
            <el-form-item label="Confirm" prop="checkPass">
              <el-input
                v-model="ruleForm.checkPass"
                type="password"
                autocomplete="off"
                style="width: 300px;"
              />
            </el-form-item>

            <!-- 电话号码 -->
            <el-form-item label="Phone" prop="phone">
              <el-input v-model="ruleForm.phone" style="width: 300px;" id="phone-number" />
            </el-form-item>

            <!-- 验证码 -->
            <el-form-item label="Code" prop="code">
              <el-input v-model="ruleForm.code"  style="width: 300px;"/>
            </el-form-item>

            <div style="position: relative; bottom: 44px; left: 120px;">
              <!-- 显示按钮和倒计时 -->
              <button v-if="!isCounting" @click="startCountdown" style="border: none; background-color: #fff;">获取验证码</button>
              <div v-else>{{ countdown }} 秒后重新发送</div>
            </div>
            

            <el-form-item>
              <!-- 提交 -->
              <el-button type="primary" @click="submitForm">
                Submit
              </el-button>
              <!-- 清空 -->
              <el-button @click="resetForm(ruleFormRef)">Reset</el-button>
            </el-form-item>
          </el-form>

          <!-- <input type="submit" value="Sign up" class="btn" style="margin-bottom: 50px;"> -->
          
          <!-- 使用第三方账号注册 -->
          <p class="social-text">Or Sign up with social platforms</p>
          <div class="social-media" style="margin-top: 50px;">
            <a href="https://im.qq.com/index/" class="social-icon">
              <img  src="@\assets\image\qq.jpg" STYLE="height: 40px;width:40px">
            </a>
            <a href="https://wx.qq.com/" class="social-icon">
              <img src="@\assets\image\wechat.jpg" STYLE="height: 40px;width:40px">
            </a>
            <a href="https://auth.alipay.com/login/index.htm" class="social-icon">
              <img src="src\assets\image\zhifubao.jpg" STYLE="height: 40px;width:40px">
            </a>
            <a href="https://weibo.com/newlogin?tabtype=weibo&gid=102803&openLoginLayer=0&url=https%3A%2F%2Fwww.weibo.com%2F" class="social-icon">
              <img src="src\assets\image\weibo.jpg" STYLE="height: 40px;width:40px">
            </a>
          </div>
        </form>
      </div>
    </div>
    <div class="panels-container">
      <div class="panel left-panel">
        <div class="content">
          <h3>
            Don t have an account ?
          </h3>
          <p>if you don t have an account
            <br>Please sign in by using your phone number.
            In this way,you can get an account.
          </p>
          <button class="btn transparent" id="sign-up-btn"> Sign up</button>
        </div>
        <!-- <img src="../assets/pictures/teacher.svg" class="image"> -->
 
      </div>
      <div class="panel right-panel">
        <div class="content">
          <h3>
            Aleady have an account?
          </h3>
          <p>If you aleady have an account,
            <br>Please use your username and password to login.
          </p>
          <button class="btn transparent" id="sign-in-btn">Login</button>
        </div>
        <!-- <img src="../assets/pictures/web.svg" class="image"> -->
 
      </div>
    </div>
  </div>
</template>
 
<style scoped>

</style>

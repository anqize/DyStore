<script lang="ts" setup>
import { onMounted, ref, reactive } from "vue";
import { LoginSubmitApi } from "../../api/loginApi";
import { ElMessage, ElMessageBox,  FormInstance, FormRules } from 'element-plus';

const ruleFormRef = ref<FormInstance>()

let user = ref({username: '', password: '',phone: ''});

// 提交登录请求
const Loginsubmit = async (event) => {
  event.preventDefault();  // 阻止表单提交
  const result = await LoginSubmitApi(user.value);
  if(result.code){
    ElMessage.success('登录成功');
  }
  else{
    ElMessage.error(result.msg);
  }
}

const checkAge = (rule: any, value: any, callback: any) => {
  if (!value) {
    return callback(new Error('此栏不能为空'))
  }
}

const validatePass = (rule: any, value: any, callback: any) => {
  if (value === '') {
    callback(new Error('此栏不能为空'))
  } else {
    if (ruleForm.checkPass !== '') {
      if (!ruleFormRef.value) return
      ruleFormRef.value.validateField('checkPass')
    }
    callback()
  }
}
const validatePass2 = (rule: any, value: any, callback: any) => {
  if (value === '') {
    callback(new Error('请确认密码'))
  } else if (value !== ruleForm.pass) {
    callback(new Error("两次输入的密码不一致"))
  } else {
    callback()
  }
}

const ruleForm = reactive({
  pass: '',
  checkPass: '',
  age: '',
})

const rules = reactive<FormRules<typeof ruleForm>>({
  pass: [{ validator: validatePass, trigger: 'blur' }],
  checkPass: [{ validator: validatePass2, trigger: 'blur' }],
  age: [{ validator: checkAge, trigger: 'blur' }],
})

const submitForm = (formEl: FormInstance | undefined) => {
  if (!formEl) return
  formEl.validate((valid) => {
    if (valid) {
      console.log('submit!')
    } else {
      console.log('error submit!')
    }
  })
}

const resetForm = (formEl: FormInstance | undefined) => {
  if (!formEl) return
  formEl.resetFields()
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

//钩子函数
onMounted(() => {
  const sign_in_btn = document.querySelector("#sign-in-btn");
    const sign_up_btn = document.querySelector("#sign-up-btn");
    const container = document.querySelector(".container");
    const phone_number = document.querySelector("#phone-number");
    sign_up_btn.addEventListener('click', () => {
      container.classList.add("sign-up-mode");
    });
    sign_in_btn.addEventListener('click', () => {
      clearInterval(countdown); // 清除计时器
      phone_number.value = ' ';
      document.getElementById('send-sms-code-btn').style.display = 'inline-block'; // 显示发送按钮
      document.getElementById('countdown').style.display = 'none'; // 隐藏计时器
      container.classList.remove("sign-up-mode");
    });
 
    var countdown; // 计时器变量
    const sendButton = document.querySelector("#send-sms-code-btn");
 
    // 添加点击事件监听器
    sendButton.addEventListener('click', () => {
      // 发送验证码的逻辑，这里使用 setTimeout 模拟异步操作
      event.preventDefault()
      setTimeout(function () {
        // 发送成功后
 
        sendButton.style.display = 'none'; // 隐藏发送按钮
        document.getElementById('countdown').style.display = 'inline-block'; // 显示计时器
        startCountdown(60); // 开始倒计时，时间为60秒
      }, 1000); // 假设发送验证码需要1秒钟
      console.log("anhuqabfh")
    });
 
    function startCountdown(seconds) {
      document.getElementById('countdown').innerText = seconds + ' 秒后重新发送';
      countdown = setInterval(function () {
        seconds--;
        if (seconds > 0) {
          document.getElementById('countdown').innerText = seconds + ' 秒后重新发送';
        } else {
          clearInterval(countdown); // 清除计时器
          document.getElementById('send-sms-code-btn').style.display = 'inline-block'; // 显示发送按钮
          document.getElementById('countdown').style.display = 'none'; // 隐藏计时器
        }
      }, 1000);
    }
})
</script>

<template>
  <div class="container">
    <div class="forms-container">
      <div class="signin-signup" id="app">
        <form action="" class="sign-in-form" :v-model="user">
          <b>Welcome To DyStore</b>
          <!-- 登录表单 -->
          <h2 class="title">Login</h2>
          {{ user }}
          <div class="input-field">
            <div class="el-icon-user-solid"></div>
 
            <input type="username" placeholder="Username" v-model="user.username" >
          </div>
          <div class="input-field">
            <div class="el-icon-key"></div>
            <input type="password" placeholder="Password" v-model="user.password">
          </div>
          <div >
            <button class="btn" @click="Loginsubmit">Login</button>
            
            <!-- <input type="button" value="Login" class="btn"  :click="Loginsubmit"> -->
          </div>
 
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
        <form class="sign-up-form">
          <b>Welcome To DyStore</b>
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
            <el-form-item label="Age" prop="age">
              <el-input v-model="ruleForm.age" style="width: 300px;"/>
            </el-form-item>
            
            <!-- 密码 -->
            <el-form-item label="Password" prop="pass">
              <el-input v-model="ruleForm.pass" type="password" style="width: 300px;" />
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
            <el-form-item label="Age" prop="age">
              <el-input v-model="ruleForm.age" style="width: 300px;"/>
            </el-form-item>
            <!-- 验证码 -->
            <el-form-item label="Age" prop="age">
              <el-input v-model="ruleForm.age"  style="width: 300px;"/>
            </el-form-item>
            <div style="position: relative; bottom: 44px; left: 120px;">
              <!-- 显示按钮和倒计时 -->
              <button v-if="!isCounting" @click="startCountdown" style="border: none; background-color: #fff;">点击开始倒计时</button>
              <div v-else>{{ countdown }} 秒后重新发送</div>
            </div>
            

            <el-form-item>
              <!-- 提交 -->
              <el-button type="primary" @click="submitForm(ruleFormRef)">
                Submit
              </el-button>
              <!-- 清空 -->
              <el-button @click="resetForm(ruleFormRef)">Reset</el-button>
            </el-form-item>
          </el-form>
          
          <el-form
            ref="ruleFormRef"
            style="max-width: 600px"
            :model="ruleForm"
            status-icon
            :rules="rules"
            label-width="auto"
            class="demo-ruleForm"
          >
            <el-form-item label="Password" prop="pass">
              <el-input v-model="ruleForm.pass" type="password" autocomplete="off" />
            </el-form-item>
            <el-form-item label="Confirm" prop="checkPass">
              <el-input
                v-model="ruleForm.checkPass"
                type="password"
                autocomplete="off"
              />
            </el-form-item>
            <el-form-item label="Age" prop="age">
              <el-input v-model.number="ruleForm.age" />
            </el-form-item>
            <el-form-item>
              <el-button type="primary" @click="submitForm(ruleFormRef)">
                Submit
              </el-button>
              <el-button @click="resetForm(ruleFormRef)">Reset</el-button>
            </el-form-item>
          </el-form>

          <!-- 使用第三方账号注册 -->
          <input type="submit" value="Sign up" class="btn" style="margin-bottom: 50px;">
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

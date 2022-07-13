<template>
  <el-container class="home-container">
    <!-- 头部区域 -->
    <el-header>
      <div style="margin-left: 20px;">
        <img src="../assets/img/favicon.png" alt="">
        <span class="logo">后台管理系统</span>
      </div>
      <div class="header-right">
        <div class="header-user-con">
            <!-- 消息中心 -->
            <div class="btn-bell">
                <el-tooltip effect="dark" :content="message?`有${message}条未读消息`:`消息中心`" placement="bottom">
                    <router-link to="/message">
                        <i class="el-icon-bell"></i>
                    </router-link>
                </el-tooltip>
                <span class="btn-bell-badge" v-if="message"></span>
            </div>
            <!-- 用户头像 -->
            <div class="block">
              <el-avatar icon="el-icon-user-solid" :size="50" :src="circleUrl"></el-avatar>
            </div>
            <!-- 用户名下拉菜单 -->
            <el-dropdown class="user-name" trigger="click" @command="handleCommand">
                <span class="el-dropdown-link">
                    {{username}}
                    <i class="el-icon-caret-bottom"></i>
                </span>
                <template #dropdown>
                    <el-dropdown-menu>
                        <el-dropdown-item command="user">个人中心</el-dropdown-item>
                        <el-dropdown-item divided command ="loginout">退出登录</el-dropdown-item>
                    </el-dropdown-menu>
                </template>
            </el-dropdown>
        </div>
      </div>
    </el-header>
    <!-- 页面主体区域 -->
    <el-container>
      <!-- 侧边栏 -->
      <el-aside :width="isCollapse ? '64px' : '200px'">
        <div class="toggle-button" @click="toggleCollapse">|||</div>
        <!-- 侧边栏菜单区域 -->
        <el-menu background-color="#333744" text-color="#fff" active-text-color="#409EFF" unique-opened :collapse="isCollapse" :collapse-transition="false" router :default-active="activePath">
          <!-- 一级菜单 -->
          <el-submenu :index="item.id + ''" v-for="item in menulist" :key="item.id">
            <!-- 一级菜单的模板区域 -->
            <template slot="title">
              <!-- 图标 -->
              <i :class="iconsObj[item.id]"></i>
              <!-- 文本 -->
              <span>{{item.authName}}</span>
            </template>

            <!-- 二级菜单 -->
            <el-menu-item :index="'/' + subItem.path" v-for="subItem in item.children" :key="subItem.id" @click="saveNavState('/' + subItem.path)">
              <template slot="title">
                <!-- 图标 -->
                <i class="el-icon-menu"></i>
                <!-- 文本 -->
                <span>{{subItem.authName}}</span>
              </template>
            </el-menu-item>
          </el-submenu>
        </el-menu>
      </el-aside>
      <!-- 右侧内容主体 -->
      <el-main>
        <!-- 路由占位符 -->
        <router-view></router-view>
      </el-main>
    </el-container>
  </el-container>
</template>

<script>
export default {
  data() {
    return {
      // 左侧菜单数据
      menulist: [
        {
          authName:"用户管理",
          id: 125,
          order: 1,
          path: "/users",
          children:[{
            authName: "用户列表",
            children: [],
            id: 110,
            order: null,
            path: "users"
          }]
        },
        {
          authName:"权限管理",
          id: 103,
          order: 2,
          path: "rights",
          children:[{
            authName: "角色列表",
            children: [],
            id: 112,
            order: null,
            path: "roles"
          },{
            authName: "权限列表",
            children: [],
            id: 113,
            order: null,
            path: "rights"
          }
          ]
        },
        {
          authName:"文章管理",
          id: 102,
          order: 4,
          path: "blog",
          children:[{
            authName: "文章列表",
            children: [],
            id: 127,
            order: null,
            path: "blog"
          },{
            authName: "标签列表",
            children: [],
            id: 137,
            order: null,
            path: "tag"
          },{
            authName: "分类列表",
            children: [],
            id: 147,
            order: null,
            path: "type"
          }]
        },
        {
          authName:"友链管理",
          id: 202,
          order: 5,
          path: "friendLink",
          children:[{
            authName: "友链列表",
            children: [],
            id: 207,
            order: null,
            path: "friendLink"
          }]
        },
        {
          authName:"图库管理",
          id: 302,
          order: 6,
          path: "picture",
          children:[{
            authName: "图片列表",
            children: [],
            id: 307,
            order: null,
            path: "picture"
          }]
        },
        {
          authName:"数据统计",
          id: 145,
          order: 7,
          path: "reports",
          children:[{
            authName: "数据报表",
            children: [],
            id: 146,
            order: null,
            path: "reports"
          }]
        }
      ],
      iconsObj: {
        '125': 'iconfont icon-user',
        '103': 'iconfont icon-tijikongjian',
        '101': 'iconfont icon-shangpin',
        '102': 'iconfont icon-danju',
        '202': 'iconfont icon-danju',
        '302': 'iconfont icon-danju',
        '145': 'iconfont icon-baobiao'
      },
      // 是否折叠
      isCollapse: false,
      // 被激活的链接地址
      activePath: '',
      username: '',
      message: 2,
      circleUrl: '',
    }
  },
  created() {
    this.circleUrl = window.sessionStorage.getItem('avatar'),
    this.username = window.sessionStorage.getItem('nickname'),
    this.activePath = window.sessionStorage.getItem('activePath')
  },
  methods: {
    logout() {
      window.sessionStorage.clear()
      this.$router.push('/login')
    },
    // 点击按钮，切换菜单的折叠与展开
    toggleCollapse() {
      this.isCollapse = !this.isCollapse
    },
    // 保存链接的激活状态
    saveNavState(activePath) {
      window.sessionStorage.setItem('activePath', activePath)
      this.activePath = activePath
    },
    handleCommand(command){
      if (command == "loginout") {
          window.sessionStorage.clear()
          this.$router.push('/login')
      } else if (command == "user") {
          this.$router.push("/users");
      }
    }
  }
}
</script>

<style lang="less" scoped>
.home-container {
  height: 100%;
}
.el-header {
  background-color:#242f42;
  display: flex;
  justify-content: space-between;
  padding-left: 0;
  align-items: center;
  color: #fff;
  font-size: 20px;
  > div {
    display: flex;
    align-items: center;
    span {
      margin-left: 15px;
    }
  }
}

.el-aside {
  background-color: #324157;
  .el-menu {
    border-right: none;
  }
}

.el-main {
  background-color: #eaedf1;
}

.iconfont {
  margin-right: 10px;
}

.toggle-button {
  background-color: #4a5064;
  font-size: 10px;
  line-height: 24px;
  color: #fff;
  text-align: center;
  letter-spacing: 0.2em;
  cursor: pointer;
}
.logo {
    float: left;
    width: 250px;
    line-height: 70px;
}
.header-right {
    float: right;
    padding-right: 50px;
}
.header-user-con {
    display: flex;
    height: 70px;
    align-items: center;
}
.btn-fullscreen {
    transform: rotate(45deg);
    margin-right: 5px;
    font-size: 24px;
}
.btn-bell,
.btn-fullscreen {
    position: relative;
    width: 30px;
    height: 30px;
    text-align: center;
    border-radius: 15px;
    cursor: pointer;
}
.btn-bell-badge {
    position: absolute;
    right: 0;
    top: -2px;
    width: 8px;
    height: 8px;
    border-radius: 4px;
    background: #f56c6c;
    color: #fff;
}
.btn-bell .el-icon-bell {
    color: #fff;
}
.user-name {
    margin-left: 10px;
}
.user-avator {
    margin-left: 20px;
}
.user-avator img {
    display: block;
    width: 40px;
    height: 40px;
    border-radius: 50%;
}
.el-dropdown-link {
    color: #fff;
    cursor: pointer;
}
</style>

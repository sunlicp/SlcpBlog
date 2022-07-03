import Vue from 'vue'
import Router from 'vue-router'
Vue.use(Router)

const Login = () => import(/* webpackChunkName: "login_home_welcome" */ './components/Login.vue')

const Home = () => import(/* webpackChunkName: "login_home_welcome" */ './components/Home.vue')

const Welcome = () => import(/* webpackChunkName: "login_home_welcome" */ './components/Welcome.vue')

const NoRight = () => import(/* webpackChunkName: "login_home_welcome" */ './components/NoRight.vue')

const Users = () => import(/* webpackChunkName: "Users_Rights_Roles" */ './components/user/Users.vue')

const Rights = () => import(/* webpackChunkName: "Users_Rights_Roles" */ './components/power/Rights.vue')

const Roles = () => import(/* webpackChunkName: "Users_Rights_Roles" */ './components/power/Roles.vue')

const Abnormals = () => import(/* webpackChunkName: "Cate_Params" */ './components/attendance/Abnormals.vue')

const Add = () => import(/* webpackChunkName: "GoodsList_Add" */ './components/attendance/Add.vue')

const Report = () => import(/* webpackChunkName: "Order_Report" */ './components/report/Report.vue')

const router = new Router({
  mode: 'history',
  routes: [
    { path: '/', redirect: '/login' },
    { path: '/login', component: Login },
    {
      path: '/home',
      component: Home,
      redirect: '/welcome',
      children: [
        { path: '/welcome', component: Welcome },
        { path: '/no', component: NoRight },
        { path: '/users', component: Users },
        { path: '/rights', component: Rights },
        { path: '/roles', component: Roles },
        { path: '/abnormals', component: Abnormals },
        { path: '/abnormals/add', component: Add },
        { path: '/reports', component: Report }
      ]
    }
  ]
})

router.beforeEach((to, from, next) => {
  if (to.path === '/login') return next()
  const tokenStr = window.sessionStorage.getItem('token')
  if (!tokenStr) return next('/login')
  next()
})

export default router

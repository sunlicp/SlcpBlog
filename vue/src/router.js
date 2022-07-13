import Vue from 'vue'
import Router from 'vue-router'
Vue.use(Router)

const Login = () => import('./components/Login.vue')

const Home = () => import('./components/Home.vue')

const Welcome = () => import('./components/Welcome.vue')

const NoRight = () => import('./components/NoRight.vue')

const Users = () => import('./components/user/Users.vue')

const Message = () => import('./components/message/Message.vue')

const Rights = () => import('./components/power/Rights.vue')

const Roles = () => import('./components/power/Roles.vue')

const Blog = () => import('./components/blog/Blog.vue')

const Type = () => import('./components/blog/Type.vue')

const Tag = () => import('./components/blog/Tag.vue')

const Add = () => import('./components/blog/Add.vue')

const Picture = () => import('./components/picture/Picture.vue')

const FriendLink = () => import('./components/friendLink/FriendLink.vue')

const Report = () => import('./components/report/Report.vue')

const router = new Router({
  mode: 'history',
  base:"/admin",
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
        { path: '/blog', component: Blog },
        { path: '/type', component: Type },
        { path: '/tag', component: Tag },
        { path: '/blog/add', component: Add },
        { path: '/picture', component: Picture },
        { path: '/friendLink', component: FriendLink },
        { path: '/reports', component: Report },
        { path: '/message', component: Message }
      ]
    }
  ]
})

router.beforeEach((to, from, next) => {
  if (to.path === '/login') return next()
  const tokenStr = window.sessionStorage.getItem('token')
  if(tokenStr == 'undefined' || !tokenStr)  return next('/login')
  next()
})

export default router

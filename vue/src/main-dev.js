import Vue from 'vue'
import App from './App.vue'
import router from './router'
import './plugins/element.js'
import './assets/fonts/iconfont.css'
import './assets/css/global.css'
import TreeTable from 'vue-table-with-tree-grid'
import VueQuillEditor from 'vue-quill-editor'
import 'quill/dist/quill.core.css'
import 'quill/dist/quill.snow.css'
import 'quill/dist/quill.bubble.css'
import NProgress from 'nprogress'
import 'nprogress/nprogress.css'
import mavonEditor from 'mavon-editor'
import 'mavon-editor/dist/css/index.css'
import axios from 'axios'

axios.defaults.baseURL = 'https://slcp.top/sys/'

axios.interceptors.request.use(config => {
  NProgress.start()
  config.headers.Authorization = window.sessionStorage.getItem('id')+"_"+window.sessionStorage.getItem('token')
  return config
})

axios.interceptors.response.use(config => {
  NProgress.done()
  return config
})

// use
Vue.use(mavonEditor)
Vue.use(VueQuillEditor)
Vue.config.productionTip = false
Vue.prototype.$http = axios

Vue.component('tree-table', TreeTable)

Vue.filter('dateFormat', function(originVal) {
  const dt = new Date(originVal)

  const y = dt.getFullYear()
  const m = (dt.getMonth() + 1 + '').padStart(2, '0')
  const d = (dt.getDate() + '').padStart(2, '0')

  const hh = (dt.getHours() + '').padStart(2, '0')
  const mm = (dt.getMinutes() + '').padStart(2, '0')
  const ss = (dt.getSeconds() + '').padStart(2, '0')

  return `${y}-${m}-${d} ${hh}:${mm}:${ss}`
})

new Vue({
  router,
  render: h => h(App)
}).$mount('#app')

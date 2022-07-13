import Vue from 'vue'
import App from './App.vue'
import './assets/css/global.css'
import router from './router'
import NProgress from 'nprogress'
import TreeTable from 'vue-table-with-tree-grid'

import VueQuillEditor from 'vue-quill-editor'
import './assets/fonts/iconfont.css'

import axios from 'axios'

axios.defaults.baseURL = 'https://slcp.top/sys/'
axios.interceptors.request.use(config => {
  
  NProgress.start()
  config.headers.Authorization = window.sessionStorage.getItem('token')
  
  return config
})

Vue.config.productionTip = false

axios.interceptors.response.use(config => {
  NProgress.done()
  return config
})

Vue.prototype.$http = axios

Vue.use(VueQuillEditor)

Vue.component('tree-table', TreeTable)

new Vue({
  router,
  render: h => h(App)
}).$mount('#app')

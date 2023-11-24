import { createApp } from 'vue'
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import App from './App.vue'
import router from './router';
import * as ElementPlusIconsVue from '@element-plus/icons-vue'

const app = createApp(App).use(ElementPlus).use(router).mount('#app')
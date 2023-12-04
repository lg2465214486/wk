import { createApp } from 'vue'
import { createI18n } from 'vue-i18n'
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import App from './App.vue'
import router from './router';
import en from './i18n/en.json';
import zh from './i18n/zh.json';
import es from './i18n/es.json';
import th from './i18n/th.json';
import ko from './i18n/ko.json';
import ja from './i18n/ja.json';
import ar from './i18n/ar.json';
import * as ElementPlusIconsVue from '@element-plus/icons-vue'

const i18n = createI18n({
    locale: 'en', // 默认语言
    messages: {
      en,
      zh,
      es,
      th,
      ko,
      ja,
      ar,
    },
});

if (localStorage.getItem('i18n')){
    i18n.global.locale = localStorage.getItem('i18n');
}

const app = createApp(App).use(ElementPlus).use(router).use(i18n).mount('#app')
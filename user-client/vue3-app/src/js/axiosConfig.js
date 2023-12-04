// axiosConfig.js

import axios from 'axios';

// const baseURL = 'http://127.0.0.1:8081'; 
// const baseURL = 'http://192.168.1.15:8081'; 
const baseURL = 'https://copisp.top/api';

const instance = axios.create({
  baseURL,
  timeout: 60000, // 设置请求超时时间
  // 在此添加其他公共配置项
});

// GET 方法
export const get = (url, params = {}) => {
  return instance.get(url, {params, headers: {'X-Token': sessionStorage.getItem('userToken')}});
};

// POST 方法
export const post = (url, data = {}) => {
  return instance.post(url, data,{
    headers: {
        'Content-Type': 'application/json',
        'X-Token': sessionStorage.getItem('userToken')
      }
  });
};

export default instance;

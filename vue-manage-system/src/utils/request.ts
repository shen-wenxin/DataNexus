import axios, {AxiosInstance, AxiosError, AxiosResponse, AxiosRequestConfig} from 'axios';
import { useRestoreActive } from 'element-plus';

const service:AxiosInstance = axios.create({
    timeout: 5000
});

service.interceptors.request.use(
    (config: AxiosRequestConfig) => {
        if (config.method === 'post') {
            // 检查 config.headers 是否已定义
            if (config.headers) {
              config.headers['Content-Type'] = 'application/json; charset=UTF-8';
            } else {
              config.headers = {
                'Content-Type': 'application/json; charset=UTF-8'
              };
            }
        }
        return config;
    },
    (error: AxiosError) => {
        console.log(error);
        return Promise.reject();
    }
);

service.interceptors.response.use(
    (response: AxiosResponse) => {
        if (response.status === 200 ||
            response.status === 201) {
            return response;
        } else {
            Promise.reject();
        }
    },
    (error: AxiosError) => {
        console.log(error);
        if (error.response?.status === 409) {
            return error.response;

        }
        return Promise.reject();
    }
);

export default service;

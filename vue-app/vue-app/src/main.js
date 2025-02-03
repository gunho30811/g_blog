import { createApp } from 'vue'
import App from './App.vue'
// Font Awesome CSS import
import '@fortawesome/fontawesome-free/css/all.css';
import '@fortawesome/fontawesome-free/js/all.js';
import VueDatePicker from '@vuepic/vue-datepicker';
import '@vuepic/vue-datepicker/dist/main.css'
const app = createApp(App); // 앱 인스턴스를 변수에 저장
app.component('VueDatePicker', VueDatePicker); // 전역 컴포넌트 등록
app.mount('#app'); // 앱을 마운트

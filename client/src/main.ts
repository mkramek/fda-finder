import { createApp } from 'vue';
import App from './App.vue';
import { key, store } from "./store";
import Toast, { POSITION } from "vue-toastification";

import 'vue-toastification/dist/index.css';
import './style.css';

store.subscribe((mutation, state) => {
    localStorage.setItem('is_theme_dark', JSON.stringify(state.dark));
});

createApp(App)
    .use(store, key)
    .use(Toast, {
        closeOnClick: true,
        position: POSITION.TOP_RIGHT,
        timeout: 2000,
        draggable: false
    })
    .mount('#app')

import "./assets/main.css";
import "primeicons/primeicons.css";
import "flowbite";

import { createApp } from "vue";
import App from "./App.vue";
import router from "./router";

process.env.Key = "";

const app = createApp(App);

app.use(router);

app.mount("#app");

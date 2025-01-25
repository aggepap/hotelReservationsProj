import "./assets/main.css";
import "primeicons/primeicons.css";
import "flowbite";

import { createApp } from "vue";
import App from "./App.vue";
import { plugin, defaultConfig } from "@formkit/vue";
import router from "./router";

const app = createApp(App);

app.use(router);

app.mount("#app");

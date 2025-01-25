import "./assets/main.css";
import "primeicons/primeicons.css";
import "flowbite";
import Toast from "vue-toastification";
import "vue-toastification/dist/index.css";

import { createApp } from "vue";
import App from "./App.vue";
import { plugin, defaultConfig } from "@formkit/vue";
import router from "./router";
import config from "../formkit.config";

const app = createApp(App);

app.use(router);
app.use(plugin, defaultConfig(config));

app.mount("#app");

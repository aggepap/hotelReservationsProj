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
import PrimeVue from "primevue/config";
import Aura from "@primevue/themes/aura";

const app = createApp(App);

app.use(router);
app.use(plugin, defaultConfig(config));
app.use(Toast);
app.use(PrimeVue, {
  theme: {
    preset: Aura,
  },
});

app.mount("#app");

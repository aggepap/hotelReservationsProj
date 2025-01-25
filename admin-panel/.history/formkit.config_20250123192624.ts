import { el } from "@formkit/i18n";
import { defaultConfig } from "@formkit/vue";
import { rootClasses } from "./formkit.theme";

export default defaultConfig({
  config: {
    rootClasses,
  },
  locales: { el },
  locale: "gr",
});

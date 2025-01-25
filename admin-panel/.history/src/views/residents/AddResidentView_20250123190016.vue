<script setup>
import { FormKit } from "@formkit/vue";
import type { text } from "@fortawesome/fontawesome-svg-core";
</script>
<template>
  <FormKit
    :classes="{
      outer: {
        wrapper: 'mb-6',
        text: 'text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500 dark:shadow-sm-light',
      },
    }"
    type="text"
    name="firstName"
    label="First Name"
    validation="required | min:2 | max:20"
    validation-visibility="live"
  />
</template>

<script setup>
import { FormKit } from "@formkit/vue";
</script>
<template>
  <FormKit label-class: "text-gray-900 dark:text-white" type="text"
  name="firstName" label="First Name" validation="required | min:2 | max:20"
  validation-visibility="live" />
</template>

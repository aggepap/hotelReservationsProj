<script setup>
import { FormKit } from "@formkit/vue";
</script>
<template>
  <FormKit
    label-class="text-black font-semibold text-lg"
    type="text"
    name="firstName"
    label="First Name"
    validation="required | min:2 | max:20"
    validation-visibility="live"
  />
</template>

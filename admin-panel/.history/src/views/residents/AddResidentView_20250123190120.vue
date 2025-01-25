<script setup>
import { FormKit } from "@formkit/vue";
</script>
<template>
  <FormKit
    :classes="{
      outer: {
        wrapper: 'mb-6 text-black',
        inner: 'flex flex-col text-black',
      },
    }"
    type="text"
    name="firstName"
    label="First Name"
    validation="required | min:2 | max:20"
    validation-visibility="live"
  />
</template>

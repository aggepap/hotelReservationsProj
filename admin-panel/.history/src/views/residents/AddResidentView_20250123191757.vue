<script setup>
import { FormKit } from "@formkit/vue";
</script>
<template>
  <h2 class="text-black text-3xl text-center leading-5 font-semibold mb-8">
    Add new Resident
  </h2>
  <div class="bg-white w-6/12 rounded-md"></div>
  <FormKit type="form">
    <FormKit
      label-class="text-black font-semibold text-lg"
      type="text"
      name="firstName"
      label="First Name"
      validation="required | min:2 | max:20"
      validation-visibility="live"
    />
  </FormKit>
</template>

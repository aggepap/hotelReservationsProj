<script setup>
import { FormKit } from "@formkit/vue";
</script>
<template>
  <h2 class="text-black text-3xl text-center leading-5 font-semibold mb-8">
    Add new Resident
  </h2>
  <div class="bg-white w-6/12 rounded-md">
    <FormKit type="form">
      <FormKit
        type="text"
        name="firstName"
        label="First Name"
        validation="required | length:3,20"
        validation-visibility="blur"
      />
      <FormKit
        type="text"
        name="lastname"
        label="Last Name"
        validation="required | length:3,30"
        validation-visibility="blur"
      />
      <FormKit
        type="number"
        name="vat"
        label="VAT Number"
        validation="required | number |length:10,10"
        validation-visibility="blur"
      />
    </FormKit>
  </div>
</template>

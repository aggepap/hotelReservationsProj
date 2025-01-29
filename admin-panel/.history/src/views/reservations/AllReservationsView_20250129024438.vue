<script setup>
import { ref, computed } from "vue";

const date = new Date();

const formattedDate = (daysAdded = 0) => {
  const newDate = new Date(date.value);
  date.setDate(date.getDate() + daysAdded);

  const year = date.getFullYear();
  const month = String(date.getMonth() + 1).padStart(2, "0");
  const day = String(date.getDate()).padStart(2, "0");

  return `${year}-${month}-${day}`;
};
</script>

<template>
  <section class="grid place-items-center">
    <h2 class="text-black text-3xl text-center leading-5 font-semibold mb-8">
      Reservations
    </h2>

    <FormKit type="form" :actions="false" submit-label="Search">
      <div class="flex justify-end items-end gap-2">
        <FormKit
          type="date"
          :value="formattedDate(0)"
          label="From"
          validation="required|"
          validation-visibility="live"
        />
        <FormKit
          type="date"
          :value="formattedDate(10)"
          label="Since"
          validation="required"
          validation-visibility="live"
        />
        <FormKit type="submit" label="Submit" />
      </div>
    </FormKit>
  </section>
</template>

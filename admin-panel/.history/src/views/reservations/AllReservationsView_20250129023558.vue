<script setup>
import { ref, computed } from "vue";

const date = ref(new Date());

const formattedDate = (daysAdded = 0) => {
  const newDate = new Date(date.value);
  date.value.setDate(date.value.getDate() + daysAdded);

  const year = date.value.getFullYear();
  const month = String(date.value.getMonth() + 1).padStart(2, "0");
  const day = String(date.value.getDate()).padStart(2, "0");

  return `${year}-${month}-${day}`;
};

console.log(formattedDate(7));
</script>

<template>
  <section class="grid place-items-center">
    <h2 class="text-black text-3xl text-center leading-5 font-semibold mb-8">
      Reservations
    </h2>

    <FormKit type="form" submit-label="Search">
      <div class="flex gap-2">
        <FormKit
          type="date"
          :value="formattedDate(0)"
          label="From"
          validation="required|"
          validation-visibility="live"
        />
        <FormKit
          type="date"
          :value="formattedDate(7)"
          label="Since"
          validation="required"
          validation-visibility="live"
        />
      </div>
    </FormKit>
  </section>
</template>

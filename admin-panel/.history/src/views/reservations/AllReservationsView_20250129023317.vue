<script setup>
import { ref, computed } from "vue";

const date = ref(new Date());

const formattedDate = (daysAdded = 0) => {
  // Create a new date object to avoid modifying the original
  const newdate = new Date(date.value);

  // Add the specified number of days
  date.setDate(date.getDate() + daysAdded);

  // Extract year, month, and day
  const year = date.getFullYear();
  const month = String(date.getMonth() + 1).padStart(2, "0"); // Months are 0-indexed
  const day = String(date.getDate()).padStart(2, "0");

  // Return the formatted date string
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
          :value="formattedDate()"
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

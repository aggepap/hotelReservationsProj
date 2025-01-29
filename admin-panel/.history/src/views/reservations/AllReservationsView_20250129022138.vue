<script setup lang="ts">
import { ref, computed } from "vue";

const date = ref(new Date());
const formattedDate = computed((daysAdded) => {
  const year = date.value.getFullYear();
  const month = date.value.toLocaleString("en-US", {
    month: "2-digit",
  });
  const day = date.value.getDate();
  return `${year}-${month}-${day}`;
});

const formattedFutureDate = computed(() => {
  const year = date.value.getFullYear();
  const month = date.value.toLocaleString("en-US", {
    month: "2-digit",
  });
});
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
          value="{{formattedDate}}"
          label="From"
          validation="required|"
          validation-visibility="live"
        />
        <FormKit
          type="date"
          value="{{formattedDate}}"
          label="Since"
          validation="required|date_after"
          validation-visibility="live"
        />
      </div>
    </FormKit>
  </section>
</template>

<script setup>
const date = new Date();
const nowTime = `${date.getFullYear()}-${
  date.getMonth() + 1
}-${date.getDate()}`;
console.log(nowTime);
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
          value="2020-1-04"
          label="End of the world"
          help="When will the end of the world take place?"
          validation="required|date_after"
          validation-visibility="live"
        />
        <FormKit
          type="datetime-local"
          value="2020-03-13T18:22"
          label="End of the world"
          help="When will the end of the world take place?"
          validation="required|date_after"
          validation-visibility="live"
        />
      </div>
    </FormKit>
  </section>
</template>

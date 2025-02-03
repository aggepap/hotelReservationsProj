<script setup>
import AddReservationForm from "@/components/AddReservationForm.vue";
import { ref, onMounted } from "vue";

const isVisible = ref(false);

onMounted(async () => {
  isVisible.value = true;
});
</script>
<template>
  <Transition name="slide-fade">
    <AddReservationForm v-if="isVisible" />
  </Transition>
</template>

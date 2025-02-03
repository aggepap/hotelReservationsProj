<script setup lang="ts">
import AddResidentForm from "@/components/AddResidentForm.vue";
import { ref, onMounted } from "vue";
const isVisible = ref(false);
onMounted(async () => {
  isVisible.value = true;
});
</script>
<template>
  <Transition name="slide-fade">
    <AddResidentForm v-if="isVisible" />
  </Transition>
</template>

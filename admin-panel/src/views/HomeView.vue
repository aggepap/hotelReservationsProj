<script setup lang="ts">
import { onMounted } from "vue";

onMounted(async () => {
  try {
    const response = await fetch("http://localhost:8080/api/hello");
    if (!response.ok) {
      throw new Error("Network response was not ok");
    }
    console.log(response.text());
  } catch (error) {
    console.error("Error fetching data:", error);
  }
});
</script>

<template></template>

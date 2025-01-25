<script setup lang="ts">
import TheWelcome from "../components/TheWelcome.vue";
import { FontAwesomeIcon } from "@fortawesome/vue-fontawesome";
import { faDoorOpen } from "@fortawesome/free-solid-svg-icons";
import { faHotel } from "@fortawesome/free-solid-svg-icons";
import { faBed } from "@fortawesome/free-solid-svg-icons";
import { faKey } from "@fortawesome/free-solid-svg-icons";
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

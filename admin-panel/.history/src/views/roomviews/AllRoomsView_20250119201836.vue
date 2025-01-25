<script setup lang="ts">
import { RoomService } from "@/services/ApiServices";
import { ref, onMounted } from "vue";

const rooms = ref([]);
let pageIndex = 0;
let pageSize = 10;
onMounted(async () => {
  rooms.value = await RoomService.getRooms();
  console.log(rooms.value);
});
</script>

<template>
  <h2 class="text-black text-3xl text-center leading-5 font-semibold mb-8">
    Hotel Rooms
  </h2>
  <section
    class="w-9/12 mx-auto px-10 h-screen grid grid-cols-4 md:grid-cols-2 lg:grid-cols-3 gap-10"
  ></section>
</template>

<script setup lang="ts">
import { RoomService } from "@/services/ApiServices";
import { reactive, onMounted } from "vue";
import type { Room } from "@/interfaces/RoomInterfaces";

const state = reactive<{
  rooms: Room[];
  loading: boolean;
}>({
  rooms: [],
  loading: true,
});

let pageIndex = 0;
let pageSize = 10;
onMounted(async () => {
  try {
    state.rooms = await RoomService.getRooms(pageIndex, pageSize);
    console.log(state.rooms);
  } catch (error) {
    console.error("Error Fetching rooms", error);
  } finally {
    state.loading = false;
  }
});
</script>

<template>
  <h2 class="text-black text-3xl text-center leading-5 font-semibold mb-8">
    Hotel Rooms
  </h2>
  <section
    class="w-9/12 mx-auto px-10 h-screen grid grid-cols-4 md:grid-cols-2 lg:grid-cols-3 gap-10"
  >
    <div v-for="room in state.rooms" :key="room.roomNumber" :room="room">
      <div class="bg-white rounded-lg shadow-md p-4">
        <h3 class="text-lg text-black font-semibold mb-2">
          Room {{ room.roomNumber }}
        </h3>
      </div>
    </div>
  </section>
</template>

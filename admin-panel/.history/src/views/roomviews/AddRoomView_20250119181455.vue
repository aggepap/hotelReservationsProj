<script setup>
import { reactive } from "vue";
import { RoomService } from "@/services/ApiServices";

const formData = reactive({
  roomNumber: "",
  floor: "",
  capacity: "4",
  hasSeaview: "",
});

const handleSubmit = () => {
  RoomService.addRoom(formData);
  console.log(formData);
};
</script>

<template>
  <h2 class="text-black text-3xl text-center leading-5 font-semibold mb-8">
    Add New Room
  </h2>
  <form class="w-full mx-auto flex flex-col items-center gap-5">
    <div class="flex items-center justify-center gap-5">
      <div class="mb-5">
        <label
          for="newRoomNumber"
          class="block mb-2 text-sm font-medium text-gray-900 dark:text-white"
          >Room Number</label
        >
        <input
          v-model="formData.roomNumber"
          type="number"
          id="newRoomNumber"
          class="shadow-sm bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500 dark:shadow-sm-light"
          min="1"
          required
        />
      </div>
      <div class="mb-5">
        <label
          for="floorNumber"
          class="block mb-2 text-sm font-medium text-gray-900 dark:text-white"
          >Floor</label
        >
        <input
          v-model="formData.floorNumber"
          type="number"
          id="floorNumber"
          class="shadow-sm bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500 dark:shadow-sm-light"
          min="1"
          max="15"
          required
        />
      </div>
      <div class="mb-5">
        <label
          for="repeat-password"
          class="block mb-2 text-sm font-medium text-gray-900 dark:text-white"
          >Room capacity</label
        >
        <select
          v-model="formData.roomCapacity"
          id="roomCapacity"
          class="shadow-sm bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500 dark:shadow-sm-light"
          required
        >
          <option value="2">2</option>
          <option value="3">3</option>
          <option value="4">4</option>
        </select>
      </div>
    </div>
    <div class="flex items-start mb-5">
      <div class="flex items-center h-5">
        <input
          v-model="formData.hasSeaview"
          id="hasSeaview"
          type="checkbox"
          value=""
          class="w-4 h-4 border border-gray-300 rounded bg-gray-50 focus:ring-3 focus:ring-blue-300 dark:bg-gray-700 dark:border-gray-600 dark:focus:ring-blue-600 dark:ring-offset-gray-800 dark:focus:ring-offset-gray-800"
          required
        />
      </div>
      <label
        for="hasSeaview"
        class="ms-2 text-sm font-medium text-gray-900 dark:text-gray-300"
        >The Room has Sea View</label
      >
    </div>

    <button
      @click.prevent="handleSubmit"
      type="submit"
      class="text-white bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:outline-none focus:ring-blue-300 font-medium rounded-lg text-sm px-5 py-2.5 text-center dark:bg-blue-600 dark:hover:bg-blue-700 dark:focus:ring-blue-800"
    >
      Add Room
    </button>
  </form>
</template>

<script setup lang="ts">
import { RoomService } from "@/services/ApiServices";
import { reactive, onMounted } from "vue";
import type { Room } from "@/interfaces/RoomInterfaces";

const state = reactive<{
  rooms: Room[];
  totalCount: number;
  pagesNumber: number;
  loading: boolean;
}>({
  rooms: [],
  totalCount: 0,
  pagesNumber: 0,
  loading: true,
});

let pageIndex = 0;
let pageSize = 12;
onMounted(() => {
  fetchRooms(pageIndex, pageSize);
});

const onPageChange = (page: number) => {
  pageIndex = page - 1;
  fetchRooms(pageIndex, pageSize);
};

const onPreviousPageClick = () => {
  if (pageIndex > 0) {
    pageIndex--;
    fetchRooms(pageIndex, pageSize);
  }
};

const onNextPageClick = () => {
  if (pageIndex < state.pagesNumber - 1) {
    pageIndex++;
    console.log(pageIndex);
    fetchRooms(pageIndex, pageSize);
  }
};

const onPageSizeChange = (event: Event) => {
  pageIndex = 0;
  const target = event.target as HTMLSelectElement;
  pageSize = Number.parseInt(target.value);
  fetchRooms(pageIndex, pageSize);
};

const fetchRooms = async (pageIndex: number, pageSize: number) => {
  try {
    const response = await RoomService.getRooms(pageIndex, pageSize);

    state.rooms = response.data.sort(
      (a: Room, b: Room) => a.roomNumber - b.roomNumber
    );
    state.totalCount = response.totalCount;
    state.pagesNumber = Math.ceil(response.totalCount / pageSize);
  } catch (error) {
    console.error("Error Fetching rooms", error);
  } finally {
    state.loading = false;
  }
};
</script>

<template>
  <h2 class="text-black text-3xl text-center leading-5 font-semibold mb-8">
    Hotel Rooms
  </h2>
  <section
    class="w-9/12 mx-auto px-10 h-full grid grid-cols-4 md:grid-cols-2 lg:grid-cols-3 gap-10 mb-10"
  >
    <div v-for="room in state.rooms" :key="room.roomNumber" :room="room">
      <div
        :class="[
          room.isAvailable
            ? 'bg-white text-black'
            : 'bg-gray-500 text-gray-800',
        ]"
        class="rounded-lg shadow-md p-4"
      >
        <h3 class="text-lg font-semibold text-center mb-2">
          Room {{ room.roomNumber }}
        </h3>
      </div>
    </div>
  </section>

  <section class="flex items-center justify-center">
    <nav aria-label="Page navigation">
      <ul class="flex items-center -space-x-px h-10 text-base">
        <li>
          <a
            @click="onPreviousPageClick()"
            href="#"
            :class="[
              pageIndex === 0
                ? 'cursor-not-allowed bg-gray-700 '
                : 'hover:bg-gray-100 hover:text-gray-700',
            ]"
            class="flex items-center justify-center px-4 h-10 ms-0 leading-tight text-gray-500 bg-white border border-e-0 border-gray-300 rounded-s-lg"
          >
            <span class="sr-only">Previous</span>
            <svg
              class="w-3 h-3 rtl:rotate-180"
              aria-hidden="true"
              xmlns="http://www.w3.org/2000/svg"
              fill="none"
              viewBox="0 0 6 10"
            >
              <path
                stroke="currentColor"
                stroke-linecap="round"
                stroke-linejoin="round"
                stroke-width="2"
                d="M5 1 1 5l4 4"
              />
            </svg>
          </a>
        </li>
        <li v-for="page in state.pagesNumber" :key="page">
          <a
            @click="onPageChange(page)"
            href="#"
            :class="[
              pageIndex === page - 1
                ? 'bg-gray-700 text-white hover:bg-gray-700 hover:text-white'
                : 'bg-white ',
            ]"
            class="flex items-center justify-center px-4 h-10 leading-tight text-gray-500 bg-white border border-gray-300 hover:bg-gray-100 hover:text-gray-700"
            >{{ page }}</a
          >
        </li>

        <li>
          <a
            @click="onNextPageClick()"
            href="#"
            :class="[
              pageIndex + 1 === state.pagesNumber
                ? 'cursor-not-allowed bg-gray-700 '
                : 'hover:bg-gray-100 hover:text-gray-700',
            ]"
            class="flex items-center justify-center px-4 h-10 leading-tight text-gray-500 bg-white border border-gray-300 rounded-e-lg"
          >
            <span class="sr-only">Next</span>
            <svg
              class="w-3 h-3 rtl:rotate-180"
              aria-hidden="true"
              xmlns="http://www.w3.org/2000/svg"
              fill="none"
              viewBox="0 0 6 10"
            >
              <path
                stroke="currentColor"
                stroke-linecap="round"
                stroke-linejoin="round"
                stroke-width="2"
                d="m1 9 4-4-4-4"
              />
            </svg>
          </a>
        </li>
      </ul>
    </nav>
    <select
      @change="onPageSizeChange($event)"
      class="rounded-md justify-self-end text-black"
      name="pagesize"
      id="pageSize"
    >
      <option value="6">6</option>
      <option value="9">9</option>
      <option selected value="12">12</option>
    </select>
  </section>
</template>

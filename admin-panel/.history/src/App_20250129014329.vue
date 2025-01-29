<script setup lang="ts">
import { RouterLink, RouterView } from "vue-router";
import { FontAwesomeIcon } from "@fortawesome/vue-fontawesome";
import { faDoorOpen } from "@fortawesome/free-solid-svg-icons";
import { faHotel } from "@fortawesome/free-solid-svg-icons";
import { faBed } from "@fortawesome/free-solid-svg-icons";
import { faKey } from "@fortawesome/free-solid-svg-icons";
import { faPlus } from "@fortawesome/free-solid-svg-icons";
import { faUsers } from "@fortawesome/free-solid-svg-icons";
</script>

<template>
  <button
    data-drawer-target="sidebar-multi-level-sidebar"
    data-drawer-toggle="sidebar-multi-level-sidebar"
    aria-controls="sidebar-multi-level-sidebar"
    type="button"
    class="inline-flex items-center p-2 mt-2 ms-3 text-sm text-gray-500 rounded-lg sm:hidden hover:bg-gray-100 focus:outline-none focus:ring-2 focus:ring-gray-200 dark:text-gray-400 dark:hover:bg-gray-700 dark:focus:ring-gray-600"
  >
    <span class="sr-only">Open sidebar</span>
    <svg
      class="w-6 h-6"
      aria-hidden="true"
      fill="currentColor"
      viewBox="0 0 20 20"
      xmlns="http://www.w3.org/2000/svg"
    >
      <path
        clip-rule="evenodd"
        fill-rule="evenodd"
        d="M2 4.75A.75.75 0 012.75 4h14.5a.75.75 0 010 1.5H2.75A.75.75 0 012 4.75zm0 10.5a.75.75 0 01.75-.75h7.5a.75.75 0 010 1.5h-7.5a.75.75 0 01-.75-.75zM2 10a.75.75 0 01.75-.75h14.5a.75.75 0 010 1.5H2.75A.75.75 0 012 10z"
      ></path>
    </svg>
  </button>
  <section class="flex flex-row justify-between items-center">
    <aside
      id="sidebar-multi-level-sidebar"
      class="fixed top-0 left-0 z-40 w-64 h-screen transition-transform -translate-x-full sm:translate-x-0"
      aria-label="Sidebar"
    >
      <div class="h-full px-3 py-4 overflow-y-auto bg-gray-50 dark:bg-gray-800">
        <ul class="space-y-2 font-medium">
          <li>
            <a
              href="#"
              class="flex items-center p-2 text-gray-900 rounded-lg dark:text-white hover:bg-gray-100 dark:hover:bg-gray-700 group"
            >
              <FontAwesomeIcon class="text-gray-600 text-2xl" :icon="faHotel" />
              <span class="ms-3">Hotel Overview</span>
            </a>
          </li>
          <li>
            <button
              type="button"
              class="flex items-center w-full p-2 text-base text-gray-900 transition duration-75 rounded-lg group hover:bg-gray-100 dark:text-white dark:hover:bg-gray-700"
              aria-controls="dropdown-rooms"
              data-collapse-toggle="dropdown-rooms"
            >
              <FontAwesomeIcon
                class="text-gray-600 text-2xl"
                :icon="faDoorOpen"
              />

              <span
                class="flex-1 ms-3 text-left rtl:text-right whitespace-nowrap"
                >Rooms</span
              >
              <svg
                class="w-3 h-3"
                aria-hidden="true"
                xmlns="http://www.w3.org/2000/svg"
                fill="none"
                viewBox="0 0 10 6"
              >
                <path
                  stroke="currentColor"
                  stroke-linecap="round"
                  stroke-linejoin="round"
                  stroke-width="2"
                  d="m1 1 4 4 4-4"
                />
              </svg>
            </button>
            <ul id="dropdown-rooms" class="hidden py-2 space-y-2">
              <li>
                <RouterLink
                  to="/rooms"
                  class="flex justify-start gap-2 items-start w-full p-2 text-gray-900 transition duration-75 rounded-lg pl-11 group hover:bg-gray-100 dark:text-white dark:hover:bg-gray-700"
                >
                  <FontAwesomeIcon
                    class="text-gray-600 text-2xl"
                    :icon="faKey"
                  />
                  View All Rooms</RouterLink
                >
              </li>
              <li>
                <RouterLink
                  to="/rooms/add"
                  class="flex justify-start gap-2 items-start w-full p-2 text-gray-900 transition duration-75 rounded-lg pl-11 group hover:bg-gray-100 dark:text-white dark:hover:bg-gray-700"
                >
                  <FontAwesomeIcon
                    class="text-gray-600 text-2xl"
                    :icon="faPlus"
                  />Add new Room</RouterLink
                >
              </li>
            </ul>
          </li>
          <li>
            <button
              type="button"
              class="flex items-center w-full p-2 text-base text-gray-900 transition duration-75 rounded-lg group hover:bg-gray-100 dark:text-white dark:hover:bg-gray-700"
              aria-controls="dropdown-residents"
              data-collapse-toggle="dropdown-residents"
            >
              <FontAwesomeIcon class="text-gray-600 text-2xl" :icon="faBed" />

              <span
                class="flex-1 ms-3 text-left rtl:text-right whitespace-nowrap"
                >Residents</span
              >
              <svg
                class="w-3 h-3"
                aria-hidden="true"
                xmlns="http://www.w3.org/2000/svg"
                fill="none"
                viewBox="0 0 10 6"
              >
                <path
                  stroke="currentColor"
                  stroke-linecap="round"
                  stroke-linejoin="round"
                  stroke-width="2"
                  d="m1 1 4 4 4-4"
                />
              </svg>
            </button>
            <ul id="dropdown-residents" class="hidden py-2 space-y-2">
              <li>
                <RouterLink
                  to="/residents"
                  class="flex justify-start gap-2 items-start w-full p-2 text-gray-900 transition duration-75 rounded-lg pl-11 group hover:bg-gray-100 dark:text-white dark:hover:bg-gray-700"
                >
                  <FontAwesomeIcon
                    class="text-gray-600 text-2xl"
                    :icon="faUsers"
                  />All Residents</RouterLink
                >
              </li>
              <li>
                <RouterLink
                  to="/residents/add"
                  class="flex justify-start gap-2 items-start w-full p-2 text-gray-900 transition duration-75 rounded-lg pl-11 group hover:bg-gray-100 dark:text-white dark:hover:bg-gray-700"
                >
                  <FontAwesomeIcon
                    class="text-gray-600 text-2xl"
                    :icon="faPlus"
                  />Add a Resident</RouterLink
                >
              </li>
            </ul>
          </li>
          <li>
            <button
              type="button"
              class="flex items-center w-full p-2 text-base text-gray-900 transition duration-75 rounded-lg group hover:bg-gray-100 dark:text-white dark:hover:bg-gray-700"
              aria-controls="dropdown-reservations"
              data-collapse-toggle="dropdown-reservations"
            >
              <FontAwesomeIcon class="text-gray-600 text-2xl" :icon="faKey" />

              <span
                class="flex-1 ms-3 text-left rtl:text-right whitespace-nowrap"
                >Reservations</span
              >
              <svg
                class="w-3 h-3"
                aria-hidden="true"
                xmlns="http://www.w3.org/2000/svg"
                fill="none"
                viewBox="0 0 10 6"
              >
                <path
                  stroke="currentColor"
                  stroke-linecap="round"
                  stroke-linejoin="round"
                  stroke-width="2"
                  d="m1 1 4 4 4-4"
                />
              </svg>
            </button>
            <ul id="dropdown-reservations" class="hidden py-2 space-y-2">
              <li>
                <RouterLink
                  to="/reservations"
                  class="flex justify-start gap-2 items-start w-full p-2 text-gray-900 transition duration-75 rounded-lg pl-11 group hover:bg-gray-100 dark:text-white dark:hover:bg-gray-700"
                >
                  <FontAwesomeIcon
                    class="text-gray-600 text-2xl"
                    :icon="faPlus"
                  />
                  All Reservations</RouterLink
                >
              </li>
              <li>
                <RouterLink
                  to="/reservations/add"
                  class="flex justify-start gap-2 items-start w-full p-2 text-gray-900 transition duration-75 rounded-lg pl-11 group hover:bg-gray-100 dark:text-white dark:hover:bg-gray-700"
                >
                  <FontAwesomeIcon
                    class="text-gray-600 text-2xl"
                    :icon="faPlus"
                  />
                  Add a Reservation</RouterLink
                >
              </li>
            </ul>
          </li>
        </ul>
      </div>
    </aside>

    <main class="flex items-start h-screen w-full pl-64">
      <div class="mx-auto w-full md:p-6 bg-gray-300 pt-12">
        <RouterView />
      </div>
    </main>
  </section>
</template>

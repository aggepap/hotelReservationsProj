<script setup></script>

<template>
  <h2 class="text-black text-3xl text-center leading-5 font-semibold mb-8">
    Add New Room
  </h2>
  <form class="max-w-sm mx-auto">
    <div class="mb-5">
      <label
        for="newRoomNumber"
        class="block mb-2 text-sm font-medium text-gray-900 dark:text-white"
        >Room Number</label
      >
      <input
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
        type="number"
        id="floorNumber"
        class="shadow-sm bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500 dark:shadow-sm-light"
        required
      />
    </div>
    <div class="mb-5">
      <label
        for="repeat-password"
        class="block mb-2 text-sm font-medium text-gray-900 dark:text-white"
        >Room capacity</label
      >
      <option
        id="roomCapacity"
        class="shadow-sm bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500 dark:shadow-sm-light"
        required
      >
        <select name="1" id="1"></select
        >1
      </option>
    </div>
    <div class="flex items-start mb-5">
      <div class="flex items-center h-5">
        <input
          id="terms"
          type="checkbox"
          value=""
          class="w-4 h-4 border border-gray-300 rounded bg-gray-50 focus:ring-3 focus:ring-blue-300 dark:bg-gray-700 dark:border-gray-600 dark:focus:ring-blue-600 dark:ring-offset-gray-800 dark:focus:ring-offset-gray-800"
          required
        />
      </div>
      <label
        for="terms"
        class="ms-2 text-sm font-medium text-gray-900 dark:text-gray-300"
        >I agree with the
        <a href="#" class="text-blue-600 hover:underline dark:text-blue-500"
          >terms and conditions</a
        ></label
      >
    </div>
    <button
      type="submit"
      class="text-white bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:outline-none focus:ring-blue-300 font-medium rounded-lg text-sm px-5 py-2.5 text-center dark:bg-blue-600 dark:hover:bg-blue-700 dark:focus:ring-blue-800"
    >
      Register new account
    </button>
  </form>
</template>

<script setup lang="ts">
import { FormKit } from "@formkit/vue";
import { ref } from "vue";
import { ResidentService } from "@/services/ApiServices";
import { useToast } from "vue-toastification";
import { RoomService } from "@/services/ApiServices";
const submitted = ref(false);
const toast = useToast();

// biome-ignore lint/suspicious/noExplicitAny: <explanation>
const handleSubmit = async (data: any) => {
  const timeout = new Promise((_, reject) => {
    setTimeout(() => {
      reject(new Error("Request timed out"));
    }, 2000);
  });
  try {
    const result = await Promise.race([
      ResidentService.addResident(data),
      timeout,
    ]);
    submitted.value = true;
    // biome-ignore lint/suspicious/noExplicitAny: <explanation>
  } catch (error: any) {
    if (error.message === "Request timed out") {
      toast.error("Request timed out");
    } else {
    }
  }
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
  <div class="bg-white rounded-md grid place-items-center py-10 px-10">
    <FormKit type="form" submit-label="Add Reservation" @submit="handleSubmit">
      <h2 class="text-black text-3xl text-center leading-5 font-semibold mb-8">
        Add new Reservation
      </h2>
      <div class="flex gap-4">
        <FormKit
          type="text"
          name="firstname"
          label="First Name"
          validation="required | length:3,20"
          validation-visibility="blur"
        />
        <FormKit
          type="text"
          name="lastname"
          label="Last Name"
          validation="required | length:3,30"
          validation-visibility="blur"
        />
      </div>

      <div class="flex gap-4">
        <FormKit
          type="date"
          name="reservationStartDate"
          label="Date of arrival"
          validation="required"
          validation-visibility="blur"
        />
        <FormKit
          type="date"
          name="reservationEndDate"
          label="Date of departure"
          validation="required | date_after_node:reservationStartDate"
          validation-visibility="blur"
        />
      </div>
      <FormKit
        type="select"
        label="Number of guests"
        name="guestsNumber"
        validation="required"
        validation-visibility="blur"
        :options="{ 1: '1', 2: '2', 3: '3', 4: '4' }"
      />
      <FormKit
        type="select"
        label="Room"
        name="roomNumber"
        :options="{ 1: '1', 2: '2', 3: '3', 4: '4' }"
      />
    </FormKit>
  </div>
</template>

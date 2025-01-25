<script setup lang="ts">
import { FormKit } from "@formkit/vue";
import { ref } from "vue";
import { ResidentService } from "@/services/ApiServices";
import { useToast } from "vue-toastification";
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
</script>
<template>
  <div class="bg-white rounded-md grid place-items-center py-10 px-10">
    <FormKit type="form" submit-label="Add Reservation" @submit="handleSubmit">
      <h2 class="text-black text-3xl text-center leading-5 font-semibold mb-8">
        Add new Reservation
      </h2>

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
        :options="{}"
      />
      <div class="flex gap-4">
        <FormKit
          type="text"
          name="vat"
          label="VAT Number"
          validation="number| required |length:10,10"
          validation-visibility="blur"
        />
        <FormKit
          type="text"
          name="idNumber"
          label="ID Number"
          validation="alphanumeric| required |length:6,15"
          validation-visibility="blur"
        />
      </div>
      <div class="flex gap-4">
        <FormKit
          type="date"
          name="birthDate"
          value="1999-01-01"
          label="Date of Birth"
          validation="required"
          validation-visibility="blur"
        />
      </div>
      <FormKit
        type="select"
        label="Gender"
        name="gender"
        validation="required"
        validation-visibility="blur"
        :options="{
          MALE: 'Male',
          FEMALE: 'Female',
          OTHER: 'Other',
        }"
      />
      <FormKit
        type="email"
        label="Email address"
        name="email"
        validation="email"
        validation-visibility="live"
        placeholder="email@example.com"
      />
      <FormKit
        type="text"
        name="address"
        label="Address"
        validation="Alpha-spaces"
        validation-visibility="blur"
        placeholder="Ronchester rd 12"
      />
      <FormKit
        type="text"
        name="phoneNumber"
        label="Telephone Number"
        validation="number"
        validation-visibility="blur"
        placeholder="00000000000"
      />
    </FormKit>
  </div>
</template>

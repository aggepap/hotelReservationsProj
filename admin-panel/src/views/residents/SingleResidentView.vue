<script setup>
import { reactive, onMounted, ref } from "vue";
import { useRoute } from "vue-router";
import { ResidentService } from "@/services/ApiServices";
const state = reactive({
  loading: true,
  residentId: 0,
});
let resident = ref();

onMounted(async () => {
  const route = useRoute();
  state.residentId = Number.parseInt(route.params.id);
  await getResidentById(state.residentId);
});

const getResidentById = async (residentId) => {
  try {
    const response = await ResidentService.getResidentById(residentId);
    resident = response;
    console.log(resident);
  } catch (error) {
    console.log(error);
  } finally {
    state.loading = false;
  }
};
</script>
<template>
  <main class="w-3/4">
    <div v-if="!state.loading" class="bg-white rounded-lg shadow-md p-6">
      <h2 class="text-2xl text-black font-semibold mb-4">Resident Details</h2>

      <div class="grid grid-cols-1 md:grid-cols-2 gap-4">
        <div>
          <p class="font-semibold text-gray-700">First Name:</p>
          <p class="text-gray-900">{{ resident.firstname }}</p>
        </div>

        <div>
          <p class="font-medium text-gray-700">Last Name:</p>
          <p class="text-gray-900">{{ resident.lastname }}</p>
        </div>

        <div>
          <p class="font-medium text-gray-700">VAT:</p>
          <p class="text-gray-900">{{ resident.vat }}</p>
        </div>

        <div>
          <p class="font-medium text-gray-700">ID Number:</p>
          <p class="text-gray-900">{{ resident.idNumber }}</p>
        </div>

        <div>
          <p class="font-medium text-gray-700">Birth Date:</p>
          <p class="text-gray-900">{{ resident.birthDate }}</p>
        </div>

        <div>
          <p class="font-medium text-gray-700">Country:</p>
          <p class="text-gray-900">
            {{ resident.countryCode }}
          </p>
        </div>

        <div>
          <p class="font-medium text-gray-700">Address:</p>
          <p v-if="resident.address" class="text-gray-900">
            {{ resident.address }}
          </p>
          <p class="text-gray-400" v-else>Not Submitted</p>
        </div>

        <div>
          <p class="font-medium text-gray-700">Email:</p>
          <p v-if="resident.email" class="text-gray-900">
            {{ resident.email }}
          </p>
          <p class="text-gray-400" v-else>Not Submitted</p>
        </div>

        <div>
          <p class="font-medium text-gray-700">Phone Number:</p>
          <p v-if="resident.phoneNumber" class="text-gray-900">
            {{ resident.phoneNumber }}
          </p>
          <p class="text-gray-400" v-else>Not Submitted</p>
        </div>

        <div>
          <p class="font-medium text-gray-700">Gender:</p>
          <p class="text-gray-900">{{ resident.gender }}</p>
        </div>

        <div>
          <p class="font-medium text-gray-700">Adult:</p>

          <p v-if="resident.isAdult" class="text-gray-900">
            {{ resident.isAdult ? "Yes" : "No" }}
          </p>
          <p class="text-gray-400" v-else>Not Submitted</p>
        </div>

        <div v-if="resident.roomNumber">
          <p class="font-medium text-gray-700">Room Number:</p>
          <p v-if="resident.roomNumber" class="text-gray-900">
            {{ resident.roomNumber }}
          </p>
          <p class="text-gray-400" v-else>Not Submitted</p>
        </div>
        <div v-if="resident.reservationCode">
          <p class="font-medium text-gray-700">Reservation Code:</p>
          <p class="text-gray-900">{{ resident.reservationCode }}</p>
        </div>
      </div>
    </div>
  </main>
</template>

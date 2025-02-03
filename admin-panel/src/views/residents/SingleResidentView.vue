<script setup>
import { reactive, onMounted, ref } from "vue";
import { useRoute, useRouter } from "vue-router";
import { ResidentService } from "@/services/ApiServices";
import UpdateResidentForm from "@/components/UpdateResidentForm.vue";
import Dialog from "primevue/dialog";
import Button from "primevue/button";
const state = reactive({
  loading: true,
  residentId: 0,
});
let resident = ref();
const route = useRoute();
const router = useRouter();
const isVisible = ref(false);
const visibleEdit = ref(false);
onMounted(async () => {
  state.residentId = Number.parseInt(route.params.id);
  await getResidentById(state.residentId);
  isVisible.value = true;
});

const getResidentById = async (residentId) => {
  try {
    const response = await ResidentService.getResidentById(residentId);
    resident = response;
  } catch (error) {
    console.log(error);
  } finally {
    state.loading = false;
  }
};
</script>
<template>
  <Transition name="slide-fade">
    <main v-if="isVisible">
      <Button
        @click="router.back()"
        class="mb-4"
        severity="contrast"
        label="Back"
        icon="pi pi-arrow-left"
      />
      <h2 class="text-2xl text-black font-semibold mb-4">Resident Details</h2>
      <div v-if="!state.loading" class="bg-white rounded-lg shadow-md">
        <div class="flex">
          <div class="grid grid-cols-1 md:grid-cols-2 gap-4 w-3/4 px-20 py-6">
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
          <div
            class="h-auto w-1/4 bg-gray-100 flex items-center justify-center"
          >
            <div class="">
              <Button
                severity="contrast"
                variant="raised"
                label="Edit Resident"
                @click="visibleEdit = true"
              />
              <Dialog
                v-model:visible="visibleEdit"
                modal
                header="Edit Resident"
                :style="{ width: '40rem' }"
              >
                <UpdateResidentForm :resident="resident" />
              </Dialog>
            </div>
          </div>
        </div>
      </div>
    </main>
  </Transition>
</template>

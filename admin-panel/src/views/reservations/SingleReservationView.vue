<script setup>
import { RouterLink, useRoute } from "vue-router";
import { ReservationService } from "@/services/ApiServices";
import { ResidentService } from "@/services/ApiServices";
import { onMounted, ref, reactive } from "vue";
import ProgressSpinner from "primevue/progressspinner";
import AddResidentForm from "@/components/AddResidentForm.vue";
import AddResidentToReservation from "@/components/AddResidentToReservation.vue";
import { useRouter } from "vue-router";
import Dialog from "primevue/dialog";
import Button from "primevue/button";
import { useToast } from "vue-toastification";
import { faXmark } from "@fortawesome/free-solid-svg-icons";
import { FontAwesomeIcon } from "@fortawesome/vue-fontawesome";

const state = reactive({
  reservationId: "",
  reservation: null,
  loading: true,
  residents: [],
});
const visibleAddExisting = ref(false);
const visibleAddNew = ref(false);
const toast = useToast();
const isVisible = ref(false);
const router = useRouter();

onMounted(async () => {
  const route = useRoute();
  state.reservationId = Number.parseInt(route.params.id);
  await getReservation(state.reservationId);
  await getResidents();
  document.addEventListener("DOMContentLoaded", (event) => {
    document.getElementById("addResidentButton").click();
  });
  isVisible.value = true;
});

const getReservation = async (id) => {
  try {
    const response = await ReservationService.getReservationById(id);
    console.log(response);
    state.reservation = response;
  } catch (error) {
    console.log(error);
  } finally {
    state.loading = false;
    console.log(state.reservation.residents);
  }
};

const getResidents = async () => {
  try {
    const response = await ResidentService.getResidents("lastname");
    state.residents = response;
  } catch (error) {
    console.log(error);
  } finally {
    state.loading = false;

    visibleAddExisting.value = false;
    visibleAddNew.value = false;
  }
};

const removeResident = async (id) => {
  console.log(id);
  try {
    const response = await ReservationService.removeResidentFromReservation(
      state.reservationId,
      id
    );
    state.reservation.residents = state.reservation.residents.filter(
      (resident) => resident.id !== id
    );
  } catch (error) {
    console.log(error);
  }
};

const handleResidentAdded = async (residentId) => {
  console.log(residentId);

  const addedResident = state.residents.find(
    (resident) => resident.id === residentId
  );
  if (addedResident) {
    await state.reservation.residents.push(addedResident);
    visibleAddExisting.value = false;
  }
};

const handleNewResidentAdded = async (residentId) => {
  try {
    const response = await ReservationService.getReservationById(
      state.reservationId
    );
    state.reservation = response;
  } catch (error) {
    console.error("Error refreshing reservation:", error);
    toast.error("Failed to refresh resident list.");
  } finally {
    visibleAddNew.value = false;
  }
};
</script>

<template>
  <Button
    @click="router.back()"
    class="mb-4"
    severity="contrast"
    label="Back"
    icon="pi pi-arrow-left"
  />
  <Transition name="slide-fade">
    <section v-if="isVisible" class="bg-white dark:bg-gray-900">
      <div
        v-if="state.loading"
        class="py-8 px-4 mx-auto grid place-items-center lg:py-16 lg:px-6"
      >
        <ProgressSpinner aria-label="Loading" />
        <span class="text-black text-lg">Loading</span>
      </div>

      <main v-if="state.reservation" class="flex">
        <div class="py-8 px-4 mx-auto lg:py-16">
          <span
            class="mb-2 text-xl font-semibold leading-none text-gray-900 md:text-2xl"
            >Reservation Code:
          </span>
          <span
            class="mb-2 text-lg font-semibold leading-none text-gray-900 md:text-2xl dark:text-white"
          >
            {{ state.reservation.reservationCode }}
          </span>

          <p
            class="mb-4 text-xl font-extrabold leading-none text-gray-900 md:text-2xl dark:text-white"
          >
            Room: {{ state.reservation.roomNumber }}
          </p>
          <dl>
            <dt
              class="mb-2 font-semibold leading-none text-gray-900 dark:text-white"
            >
              <span class="inline-block w-[6.5rem]">Starting Date :</span>
              {{ state.reservation.reservationStartDate }}
            </dt>
            <dt
              class="mb-2 font-semibold leading-none text-gray-900 dark:text-white"
            >
              <span class="inline-block w-[6.5rem]">End Date :</span>
              {{ state.reservation.reservationEndDate }}
            </dt>
            <dd class="mb-4 text-gray-500 sm:mb-5 dark:text-gray-400">
              Reservation Status:
              {{ state.reservation.isActive ? "Active" : "Inactive" }}
            </dd>
          </dl>

          <div class="flex items-center space-x-4">
            <button
              type="button"
              class="text-white inline-flex items-center bg-blue-600 hover:bg-blue-800 focus:ring-4 focus:outline-none focus:ring-primary-300 font-medium rounded-lg text-sm px-5 py-2.5 text-center dark:bg-primary-600 dark:hover:bg-primary-700 dark:focus:ring-primary-800"
            >
              <svg
                aria-hidden="true"
                class="mr-1 -ml-1 w-5 h-5"
                fill="currentColor"
                viewBox="0 0 20 20"
                xmlns="http://www.w3.org/2000/svg"
              >
                <path
                  d="M17.414 2.586a2 2 0 00-2.828 0L7 10.172V13h2.828l7.586-7.586a2 2 0 000-2.828z"
                ></path>
                <path
                  fill-rule="evenodd"
                  d="M2 6a2 2 0 012-2h4a1 1 0 010 2H4v10h10v-4a1 1 0 112 0v4a2 2 0 01-2 2H4a2 2 0 01-2-2V6z"
                  clip-rule="evenodd"
                ></path>
              </svg>
              Edit
            </button>
            <button
              type="button"
              class="inline-flex items-center text-white bg-red-600 hover:bg-red-700 focus:ring-4 focus:outline-none focus:ring-red-300 font-medium rounded-lg text-sm px-5 py-2.5 text-center dark:bg-red-500 dark:hover:bg-red-600 dark:focus:ring-red-900"
            >
              <svg
                aria-hidden="true"
                class="w-5 h-5 mr-1.5 -ml-1"
                fill="currentColor"
                viewBox="0 0 20 20"
                xmlns="http://www.w3.org/2000/svg"
              >
                <path
                  fill-rule="evenodd"
                  d="M9 2a1 1 0 00-.894.553L7.382 4H4a1 1 0 000 2v10a2 2 0 002 2h8a2 2 0 002-2V6a1 1 0 100-2h-3.382l-.724-1.447A1 1 0 0011 2H9zM7 8a1 1 0 012 0v6a1 1 0 11-2 0V8zm5-1a1 1 0 00-1 1v6a1 1 0 102 0V8a1 1 0 00-1-1z"
                  clip-rule="evenodd"
                ></path>
              </svg>
              Delete
            </button>
          </div>
        </div>
        <aside class="bg-gray-100 w-1/4 p-4">
          <div class="flex flex-col h-full">
            <h2 class="text-xl font-bold mb-4 text-black">Residents</h2>
            <TransitionGroup name="list" tag="ul" class="justify-self-start">
              <li
                class="flex justify-between items-center mb-2"
                v-for="resident in state.reservation.residents"
                :key="resident.id"
              >
                <RouterLink
                  :to="`/residents/${resident.id}`"
                  class="text-black text-lg hover:border-b-2 hover:border-black"
                >
                  {{ resident.firstname }} {{ resident.lastname }}
                </RouterLink>
                <FontAwesomeIcon
                  @click="removeResident(resident.id)"
                  class="text-black cursor-pointer p-2"
                  :icon="faXmark"
                />
              </li>
            </TransitionGroup>

            <div class="flex flex-col h-full justify-self-end mt-auto gap-4">
              <Button
                class="justify-self-end mt-auto"
                severity="contrast"
                variant="raised"
                label="Add Existing Resident"
                @click="visibleAddExisting = true"
              />
              <Dialog
                v-model:visible="visibleAddExisting"
                modal
                header="Add Resident"
                :style="{ width: '40rem' }"
              >
                <AddResidentToReservation
                  :residents="state.residents"
                  :reservationId="state.reservation.id"
                  @residentAdded="handleResidentAdded"
                />
              </Dialog>

              <Button
                severity="contrast"
                variant="outlined"
                label="Add New Resident"
                @click="visibleAddNew = true"
              />
              <Dialog
                v-model:visible="visibleAddNew"
                modal
                header="Add Resident"
                :style="{ width: '40rem' }"
              >
                <AddResidentForm
                  @residentAdded="handleNewResidentAdded"
                  :reservationId="state.reservationId"
                />
              </Dialog>
            </div>
          </div>
        </aside>
      </main>
    </section>
  </Transition>
</template>
<style scoped>
.list-move,
.list-enter-active,
.list-leave-active {
  transition: all 0.5s ease;
}
.list-enter-from,
.list-leave-to {
  opacity: 0;
  transform: translateX(30px);
}
.list-leave-active {
  position: absolute;
}
</style>

<script setup lang="ts">
import { ref, computed, reactive, onMounted } from "vue";
import DataTable from "primevue/datatable";
import Column from "primevue";
import { ReservationService } from "@/services/ApiServices";

/**
 * Get initial date and format it
 */

const state = reactive({
  reservations: [],
  loading: true,
});

onMounted(() => {
  fetchReservations(0, 10, "id", formattedDate(), formattedDate(1));
});

const date = new Date();
const formattedDate = (daysAdded = 0) => {
  const newDate = new Date(date.value);
  date.setDate(date.getDate() + daysAdded);

  const year = date.getFullYear();
  const month = String(date.getMonth() + 1).padStart(2, "0");
  const day = String(date.getDate()).padStart(2, "0");

  return `${year}-${month}-${day}`;
};

/**
 * Get All Reservations
 */
const fetchReservations = async (
  pageIndex: number,
  pageSize: number,
  sortBy: string,
  fromDate: string,
  toDate: string
) => {
  state.loading = true;
  try {
    const response = await ReservationService.getReservations(
      pageIndex,
      pageSize,
      sortBy,
      fromDate,
      toDate
    );
    state.reservations = response.data.reservations;
  } finally {
    state.loading = false;
  }
};
</script>

<template>
  <section class="grid place-items-center">
    <h2 class="text-black text-3xl text-center leading-5 font-semibold mb-8">
      Reservations
    </h2>
    <div class="dateForm">
      <FormKit type="form" :actions="false" submit-label="Search">
        <div class="flex justify-center items-center gap-2">
          <FormKit
            type="date"
            :value="formattedDate(0)"
            label="From"
            validation="required|"
            validation-visibility="live"
          />
          <FormKit
            type="date"
            :value="formattedDate(10)"
            label="Since"
            validation="required"
            validation-visibility="live"
          />
          <FormKit outer-class="m-0" type="submit" label="Submit" />
        </div>
      </FormKit>
    </div>

    <main></main>
  </section>
</template>

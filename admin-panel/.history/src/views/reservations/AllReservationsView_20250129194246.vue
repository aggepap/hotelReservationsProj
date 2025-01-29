<script setup lang="ts">
import { ref, reactive, onMounted } from "vue";
import DataTable from "primevue/datatable";
import Column from "primevue";
import { ReservationService } from "@/services/ApiServices";
import { FilterMatchMode, FilterOperator } from "@primevue/core/api";

/**
 * Get initial date and format it
 */

const state = reactive({
  reservations: [],
  loading: true,
});

onMounted(() => {
  fetchReservations(0, 10, "reservationCode", "1999-01-01", formattedDate(1));
  initFilters();
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
    state.reservations = response.data;
    console.log(response.data);
  } finally {
    state.loading = false;
  }
};

/**
 * DataTable Settings
 */
const filters = ref();
const initFilters = () => {
  filters.value = {
    global: { value: null, matchMode: FilterMatchMode.CONTAINS },
    reservationCode: {
      operator: FilterOperator.AND,
      constraints: [{ value: null, matchMode: FilterMatchMode.STARTS_WITH }],
    },
    reservationBookedDate: {
      operator: FilterOperator.AND,
      constraints: [{ value: null, matchMode: FilterMatchMode.STARTS_WITH }],
    },
    reservationStartDate: { value: null, matchMode: FilterMatchMode.IN },
    birthDate: {
      operator: FilterOperator.AND,
      constraints: [{ value: null, matchMode: FilterMatchMode.DATE_IS }],
    },
    reservationEndDate: {
      operator: FilterOperator.AND,
      constraints: [{ value: null, matchMode: FilterMatchMode.EQUALS }],
    },
    roomNumber: {
      operator: FilterOperator.OR,
      constraints: [{ value: null, matchMode: FilterMatchMode.EQUALS }],
    },
  };
};

const clearFilter = () => {
  initFilters();
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

    <main>
      <DataTable
        :value="state.reservations"
        paginator
        showGridlines
        :rows="10"
        dataKey="id"
        filterDisplay="menu"
        :loading="state.loading"
        :globalFilterFields="[
          'reservationCode',
          'reservationBookedDate',
          'reservationStartDate',
          'guestNumber',
          'roomNymber',
          'countryCode',
          'gender',
        ]"
      >
        <template #header>
          <div class="flex justify-between">
            <Button
              type="button"
              icon="pi pi-filter-slash"
              label="Clear"
              outlined
              @click="clearFilter()"
            />
            <IconField>
              <InputIcon>
                <i class="pi pi-search" />
              </InputIcon>
              <InputText
                v-model="filters['global'].value"
                placeholder="Keyword Search"
              />
            </IconField>
          </div>
        </template>
      </DataTable>
    </main>
  </section>
</template>

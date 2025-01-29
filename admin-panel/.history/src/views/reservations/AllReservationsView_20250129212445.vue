<script setup lang="ts">
import { ref, reactive, onMounted } from "vue";
import DataTable from "primevue/datatable";
import Column from "primevue/column";
import { ReservationService } from "@/services/ApiServices";
import { FilterMatchMode, FilterOperator } from "@primevue/core/api";
import Button from "primevue/button";
import InputText from "primevue/inputtext";
import InputIcon from "primevue/inputicon";
import IconField from "primevue/iconfield";

/**
 * Get initial date and format it
 */

const state = reactive({
  reservations: [],
  loading: true,
});

onMounted(() => {
  fetchReservations(0, 10, "reservationCode", "1990-01-01", formattedDate(1));
});

const date = new Date();
const formattedDate = (daysAdded = 0) => {
  const newDate = new Date(date);
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
  sortedBy: string,
  fromDate: string,
  toDate: string
) => {
  state.loading = true;
  try {
    const response = await ReservationService.getReservations(
      pageIndex,
      pageSize,
      sortedBy,
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
initFilters();
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
        :filters="filters"
        filterDisplay="menu"
        :loading="state.loading"
        :globalFilterFields="[
          'reservationCode',
          'reservationBookedDate',
          'reservationStartDate',
          'guestNumber',
          'roomNumber',
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
        <template #empty> No Reservations found. </template>
        <template #loading> Loading Reservations data. Please wait. </template>
        <Column
          field="reservationCode"
          sortable
          header="Reservation Code"
          style="min-width: 12rem"
        >
          <template #filter="{ filterModel }">
            <InputText
              v-model="filterModel.value"
              type="text"
              placeholder="Search by code"
            />
          </template>
        </Column>
        <Column
          field="reservationBookedDate"
          sortable
          header="Booked Date"
          style="min-width: 12rem"
        >
          <template #filter="{ filterModel }">
            <InputText
              v-model="filterModel.value"
              type="date"
              placeholder="Search by Booked date"
            />
          </template>
        </Column>
        <Column
          field="reservationStartDate"
          sortable
          header="Start Date"
          style="min-width: 12rem"
        >
          <template #filter="{ filterModel }">
            <InputText
              v-model="filterModel.value"
              type="date"
              placeholder="Search by Start date"
            />
          </template>
        </Column>
        <Column
          field="reservationEndDate"
          sortable
          header="End Date"
          style="min-width: 12rem"
        >
          <template #filter="{ filterModel }">
            <InputText
              v-model="filterModel.value"
              type="date"
              placeholder="Search by end date"
            />
          </template>
        </Column>
        <Column
          field="residents"
          sortable
          header="Residents"
          style="min-width: 12rem"
        >
          <template #body="{ data }">
            <span
              >{{
                data.residents.length > 0
                  ? data.residents[0].firstname
                  : "no name"
              }}
            </span>

            <span>{{
              data.residents.length > 0 ? data.residents[0].lastname : ""
            }}</span>
          </template>
          <template #filter="{ filterModel }">
            <InputText
              v-model="filterModel.value"
              type="text"
              placeholder="Search by end date"
            />
          </template>
        </Column>
      </DataTable>
    </main>
  </section>
</template>

<script setup lang="ts">
import { ref, reactive, onMounted } from "vue";
import DataTable from "primevue/datatable";
import Column from "primevue/column";
import { ReservationService } from "@/services/ApiServices";
import { FilterMatchMode, FilterOperator } from "@primevue/core/api";
import { useRouter } from "vue-router";
import Button from "primevue/button";
import InputText from "primevue/inputtext";
import InputIcon from "primevue/inputicon";
import IconField from "primevue/iconfield";

const router = useRouter();
const isVisible = ref(false);
const state = reactive({
  reservations: [],
  loading: true,
});
const editingRows = ref([]);
onMounted(() => {
  fetchReservations("reservationCode", "1990-01-01", formattedCurrentDate(30));
  isVisible.value = true;
});

/**
 * Get initial date and format it
 */

const date = new Date();
const formattedCurrentDate = (daysAdded = 0) => {
  const newDate = new Date(date);
  date.setDate(date.getDate() + daysAdded);

  const year = date.getFullYear();
  const month = String(date.getMonth() + 1).padStart(2, "0");
  const day = String(date.getDate()).padStart(2, "0");

  return `${year}-${month}-${day}`;
};

const formatDate = (dateString: string) => {
  if (!dateString) return "";

  const date = new Date(dateString);

  const day = String(date.getDate()).padStart(2, "0");
  const month = String(date.getMonth() + 1).padStart(2, "0");
  const year = String(date.getFullYear());

  return `${day}-${month}-${year}`;
};

/**
 * Get All Reservations
 */
const fetchReservations = async (
  sortedBy: string,
  fromDate: string,
  toDate: string,
  isActive?: boolean
) => {
  state.loading = true;
  try {
    const response = await ReservationService.getAllReservations(
      sortedBy,
      fromDate,
      toDate,
      false
    );
    state.reservations = response.data.map((reservation) => ({
      ...reservation,
      firstResidentLastName:
        reservation.residents && reservation.residents.length > 0e2
          ? reservation.residents[0].lastname
          : "No Name",
    }));
    console.log(state.reservations);
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
    firstResidentLastName: {
      operator: FilterOperator.OR,
      constraints: [{ value: null, matchMode: FilterMatchMode.EQUALS }],
    },
  };
};
initFilters();
const clearFilter = () => {
  initFilters();
};

const handleSubmit = (data: any) => {
  fetchReservations("reservationCode", data.fromDate, data.toDate);
  console.log(data);
};

const onRowEditSave = (event: any) => {
  let { newData, index } = event;
  console.log(newData, index);
  ReservationService.updateReservation(newData);
};

const onRowSelect = (event: any) => {
  router.push({
    name: "singleReservation",
    params: {
      id: event.data.id,
    },
  });
};
</script>

<template>
  <Transition name="slide-fade">
    <section v-if="isVisible">
      <h2 class="text-black text-3xl text-center leading-5 font-semibold mb-8">
        Reservations
      </h2>

      <div class="dateForm grid place-items-center">
        <FormKit
          type="form"
          :actions="false"
          @submit="handleSubmit"
          submit-label="Search"
        >
          <div class="flex justify-center items-center gap-2">
            <FormKit
              name="fromDate"
              type="date"
              :value="formattedCurrentDate()"
              label="From"
              validation="required|"
              validation-visibility="live"
            />
            <FormKit
              type="date"
              name="toDate"
              :value="formattedCurrentDate(10)"
              label="To"
              validation="required"
              validation-visibility="live"
            />
            <FormKit
              type="submit"
              :classes="{
                outer: '!-mb-2',
              }"
              label="Submit"
            />
          </div>
        </FormKit>
      </div>

      <div class="">
        <DataTable
          @rowSelect="onRowSelect"
          :value="state.reservations"
          selectionMode="single"
          editMode="row"
          v-model:editingRows="editingRows"
          @row-edit-save="onRowEditSave"
          resizableColumns
          columnResizeMode="fit"
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
            'roomNumber',
            'firstResidentLastName',
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
          <template #loading>
            Loading Reservations data. Please wait.
          </template>
          <Column
            field="reservationCode"
            sortable
            header="Reservation Code"
            style="min-width: 5rem"
          >
            <template #body="{ data }">
              {{ data.reservationCode.slice(0, 13) }}
            </template>
          </Column>
          <Column
            field="reservationBookedDate"
            sortable
            header="Booked Date"
            style="min-width: 5rem"
          >
            <template #body="{ data }">
              {{ formatDate(data.reservationBookedDate) }}
            </template>
          </Column>
          <Column
            field="reservationStartDate"
            sortable
            header="Start Date"
            style="min-width: 5rem"
          >
            <template #editor="{ data, field }">
              <InputText type="date" v-model="data[field]" fluid />
            </template>
            <template #body="{ data }">
              {{ formatDate(data.reservationStartDate) }}
            </template>
          </Column>
          <Column
            field="reservationEndDate"
            sortable
            header="End Date"
            style="min-width: 5rem"
          >
            <template #editor="{ data, field }">
              <InputText type="date" v-model="data[field]" fluid />
            </template>
            <template #body="{ data }">
              {{ formatDate(data.reservationEndDate) }}
            </template>
          </Column>
          <Column
            field="firstResidentLastName"
            sortable
            header="Residents"
            style="min-width: 5rem"
          >
          </Column>
          <Column
            field="roomNumber"
            sortable
            header="Room Number"
            style="min-width: 5rem"
          >
            <template #editor="{ data, field }">
              <InputText v-model="data[field]" fluid />
            </template>
          </Column>
          <Column
            :rowEditor="true"
            style="width: 5%; min-width: 2rem"
            bodyStyle="text-align:center"
          ></Column>
        </DataTable>
      </div>
    </section>
  </Transition>
</template>

<style scoped>
#input7 {
  padding: 0;
  margin: 0;
}
</style>

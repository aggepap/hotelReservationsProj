<script setup lang="ts">
import DataTable from "primevue/datatable";
import Column from "primevue/column";
import { reactive, ref, onMounted } from "vue";
import { ResidentService } from "@/services/ApiServices";
import type { Resident } from "@/interfaces/ResidentInterfaces";
import { FilterMatchMode, FilterOperator } from "@primevue/core/api";

const filters = ref();
const state = reactive<{
  residents: Resident[];
  totalCount: number;
  pagesNumber: number;
  loading: boolean;
}>({
  residents: [],
  totalCount: 0,
  pagesNumber: 0,
  loading: true,
});

const representatives = ref([
  { firstname: "Amy Elsner", lastname: "amyelsner.png" },
]);

const initFilters = () => {
  filters.value = {
    global: { value: null, matchMode: FilterMatchMode.CONTAINS },
    firstname: {
      operator: FilterOperator.AND,
      constraints: [{ value: null, matchMode: FilterMatchMode.STARTS_WITH }],
    },
    lastname: {
      operator: FilterOperator.AND,
      constraints: [{ value: null, matchMode: FilterMatchMode.STARTS_WITH }],
    },
    representative: { value: null, matchMode: FilterMatchMode.IN },
    birthDate: {
      operator: FilterOperator.AND,
      constraints: [{ value: null, matchMode: FilterMatchMode.DATE_IS }],
    },
    countryCode: {
      operator: FilterOperator.AND,
      constraints: [{ value: null, matchMode: FilterMatchMode.EQUALS }],
    },
    gender: {
      operator: FilterOperator.OR,
      constraints: [{ value: null, matchMode: FilterMatchMode.EQUALS }],
    },
    room: {
      operator: FilterOperator.OR,
      constraints: [{ value: null, matchMode: FilterMatchMode.EQUALS }],
    },
    idNumber: {
      operator: FilterOperator.OR,
      constraints: [{ value: null, matchMode: FilterMatchMode.EQUALS }],
    },
    vatNumber: {
      operator: FilterOperator.OR,
      constraints: [{ value: null, matchMode: FilterMatchMode.EQUALS }],
    },
  };
};

initFilters();

let pageIndex = 0;
let pageSize = 12;
let orderBy = "lastname";

onMounted(() => {});

const fetchCustomers = async (
  pageIndex: number,
  pageSize: number,
  orderBy: string
) => {
  try {
    state.loading = true;
    const response = await ResidentService.getResidents(
      pageIndex,
      pageSize,
      orderBy
    );
    state.residents = response.data.data;
    state.totalCount = response.data.totalCount;
    state.pagesNumber = Math.ceil(state.totalCount / pageSize);
  } catch (error) {
    console.log(error);
  } finally {
    state.loading = false;
  }
};

const getSeverity = (status: any) => {
  switch (status) {
    case "unqualified":
      return "danger";

    case "qualified":
      return "success";

    case "new":
      return "info";

    case "negotiation":
      return "warn";

    case "renewal":
      return null;
  }
};
</script>

<template>
  <DataTable
    v-model:filters="filters"
    :value="state.residents"
    paginator
    :rows="10"
    dataKey="id"
    filterDisplay="row"
    :loading="state.loading"
    :globalFilterFields="[
      'name',
      'country.name',
      'representative.name',
      'status',
    ]"
  >
    <template #header>
      <div class="flex justify-end">
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
    <template #empty> No customers found. </template>
    <template #loading> Loading customers data. Please wait. </template>
    <Column field="name" header="Name" style="min-width: 12rem">
      <template #body="{ data }">
        {{ data.name }}
      </template>
      <template #filter="{ filterModel, filterCallback }">
        <InputText
          v-model="filterModel.value"
          type="text"
          @input="filterCallback()"
          placeholder="Search by name"
        />
      </template>
    </Column>
  </DataTable>
</template>

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
</script>

<template>
  <DataTable
    :value="state.residents"
    sortField="lastname"
    :sortOrder="-1"
    tableStyle="min-width: 50rem"
  >
    <Column
      field="firstname"
      header="Firstname"
      sortable
      style="width: 20%"
    ></Column>
    <Column
      field="lastname"
      header="Lastame"
      sortable
      style="width: 20%"
    ></Column>
    <Column field="countryCode" header="Country" :sortable="true"> </Column>
    <Column
      field="category"
      header="Category"
      sortable
      style="width: 20%"
    ></Column>
    <Column
      field="quantity"
      header="Quantity"
      sortable
      style="width: 20%"
    ></Column>
  </DataTable>
</template>

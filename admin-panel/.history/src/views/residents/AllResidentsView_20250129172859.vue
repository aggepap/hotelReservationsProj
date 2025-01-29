<script setup lang="ts">
import DataTable from "primevue/datatable";
import Column from "primevue/column";
import { reactive, ref, onMounted } from "vue";
import { ResidentService } from "@/services/ApiServices";
import type { Resident } from "@/interfaces/ResidentInterfaces";
import { FilterMatchMode, FilterOperator } from "@primevue/core/api";
import Button from "primevue/button";
import InputText from "primevue/inputtext";
import InputIcon from "primevue/inputicon";
import IconField from "primevue/iconfield";
import CountryFlag from "vue-country-flag-next";
import { DatePicker } from "primevue";

const filters = ref();
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
  };
};
initFilters();

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

let pageIndex = 0;
let pageSize = 300;
let orderBy = "lastname";

onMounted(() => {
  fetchCustomers(pageIndex, pageSize, orderBy);
  console.log(state.residents);
});

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
    state.residents = response.data;
    state.totalCount = response.data.totalCount;
    state.pagesNumber = Math.ceil(state.totalCount / pageSize);
    console.log(response.data);
  } catch (error) {
    console.log(error);
  } finally {
    state.loading = false;
  }
};
const clearFilter = () => {
  initFilters();
};
</script>

<template>
  <h2 class="text-black text-3xl text-center leading-5 font-semibold mb-8">
    Residents
  </h2>
  <div class="card">
    <DataTable
      v-model:filters="filters"
      :value="state.residents"
      paginator
      showGridlines
      :rows="10"
      dataKey="id"
      filterDisplay="menu"
      :loading="state.loading"
      :globalFilterFields="[
        'firstname',
        'lastname',
        'address',
        'birthDate',
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
      <template #empty> No customers found. </template>
      <template #loading> Loading customers data. Please wait. </template>
      <Column
        field="firstname"
        sortable
        header="Firstname"
        style="min-width: 12rem"
      >
        <template #filter="{ filterModel }">
          <InputText
            v-model="filterModel.value"
            type="text"
            placeholder="Search by firstname"
          />
        </template>
      </Column>
      <Column
        field="lastname"
        sortable
        header="Lastname"
        style="min-width: 12rem"
      >
        <template #filter="{ filterModel }">
          <InputText
            v-model="filterModel.value"
            type="text"
            placeholder="Search by Lastname"
          />
        </template>
      </Column>
      <Column
        field="countryCode"
        sortable
        header="Country"
        style="min-width: 12rem"
      >
        <template #body="{ data }">
          <div class="flex gap-4 items-end">
            <country-flag :country="data.countryCode" />

            <span>{{ data.countryCode }}</span>
          </div>
        </template>
        <template #filter="{ filterModel }">
          <InputText
            v-model="filterModel.value"
            type="text"
            placeholder="Search by Lastname"
          />
        </template>
      </Column>
      <Column
        field="countryCode"
        sortable
        header="Country"
        style="min-width: 12rem"
      >
        <template #body="{ data }">
          <div class="flex gap-4 items-end">
            <country-flag :country="data.countryCode" />

            <span>{{ data.countryCode }}</span>
          </div>
        </template>
        <template #filter="{ filterModel }">
          <DatePicker
            v-model="filterModel.value"
            type="text"
            placeholder="Search by Lastname"
          />
        </template>
      </Column>

      <!-- <Column
        field="activity"
        header="Activity"
        :showFilterMatchModes="false"
        style="min-width: 12rem"
      >
        <template #body="{ data }">
          <ProgressBar
            :value="data.activity"
            :showValue="false"
            style="height: 6px"
          ></ProgressBar>
        </template>
        <template #filter="{ filterModel }">
          <Slider v-model="filterModel.value" range class="m-4"></Slider>
          <div class="flex items-center justify-between px-2">
            <span>{{ filterModel.value ? filterModel.value[0] : 0 }}</span>
            <span>{{ filterModel.value ? filterModel.value[1] : 100 }}</span>
          </div>
        </template>
      </Column>
      <Column
        field="verified"
        header="Verified"
        dataType="boolean"
        bodyClass="text-center"
        style="min-width: 8rem"
      >
        <template #body="{ data }">
          <i
            class="pi"
            :class="{
              'pi-check-circle text-green-500 ': data.verified,
              'pi-times-circle text-red-500': !data.verified,
            }"
          ></i>
        </template>
        <template #filter="{ filterModel }">
          <label for="verified-filter" class="font-bold"> Verified </label>
          <Checkbox
            v-model="filterModel.value"
            :indeterminate="filterModel.value === null"
            binary
            inputId="verified-filter"
          />
        </template>
      </Column> -->
    </DataTable>
  </div>
</template>

<script setup lang="ts">
import DataTable from "primevue/datatable";
import Column from "primevue/column";
import { reactive, ref, onMounted } from "vue";
import { ResidentService } from "@/services/ApiServices";
import type { Resident } from "@/interfaces/ResidentInterfaces";
import { FilterMatchMode, FilterOperator } from "@primevue/core/api";

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
let pageSize = 12;
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
  <template>
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
          'name',
          'country.name',
          'representative.name',
          'balance',
          'status',
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
        <Column field="name" header="Name" style="min-width: 12rem">
          <template #body="{ data }">
            {{ data.name }}
          </template>
          <template #filter="{ filterModel }">
            <InputText
              v-model="filterModel.value"
              type="text"
              placeholder="Search by name"
            />
          </template>
        </Column>
        <Column
          header="Country"
          filterField="country.name"
          style="min-width: 12rem"
        >
          <template #body="{ data }">
            <div class="flex items-center gap-2">
              <img
                alt="flag"
                src="https://primefaces.org/cdn/primevue/images/flag/flag_placeholder.png"
                :class="`flag flag-${data.country.code}`"
                style="width: 24px"
              />
              <span>{{ data.country.name }}</span>
            </div>
          </template>
          <template #filter="{ filterModel }">
            <InputText
              v-model="filterModel.value"
              type="text"
              placeholder="Search by country"
            />
          </template>
          <template #filterclear="{ filterCallback }">
            <Button
              type="button"
              icon="pi pi-times"
              @click="filterCallback()"
              severity="secondary"
            ></Button>
          </template>
          <template #filterapply="{ filterCallback }">
            <Button
              type="button"
              icon="pi pi-check"
              @click="filterCallback()"
              severity="success"
            ></Button>
          </template>
          <template #filterfooter>
            <div class="px-4 pt-0 pb-4 text-center">Customized Buttons</div>
          </template>
        </Column>

        <Column
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
        </Column>
      </DataTable>
    </div>
  </template>

  <script setup>
    import { ref, onMounted } from "vue";
    import { CustomerService } from "@/service/CustomerService";
    import { FilterMatchMode, FilterOperator } from "@primevue/core/api";

    const customers = ref();
    const filters = ref();
    const representatives = ref([
      { name: "Amy Elsner", image: "amyelsner.png" },
      { name: "Anna Fali", image: "annafali.png" },
      { name: "Asiya Javayant", image: "asiyajavayant.png" },
      { name: "Bernardo Dominic", image: "bernardodominic.png" },
      { name: "Elwin Sharvill", image: "elwinsharvill.png" },
      { name: "Ioni Bowcher", image: "ionibowcher.png" },
      { name: "Ivan Magalhaes", image: "ivanmagalhaes.png" },
      { name: "Onyama Limba", image: "onyamalimba.png" },
      { name: "Stephen Shaw", image: "stephenshaw.png" },
      { name: "XuXue Feng", image: "xuxuefeng.png" },
    ]);
    const statuses = ref([
      "unqualified",
      "qualified",
      "new",
      "negotiation",
      "renewal",
      "proposal",
    ]);
    const loading = ref(true);

    onMounted(() => {
      CustomerService.getCustomersMedium().then((data) => {
        customers.value = getCustomers(data);
        loading.value = false;
      });
    });

    const initFilters = () => {
      filters.value = {
        global: { value: null, matchMode: FilterMatchMode.CONTAINS },
        name: {
          operator: FilterOperator.AND,
          constraints: [
            { value: null, matchMode: FilterMatchMode.STARTS_WITH },
          ],
        },
        "country.name": {
          operator: FilterOperator.AND,
          constraints: [
            { value: null, matchMode: FilterMatchMode.STARTS_WITH },
          ],
        },
        representative: { value: null, matchMode: FilterMatchMode.IN },
        date: {
          operator: FilterOperator.AND,
          constraints: [{ value: null, matchMode: FilterMatchMode.DATE_IS }],
        },
        balance: {
          operator: FilterOperator.AND,
          constraints: [{ value: null, matchMode: FilterMatchMode.EQUALS }],
        },
        status: {
          operator: FilterOperator.OR,
          constraints: [{ value: null, matchMode: FilterMatchMode.EQUALS }],
        },
        activity: { value: [0, 100], matchMode: FilterMatchMode.BETWEEN },
        verified: { value: null, matchMode: FilterMatchMode.EQUALS },
      };
    };

    initFilters();

    const formatDate = (value) => {
      return value.toLocaleDateString("en-US", {
        day: "2-digit",
        month: "2-digit",
        year: "numeric",
      });
    };
    const formatCurrency = (value) => {
      return value.toLocaleString("en-US", {
        style: "currency",
        currency: "USD",
      });
    };
    const clearFilter = () => {
      initFilters();
    };
    const getCustomers = (data) => {
      return [...(data || [])].map((d) => {
        d.date = new Date(d.date);

        return d;
      });
    };
    const getSeverity = (status) => {
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
</template>

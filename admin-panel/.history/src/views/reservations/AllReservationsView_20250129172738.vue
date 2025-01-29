<script setup>
import { ref, computed } from "vue";

const date = new Date();
const formattedDate = (daysAdded = 0) => {
  const newDate = new Date(date.value);
  date.setDate(date.getDate() + daysAdded);

  const year = date.getFullYear();
  const month = String(date.getMonth() + 1).padStart(2, "0");
  const day = String(date.getDate()).padStart(2, "0");

  return `${year}-${month}-${day}`;
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
      <template>
        <div class="card">
          <DataTable
            v-model:filters="filters"
            :value="customers"
            paginator
            :rows="10"
            dataKey="id"
            filterDisplay="row"
            :loading="loading"
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
              <template #filter="{ filterModel, filterCallback }">
                <InputText
                  v-model="filterModel.value"
                  type="text"
                  @input="filterCallback()"
                  placeholder="Search by country"
                />
              </template>
            </Column>
            <Column
              header="Agent"
              filterField="representative"
              :showFilterMenu="false"
              style="min-width: 14rem"
            >
              <template #body="{ data }">
                <div class="flex items-center gap-2">
                  <img
                    :alt="data.representative.name"
                    :src="`https://primefaces.org/cdn/primevue/images/avatar/${data.representative.image}`"
                    style="width: 32px"
                  />
                  <span>{{ data.representative.name }}</span>
                </div>
              </template>
              <template #filter="{ filterModel, filterCallback }">
                <MultiSelect
                  v-model="filterModel.value"
                  @change="filterCallback()"
                  :options="representatives"
                  optionLabel="name"
                  placeholder="Any"
                  style="min-width: 14rem"
                  :maxSelectedLabels="1"
                >
                  <template #option="slotProps">
                    <div class="flex items-center gap-2">
                      <img
                        :alt="slotProps.option.name"
                        :src="`https://primefaces.org/cdn/primevue/images/avatar/${slotProps.option.image}`"
                        style="width: 32px"
                      />
                      <span>{{ slotProps.option.name }}</span>
                    </div>
                  </template>
                </MultiSelect>
              </template>
            </Column>
            <Column
              field="status"
              header="Status"
              :showFilterMenu="false"
              style="min-width: 12rem"
            >
              <template #body="{ data }">
                <Tag
                  :value="data.status"
                  :severity="getSeverity(data.status)"
                />
              </template>
              <template #filter="{ filterModel, filterCallback }">
                <Select
                  v-model="filterModel.value"
                  @change="filterCallback()"
                  :options="statuses"
                  placeholder="Select One"
                  style="min-width: 12rem"
                  :showClear="true"
                >
                  <template #option="slotProps">
                    <Tag
                      :value="slotProps.option"
                      :severity="getSeverity(slotProps.option)"
                    />
                  </template>
                </Select>
              </template>
            </Column>
            <Column
              field="verified"
              header="Verified"
              dataType="boolean"
              style="min-width: 6rem"
            >
              <template #body="{ data }">
                <i
                  class="pi"
                  :class="{
                    'pi-check-circle text-green-500': data.verified,
                    'pi-times-circle text-red-400': !data.verified,
                  }"
                ></i>
              </template>
              <template #filter="{ filterModel, filterCallback }">
                <Checkbox
                  v-model="filterModel.value"
                  :indeterminate="filterModel.value === null"
                  binary
                  @change="filterCallback()"
                />
              </template>
            </Column>
          </DataTable>
        </div>
      </template>
    </main>
  </section>
</template>

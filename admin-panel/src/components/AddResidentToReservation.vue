<script setup>
import { FormKit } from "@formkit/vue";
import { computed, onMounted, ref } from "vue";
import { ReservationService } from "@/services/ApiServices";

const props = defineProps({
  residents: { type: Array, required: true },
  reservationId: { type: Number, required: true },
});

onMounted(async () => {
  console.log(props.residents);
});

let selectedResident = ref(null);
const emit = defineEmits(["residentAdded"]);

const residentOptions = computed(() => {
  if (props.residents && props.residents.length > 0) {
    return props.residents.reduce((options, resident) => {
      options[resident.id] = `${resident.firstname} ${resident.lastname}`;
      return options;
    }, {});
    // biome-ignore lint/style/noUselessElse: <explanation>
  } else {
    return {};
  }
});

const handleSubmit = async (data) => {
  selectedResident = Number.parseInt(data.resident);
  try {
    ReservationService.addResidentToReservation(
      props.reservationId,
      selectedResident
    );
    emit("residentAdded", selectedResident);
  } catch (error) {
    console.log(error);
  }
};
</script>

<template>
  <div class="bg-white rounded-md grid place-items-center py-10 px-10">
    <FormKit type="form" @submit="handleSubmit" submit-label="Choose Resident">
      <FormKit
        name="resident"
        type="select"
        :options="residentOptions"
        submit-label="Choose Resident"
      />
    </FormKit>
  </div>
</template>

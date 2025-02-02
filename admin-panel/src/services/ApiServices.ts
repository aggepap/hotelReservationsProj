import type { AddRoomData } from "@/interfaces/RoomInterfaces";
import type { addResidentData } from "@/interfaces/ResidentInterfaces";
import type {
  AddReservationData,
  UpdateReservationData,
} from "@/interfaces/ReservationInterfaces";
import { useToast } from "vue-toastification";
import AddResidentToReservation from "@/components/AddResidentToReservation.vue";
const toast = useToast();

/**
 * RoomService
 */
export const RoomService = {
  getRooms: async (pageIndex: number, pageSize: number) => {
    const response = await fetch(
      `http://localhost:8080/api/rooms?page=${pageIndex}&size=${pageSize}`
    );
    return response.json();
  },
  addRoom: async (room: AddRoomData) => {
    const response = await fetch("/apiUrl/api/rooms/add", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(room),
    });
    if (!response.ok) {
      const error = await response.json();
      toast.error(error.message);
      throw new Error(`HTTP error! status: ${response.status}`);
    }
    toast.success("Room added successfully");
    return response.json();
  },
};

/**
 * ResidentService
 */
export const ResidentService = {
  addResident: async (resident: addResidentData) => {
    const response = await fetch("http://localhost:8080/api/residents/add", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(resident),
    });
    console.log(response);

    if (!response.ok) {
      const error = await response.json();
      toast.error(error.message);
      console.error("Error:", await response.text());
    } else {
      toast.success("Resident added successfully");
      return response.json();
    }
  },

  getResidents: async (sortedBy: string) => {
    const response = await fetch(
      `http://localhost:8080/api/residents/all?sortedBy=${sortedBy}`
    );
    return response.json();
  },
  getResidentById: async (id: number) => {
    const response = await fetch(`http://localhost:8080/api/residents/${id}`);
    return response.json();
  },
};
/**
 * ReservationService
 */

export const ReservationService = {
  addReservation: async (reservation: AddReservationData) => {
    const response = await fetch("http://localhost:8080/api/reservations/add", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(reservation),
    });
    if (!response.ok) {
      const error = await response.json();
      toast.error(error.message);
      console.log(response);

      throw new Error(`HTTP error! status: ${response.status}`);
    }
    toast.success("Reservation added successfully");
    return response.json();
  },
  updateReservation: async (updateData: UpdateReservationData) => {
    const response = await fetch(
      "http://localhost:8080/api/reservations/update",
      {
        method: "PATCH",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(updateData),
      }
    );
    if (!response.ok) {
      const error = await response.json();
      toast.error(error.message);
      console.log(error);
      throw new Error(`HTTP error! status: ${response.status}`);
    }
    toast.success("Reservation updated successfully");
    return response.json();
  },

  addResidentToReservation: async (
    reservationId: number,
    residentId: number
  ) => {
    const response = await fetch(
      `http://localhost:8080/api/reservations/${reservationId}/add-resident/${residentId}`,
      {
        method: "PATCH",
        headers: {
          "Content-Type": "application/json",
        },
      }
    );

    if (!response.ok) {
      const error = await response.json();
      toast.error(error.message);
      console.log(response);
    } else {
      toast.success("Resident added to reservation successfully");
      return response.json();
    }
  },
  removeResidentFromReservation: async (
    reservationId: number,
    residentId: number
  ) => {
    const response = await fetch(
      `http://localhost:8080/api/reservations/${reservationId}/remove-resident/${residentId}`,
      {
        method: "PATCH",
        headers: {
          "Content-Type": "application/json",
        },
      }
    );

    if (!response.ok) {
      const error = await response.json();
      toast.error(error.message);
      console.log(response);
    } else {
      toast.info("Resident was deleted from reservation successfully");
      return response.json();
    }
  },
  getAllReservations: async (
    sortedBy: string,
    fromDate: string,
    toDate: string,
    isActive?: boolean
  ) => {
    const response = await fetch(
      `http://localhost:8080/api/reservations/all?sortedBy=${sortedBy}&fromDate=${fromDate}&toDate=${toDate}&isActive=${isActive}`
    );
    return response.json();
  },
  getReservationById: async (id: number) => {
    const response = await fetch(
      `http://localhost:8080/api/reservations/${id}`
    );
    return response.json();
  },
};

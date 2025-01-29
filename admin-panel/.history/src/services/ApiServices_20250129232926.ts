import type { AddRoomData } from "@/interfaces/RoomInterfaces";
import type { addResidentData } from "@/interfaces/ResidentInterfaces";
import type { AddReservationData } from "@/interfaces/ReservationInterfaces";
import { useToast } from "vue-toastification";
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
      `http://localhost:8080/api/residents/all?sortedby=${sortedBy}`
    );
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
      throw new Error(`HTTP error! status: ${response.status}`);
    }
    toast.success("Reservation added successfully");
    return response.json();
  },
  getReservations: async (
    pageIndex: number,
    pageSize: number,
    sortedBy: string,
    fromDate: string,
    toDate: string
  ) => {
    const response = await fetch(
      `http://localhost:8080/api/reservations/all?page=${pageIndex}&size=${pageSize}&sortedBy=${sortedBy}&fromDate=${fromDate}&toDate=${toDate}`
    );
    return response.json();
  },
};

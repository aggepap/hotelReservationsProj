import type { AddRoomData } from "@/interfaces/RoomInterfaces";
import type { addResidentData } from "@/interfaces/ResidentInterfaces";
import { useToast } from "vue-toastification";
const toast = useToast();

export const RoomService = {
  getRooms: async (pageIndex: number, pageSize: number) => {
    const response = await fetch(
      `/apiUrl/api/rooms?page=${pageIndex}&size=${pageSize}`
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
      throw new Error(`HTTP error! status: ${response.status}`);
    }
    return response.json();
    toast.success("Room added successfully");
  },
};

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

  getResidents: async (pageIndex: number, pageSize: number) => {
    const response = await fetch(
      `http://localhost:8080/api/residents?page=${pageIndex}&size=${pageSize}`
    );
    return response.json();
  },
};

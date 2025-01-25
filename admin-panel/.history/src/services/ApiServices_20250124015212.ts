import type { AddRoomData } from "@/interfaces/RoomInterfaces";
import type { addResidentData } from "@/interfaces/ResidentInterfaces";

export const RoomService = {
  getRooms: async (pageIndex: number, pageSize: number) => {
    const response = await fetch(
      `http://localhost:8080/api/rooms?page=${pageIndex}&size=${pageSize}`
    );
    return response.json();
  },
  addRoom: async (room: AddRoomData) => {
    const response = await fetch("http://localhost:8080/api/rooms/add", {
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
      throw new Error(
        `HTTP error! status: ${response.status} ${response.statusText}`
      );
    }
    return response.json();
  },
};

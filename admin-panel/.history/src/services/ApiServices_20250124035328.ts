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
      // Handle errors based on status code
      if (response.status === 400) {
        // Bad request (e.g., validation error)
        console.error("Bad request:", await response.text());
        // You can display a user-friendly message based on the error details from the response
      } else if (response.status === 404) {
        // Not found (e.g., resource not found)
        console.error("Resource not found");
        // Display a message indicating the resource was not found
      } else if (response.status === 409) {
        // Conflict (e.g., resource already exists)
        console.error("Conflict:", await response.text());
        // Display a message indicating a conflict occurred
      } else {
        // Other errors (e.g., internal server error)
        console.error("Unexpected error:", await response.text());
        // Display a generic error message
      }
    } else {
      return response.json();
    }
  },
};

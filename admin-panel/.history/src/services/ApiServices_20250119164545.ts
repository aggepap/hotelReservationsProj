export const RoomService = {
  getRooms: async () => {
    const response = await fetch("http://localhost:3000/rooms");
    return response.json();
  },
  addRoom: async (room: AddRoomData) => {
    const response = await fetch("http://localhost:8000/api/rooms/add", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(room),
    });
    return response.json();
  },
};

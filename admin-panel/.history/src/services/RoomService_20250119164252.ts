export const RoomService = {
  getAllRooms: async () => {
    const response = await fetch("http://localhost:3000/rooms");
    const data = await response.json();
    return data;
  },
  addRoom: async (room: Room) => {
    const response = await fetch("http://localhost:3000/rooms", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(room),
    });
    const data = await response.json();
    return data;
  },
};

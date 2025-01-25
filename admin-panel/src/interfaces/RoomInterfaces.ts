export interface AddRoomData {
  roomNumber: number;
  floor: number;
  capacity: number;
  hasSeaView: boolean;
}
export interface Room {
  id: number;
  roomNumber: number;
  floor: number;
  capacity: number;
  hasSeaView: boolean;
  isAvailable: boolean;
}

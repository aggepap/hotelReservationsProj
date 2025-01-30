export interface AddReservationData {
  reservationStartDate: Date;
  reservationEndDate: Date;
  roomNumber: string;
  guestsNumber: number;
  firstname: string;
  lastname: string;
  vat: string;
  idNumber: string;
  countryCode: string;
  birthDate: Date;
  gender: string;
  email: string;
  address: string;
  phoneNumber: string;
}

export interface UpdateReservationData {
  id: number;
  reservationStartDate: Date;
  reservationEndDate: Date;
  roomNumber: string;
  guestsNumber: number;
}

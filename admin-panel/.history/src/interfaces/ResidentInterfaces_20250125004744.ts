export interface addResidentData {
  firstName: string;
  lastName: string;
  email: string;
  vat: string;
  idNumber: string;
  address: string;
  doB: string;
  gender: string;
  country: string;
  phoneNumber: string;
}

export interface Resident {
  firstName: string;
  lastName: string;
  email: string;
  vat: string;
  idNumber: string;
  address: string;
  doB: Date;
  gender: string;
  countryCode: string;
  phoneNumber: string;
}

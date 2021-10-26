export type AppealType = "IP_BAN" | "BAN" | "MUTE";

export type Email = {
  address: string;
  domain: string;
}

export type Phone = {
  country: number;
  number: number;
}

export type Discord = {
  name: string;
  tag: number;
}
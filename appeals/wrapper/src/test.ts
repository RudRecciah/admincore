import { Server } from "./Appeal";

const test = async (): Promise<void> => {
  const server = new Server("localhost", 3000);
  await server.newAppeal("86a8f35b-c64e-4315-bf9e-f03efdbe1a19", "BAN", "FUCK", "bro sus", false, { address: "test", domain: "example.com" }, { country: 1, number: 1234567890 }, { name: "sus", tag: 1234 }, 246080207704817664);
};

test().catch(console.log);
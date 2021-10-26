import fetch from "node-fetch";
import { AppealType, Discord, Email, Phone } from "./types";
import { lookupUUID, NameMCUser } from "namemc";

export class Server {

  private readonly ip: string;
  private readonly port: number;

  /**
   * Makes a new server
   * @param ip The IP address/domain name of the server
   * @param port The port of the server
   */
  constructor(ip: string, port: number) {
    this.ip = ip;
    this.port = port;
  }

  /**
   * Attempts to appeal a player's ban
   * @param uuid The uuid of the player to appeal
   * @param appealType The type of punishment that should
   * @param reason The reason for revoking this punishment
   * @param evidence The evidence to back up this reason
   * @param punishedBefore Whether or not the player has been punished before
   * @param email The player's email
   * @param number The player's phone number
   * @param discordName The player's discord name and tag
   * @param discordId The player's discord ID
   * @throws Error An error will be thrown if an appeal is invalid in any form, if the server returns an error, or if the request times out
   * @returns Returns true if the appeal was successfully created and false if the appeal was not created and there was no known error that occoured to prevent it from being created
   */
  public async newAppeal(uuid: string, appealType: AppealType, reason?: string, evidence?: string, punishedBefore?: boolean, email?: Email, number?: Phone, discordName?: Discord, discordId?: number): Promise<boolean> {
    // make sure user exists
    let user: NameMCUser;
    try {
      user = await lookupUUID(uuid);
    }catch(e) {
      throw new Error("Player doesnt exist!");
    }
    // set values to defaults
    const id = Date.now();
    reason = reason ?? "";
    evidence = evidence ?? "";
    const finalPunishedBefore = punishedBefore ?? "";
    let finalPunishedBeforeString: string;
    if(typeof finalPunishedBefore !== "string") {
      finalPunishedBeforeString = finalPunishedBefore ? "yes" : "no";
    }else{
      finalPunishedBeforeString = finalPunishedBefore;
    }
    const finalEmail = typeof email === "undefined" ? "" : `${email.address}@${email.domain}`;
    const finalNumber = typeof number === "undefined" ? "" : `+${number.country} ${number.number}`;
    const finalDiscordName = typeof discordName === "undefined" ? "" : `@${discordName.name}#${discordName.tag}`;
    const finalDiscordId = discordId ?? "";
    // set headers
    const appealHeaders = new Headers();
    appealHeaders.append("id", `["${id}"]`);
    appealHeaders.append("name", `["${user.currentName}"]`);
    appealHeaders.append("uuid", `["${user.uuid}"]`);
    appealHeaders.append("type", `["${appealType}"]`);
    appealHeaders.append("reason", `["${reason}"]`);
    appealHeaders.append("evidence", `["${evidence}"]`);
    appealHeaders.append("punishedBefore", `["${finalPunishedBeforeString}"]`);
    appealHeaders.append("email", `["${finalEmail}"]`);
    appealHeaders.append("number", `["${finalNumber}"]`);
    appealHeaders.append("discordName", `["${finalDiscordName}"]`);
    appealHeaders.append("discordId", `["${finalDiscordId}"]`);
    // send appeal
    const appeal = await fetch(`${this.ip}:${this.port}`, { headers: appealHeaders });
    switch(appeal.status) {
      case 201:
        return true;
      case 400:
        if(id.toString() === "" || uuid === "") {
          throw new Error("The ID and/or UUID can't be blank!");
        }else{
          throw new Error("Invalid character in request! Valid characters: [^-0-9a-zA-Z_,.#@ ]");
        }
      case 409:
        throw new Error("Appeal already exists!");
      case 412:
        throw new Error("Player is not punished!");
      case 501:
        throw new Error("Server error 501!");
      case 408:
        throw new Error("Timed out!");
      default:
        return false;
    }
  }

}
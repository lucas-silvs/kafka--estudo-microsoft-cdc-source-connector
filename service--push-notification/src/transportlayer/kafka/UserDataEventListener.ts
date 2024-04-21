import { UserDataEventModel } from "./models/UserDataEventModel";

export interface UserDataEventListener {
  startListenEvents(): void;
}

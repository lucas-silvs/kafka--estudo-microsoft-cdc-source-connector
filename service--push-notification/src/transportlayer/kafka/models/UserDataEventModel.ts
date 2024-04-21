import UserDataEvent from "./UserDataEvent";

export interface UserDataEventModel {
  userDataBefore: UserDataEvent;
  userDataAfter: UserDataEvent;
  operation: string;
}

import UserDataEvent from "./UserDataEvent";

export interface MessageUserEventModel {
  userDataBefore: UserDataEvent;
  userDataAfter: UserDataEvent;
  operation: string;
}

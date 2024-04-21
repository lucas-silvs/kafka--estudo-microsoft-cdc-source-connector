import { SchemaRegistry } from "@kafkajs/confluent-schema-registry";
import { UserNotificationUserCaseImpl } from "./interactors/UserNotificationUseCaseImpl";
import UserDataEventListener from "./transportlayer/kafka/UserDataEventListernerImpl";
import UserDataEventListenerImpl from "./transportlayer/kafka/UserDataEventListernerImpl";

const userNotificationUseCase: UserNotificationUserCaseImpl =
  new UserNotificationUserCaseImpl();

const userDataEventListener = new UserDataEventListener(
  userNotificationUseCase,
  UserDataEventListenerImpl.createKafkaConsumer()
);

userDataEventListener.startListenEvents();

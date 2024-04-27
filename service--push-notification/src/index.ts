import { SchemaRegistry } from "@kafkajs/confluent-schema-registry";
import { UserNotificationUserCaseImpl } from "./interactors/UserNotificationUseCaseImpl";
import UserDataEventListener from "./transportlayer/kafka/UserDataEventListernerImpl";
import { Kafka } from "kafkajs";
import { config } from "./configs/configs";
import { ClientEmailDataSourceImpl } from "./datasources/email/ClientEmailDataSourceImpl";
import { ClientEmailDataSource } from "./datasources/email/ClientEmailDataSource";
import { NotificationRepositoryImpl } from "./repositories/NotificationRepositoryImpl";
import { NotificationRepository } from "./repositories/NotificationRepository";

const kafka = new Kafka({
  clientId: config.KAFKA_CLIENT_ID,
  brokers: [config.KAFKA_BOOTSTRAP_SERVERS],
});
const consumer = kafka.consumer({ groupId: config.KAFKA_GROUP_ID });

const emailDatasource: ClientEmailDataSource = new ClientEmailDataSourceImpl();

const notificationRepository: NotificationRepository =
  new NotificationRepositoryImpl(emailDatasource);

const userNotificationUseCase: UserNotificationUserCaseImpl =
  new UserNotificationUserCaseImpl(notificationRepository);

const userDataEventListener = new UserDataEventListener(
  userNotificationUseCase,
  consumer,
  new SchemaRegistry({ host: config.KAFKA_SCHEMA_REGISTRY_URL })
);

userDataEventListener.startListenEvents();

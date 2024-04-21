import {
  Consumer,
  ConsumerSubscribeTopics,
  EachMessagePayload,
  Kafka,
} from "kafkajs";
import { UserDataEventListener } from "./UserDataEventListener";
import { SchemaRegistry } from "@kafkajs/confluent-schema-registry";
import { UserDataEventModel } from "./models/UserDataEventModel";
import { UserData } from "../../entities/UserData";
import { UserNotificationUserCase } from "../../interactors/UserNotificationUseCase";

export default class UserDataEventListenerImpl
  implements UserDataEventListener
{
  private schemaRegistry: SchemaRegistry;

  public constructor(
    private userNotificationUseCase: UserNotificationUserCase,
    private kafkaConsumer: Consumer
  ) {
    this.schemaRegistry = new SchemaRegistry({ host: "http://localhost:8081" });
  }

  public async startListenEvents(): Promise<void> {
    const topic: ConsumerSubscribeTopics = {
      topics: ["clients.cdc.clients.users"],
      fromBeginning: false,
    };

    try {
      await this.kafkaConsumer.connect();
      await this.kafkaConsumer.subscribe(topic);

      await this.kafkaConsumer.run({
        eachMessage: async (messagePayload: EachMessagePayload) => {
          const { topic, partition, message } = messagePayload;
          const prefix = `${topic}[${partition} | ${message.offset}] / ${message.timestamp}`;

          if (message.value === null) {
            console.log(`- ${prefix} ${"message.value is null"}`);
            return;
          }
          const messageValue = await this.schemaRegistry.decode(
            message.value as Buffer
          );

          const userData: UserDataEventModel = {
            userDataBefore: messageValue.before,
            userDataAfter: messageValue.after,
            operation: messageValue.op,
          };
          const userDataEntityBefore: UserData = {
            address: userData.userDataAfter?.address,
            id: userData.userDataAfter?.id,
            name: userData.userDataAfter?.id,
          };

          const userDataEntityAfter: UserData = {
            address: userData.userDataBefore?.address,
            id: userData.userDataBefore?.id,
            name: userData.userDataBefore?.id,
          };

          this.userNotificationUseCase.pushNotification({
            operation: userData.operation,
            userDataBefore: userDataEntityBefore,
            userDataAfter: userDataEntityAfter,
          });

          console.log(userData);
        },
      });
    } catch (error) {
      console.log("Error: ", error);
    }
  }

  public async shutdown(): Promise<void> {
    await this.kafkaConsumer.disconnect();
  }

  public static createKafkaConsumer(): Consumer {
    const kafka = new Kafka({
      clientId: "teste",
      brokers: ["localhost:9092"],
    });
    const consumer = kafka.consumer({ groupId: "nodejs" });
    return consumer;
  }
}

import {
  Consumer,
  ConsumerSubscribeTopics,
  EachMessagePayload,
  Kafka,
} from "kafkajs";
import { UserDataEventListener } from "./UserDataEventListener";
import { SchemaRegistry } from "@kafkajs/confluent-schema-registry";
import { MessageUserEventModel } from "./models/MessageUserEventModel";
import { UserData } from "../../entities/UserData";
import { UserNotificationUserCase } from "../../interactors/UserNotificationUseCase";
import { config } from "../../configs/configs";

export default class UserDataEventListenerImpl
  implements UserDataEventListener
{
  public constructor(
    private userNotificationUseCase: UserNotificationUserCase,
    private kafkaConsumer: Consumer,
    private schemaRegistry: SchemaRegistry
  ) {}

  public async startListenEvents(): Promise<void> {
    const topic: ConsumerSubscribeTopics = {
      topics: [config.KAFKA_TOPIC],
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

          const userData: MessageUserEventModel = {
            userDataBefore: messageValue.before,
            userDataAfter: messageValue.after,
            operation: messageValue.op,
          };
          const userDataEntityAfter: UserData = {
            address: userData.userDataAfter?.address,
            id: userData.userDataAfter?.id,
            name: userData.userDataAfter?.id,
            email: "lucas@aluno.faculdadeimpacta.com.br",
          };

          const userDataEntityBefore: UserData = {
            address: userData.userDataBefore?.address,
            id: userData.userDataBefore?.id,
            name: userData.userDataBefore?.id,
            email: "lucas@aluno.faculdadeimpacta.com.br",
          };

          this.userNotificationUseCase.pushNotification({
            operation: userData.operation,
            userDataBefore: userDataEntityBefore,
            userDataAfter: userDataEntityAfter,
          });
        },
      });
    } catch (error) {
      console.log("Error: ", error);
    }
  }

  public async shutdown(): Promise<void> {
    await this.kafkaConsumer.disconnect();
  }
}

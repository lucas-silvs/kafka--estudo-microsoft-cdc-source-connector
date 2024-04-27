import { ClientEmailDataSource } from "../datasources/email/ClientEmailDataSource";
import { EmailModelDatasource } from "../datasources/email/models/EmailModelDatasource";
import { NotificationRepository } from "./NotificationRepository";

export class NotificationRepositoryImpl implements NotificationRepository {
  constructor(private emailClient: ClientEmailDataSource) {}

  sendNotification(
    userContactTarget: string,
    message: string,
    subjectMessage: string
  ): void {
    const emailData: EmailModelDatasource = {
      emailDestination: userContactTarget,
      emailMessage: message,
      subject: subjectMessage,
    };
    console.log(`Enviando notificação para ${emailData.emailDestination}`);
  }
}

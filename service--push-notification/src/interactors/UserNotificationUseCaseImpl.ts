import { ClientEmailDataSource } from "../datasources/email/ClientEmailDataSource";
import { EmailModelDatasource } from "../datasources/email/models/EmailModelDatasource";
import {
  DataNotificationModelUseCase,
  UserNotificationUserCase,
} from "./UserNotificationUseCase";

export class UserNotificationUserCaseImpl implements UserNotificationUserCase {
  public constructor(private emailClient: ClientEmailDataSource) {}

  pushNotification(data: DataNotificationModelUseCase): void {
    let message;
    let subject;
    let emailDestination;
    switch (data.operation) {
      case "u":
        // Lógica para atualização
        message = `usuario ${data.userDataAfter?.id} atualizado com sucesso!`;
        subject = `Atualização de usuario ${data.userDataAfter?.id}`;
        emailDestination = data.userDataAfter?.email;
        break;
      case "c":
        // Lógica para criação
        message = `usuario ${data.userDataAfter?.id} criado com sucesso!`;
        subject = `Criação de usuario ${data.userDataAfter?.id}`;
        emailDestination = data.userDataAfter?.email;

        break;
      case "d":
        message = `usuario ${data.userDataBefore?.id} deletado com sucesso!`;
        subject = `Deleção de usuario ${data.userDataBefore?.id}`;
        emailDestination = data.userDataAfter?.email;
        break;

      default:
        console.log("operação não reconhecida!");
    }
    const emailData: EmailModelDatasource = {
      emailDestination: emailDestination,
      emailMessage: message,
      subject: subject,
    };

    this.emailClient.sendEmail(emailData);
  }
}

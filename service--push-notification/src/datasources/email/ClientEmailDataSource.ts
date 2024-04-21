import { EmailModelDatasource } from "./models/EmailModelDatasource";

export interface ClientEmailDataSource {
  sendEmail(emailData: EmailModelDatasource): void;
}

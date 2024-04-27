import { createTransport, Transporter } from "nodemailer";
import { ClientEmailDataSource } from "./ClientEmailDataSource";
import { EmailModelDatasource } from "./models/EmailModelDatasource";
import { config } from "../../configs/configs";

export class ClientEmailDataSourceImpl implements ClientEmailDataSource {
  private transporter: Transporter;

  constructor() {
    this.transporter = createTransport({
      host: config.EMAIL_CLIENT_HOST,
      port: parseInt(config.EMAIL_CLIENT_PORT),
      auth: {
        user: config.EMAIL_CLIENT_USER,
        pass: config.EMAIL_CLIENT_PASS,
      },
    });
  }

  async sendEmail(email: EmailModelDatasource): Promise<void> {
    let mailOptions = {
      from: config.EMAIL_CLIENT_USER,
      to: email.emailDestination,
      subject: email.subject,
      text: email.emailMessage,
    };

    console.log(mailOptions);

    this.transporter.sendMail(mailOptions, function (error, info) {
      console.log("error", error);
      console.log("info", info);
      if (error) {
        return error;
      } else {
        return "E-mail enviado com sucesso!";
      }
    });
  }
}

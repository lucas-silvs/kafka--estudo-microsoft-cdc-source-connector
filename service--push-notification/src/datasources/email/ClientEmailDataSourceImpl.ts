import { createTransport, Transporter } from "nodemailer";
import { config } from "../../configs/configs";
import { NotificationRepository } from "../../repositories/NotificationRepository";

export class ClientEmailDataSourceImpl implements NotificationRepository {
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

  async sendNotification(
    userContactTarget: string,
    message: string,
    subjectMessage: string
  ): Promise<void> {
    let mailOptions = {
      from: config.EMAIL_CLIENT_USER,
      to: userContactTarget,
      subject: subjectMessage,
      text: message,
      html: `
      <div style="font-family: 'Helvetica Neue', Helvetica, Arial, sans-serif; padding: 20px; line-height: 1.5;">
        <h2 style="color: #333;">${subjectMessage}</h2>
        <p>${message}</p>
        <p>Atenciosamente,</p>
        <p>Lojinha :)</p>
      </div>
    `,
    };

    try {
      const info = await this.transporter.sendMail(mailOptions);
      console.log("email enviado com sucesso: %s", userContactTarget);
    } catch (error) {
      console.log(
        "error ao enviar email: %s -- email com problema: ",
        (error as Error).message,
        userContactTarget
      );
    }
  }
}

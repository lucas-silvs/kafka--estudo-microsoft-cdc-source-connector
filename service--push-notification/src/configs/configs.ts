// config.ts
import { z } from "zod";
import dotenv from "dotenv";

dotenv.config();

// Esquema Zod para as variáveis de ambiente
const envSchema = z.object({
  APPLICATION_NAME: z.string(),
  APPLICATION_VERSION: z.string(),
  KAFKA_BOOTSTRAP_SERVERS: z.string(),
  KAFKA_SCHEMA_REGISTRY_URL: z.string().url(),
  KAFKA_GROUP_ID: z.string(),
  KAFKA_CLIENT_ID: z.string(),
  KAFKA_TOPIC: z.string(),
  EMAIL_CLIENT_USER: z.string().email(),
  EMAIL_CLIENT_PASS: z.string(),
  EMAIL_CLIENT_PORT: z.string(),
  EMAIL_CLIENT_HOST: z.string(),
});

// Função para extrair e validar as variáveis de ambiente
export const getConfig = () => {
  try {
    // Tenta parsear as variáveis de ambiente usando o esquema definido
    const config = envSchema.parse(process.env);
    return config;
  } catch (error) {
    if (error instanceof z.ZodError) {
      // Loga detalhes dos erros de cada campo não validado
      console.error("Erro de validação de configuração:");
      error.errors.forEach((err) => {
        console.error(`Campo: ${err.path[0]}, Mensagem: ${err.message}`);
      });
      process.exit(1); // Encerra a aplicação se a configuração for inválida
    }
    throw error; // Relança qualquer outro erro que não seja um ZodError
  }
};

// Exporta uma instância das configurações validadas
export const config = getConfig();

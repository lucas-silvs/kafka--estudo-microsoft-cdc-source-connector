# kafka--estudo-microsoft-cdc-source-connector
Projeto para estudo de aplicação para estudo do uso do design pattern Change Data Capture (CDC) para monitorar alterações em um banco de dados com o Kafka


## Sumário



## Tecnologias

- Microsoft SQL Server
- Apache Kafka 3.5
- Kafka Connectors
- **(A definir linguagem de programação dos serviços)**


## Arquitetura de Software para Desenvolvimento

Todos os projetos desenvolvidos no estudo irão seguir o design arquitetural da Arquitetura Hexagonal, afim de pratica a separação dos componentes internos e externos da camada de negócio. Os projetos terão a estrutura do desenho a seguir:

![Arquitetura Hexagonal - Estruturas](./imgs/Hexagonal-architecture-draw-structures.png)


Estrutura do projeto:

- **Transport Layer**: Camada para a entrada de dados do projeto, podendo ser um controller REST API, uma FILA SQL, etc.

- **Interactors**: Camada de negócio da aplicação, que representa toda a logica do negócio, como validações, utilizando principalmente a camada de entities como seus objetos.

- **Entities**: Camada de dominio dos objetos, eles representam a camada de dominio e contem os objetos que representam os conceitos principais para o negócio.

- **Repositories**: Camada de acesso a recursos externos da aplicação, nessa camada ainda não é definida a tecnologia dos recursos externos, sendo apenas uma porta de acesso para serviços fora da aplicação.

- **Data Sources**: Camada que representa os recursos externos que são acessiveis pela camada de Repositories da aplicação, podendo ser um banco de dados, um serviço externo que responde por REST API, uma fila, etc.


## Arquitetura dos componentes

A seguir está detalhado os componentes que irão compor o estudo

![Arquitetura dos componentes](./imgs/CRUD-&-CDC.drawio.svg)


### service--userdata
Serviço é responsavel por todas as alterações de dados de usuário realizadas no banco de dados. O serviço irá responder via REST API.

### userdata
Banco de dados que terá armazenados os dados do usuário. O banco será configurado para utilizar o Desing Pattern CDC, assim todas as alterações realizadas no banco serão armazenadas em uma tabela SQL separa.

### Kafka Source Connector
Conector Kafka Microsoft SQL Server CDC Source Connector para integrar com o banco de dados, sendo responsavel por produzir as mensagens de atualização de dados cadastrais para um tópico Kafka.

### cdc.info-user
Tópico Kafka responsavel por receber as atualizações de dados cadastrais, essa informação será consumida pelo serviço de push por email. O objetivo do tópico é informar a todas as partes interessadas sobre a atualização de dados cadastrais.


### service--push-email
Serviço responsavel por realizar o envio de notificações para usuários via email. O serviço irá consumir o tópico kafka cdc.inf-user para que seja enviado sobre a atualização de dados cadastrais para o usuário


## Referencias

- [Hexagonal Architeture by netflixtechblog](https://netflixtechblog.com/ready-for-changes-with-hexagonal-architecture-b315ec967749)
- [C4 Model](https://c4model.com/)





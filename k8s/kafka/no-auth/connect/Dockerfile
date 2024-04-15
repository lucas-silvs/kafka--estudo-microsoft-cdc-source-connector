FROM confluentinc/cp-server-connect:7.6.1
USER root
RUN  confluent-hub install --no-prompt confluentinc/kafka-connect-datagen:0.3.3
RUN  confluent-hub install --no-prompt debezium/debezium-connector-sqlserver:2.4.2
RUN confluent-hub install --no-prompt debezium/debezium-connector-mysql:latest

USER 1001
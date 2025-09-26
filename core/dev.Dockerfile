FROM maven:3.9.5-eclipse-temurin-21

ENV LANG=C.UTF-8

# Criação de diretórios separados para cache e projeto
RUN mkdir -p /home/.m2 \
    && mkdir -p /home/app-pg \
    && chown -R 1000:1000 /home

WORKDIR /home/app-pg

USER 1000

# Executa o Quarkus em modo dev com porta e home do usuário separados
CMD [ "mvn", "-Duser.home=/home", "quarkus:dev", "-Ddebug", "-DdebugHost=0.0.0.0", "-Dquarkus.http.port=3001" ]

# build stage
FROM openjdk:11 as masther-thesis-backend-build
WORKDIR application

# SSL
ARG SSL_PASSWORD=secret

COPY ./pom.xml ./pom.xml
COPY mvnw .
COPY .mvn .mvn
COPY ./src ./src
RUN ["chmod", "+x", "mvnw"]

#SSL second part
RUN keytool -genkeypair -keyalg RSA -keysize 2048 -validity 365 \
    -dname "CN=PKocan, OU=WEiI, O=PRz, L=Rzeszow, ST=podkarpackie, C=PL" -alias stock-exchange -storetype PKCS12 \
    -keystore ./src/main/resources/stock-exchange.p12 -keypass $SSL_PASSWORD -storepass $SSL_PASSWORD

RUN ./mvnw clean package && cp target/master-thesis-backend.jar master-thesis-backend.jar

# production stage
FROM openjdk:11
RUN groupadd -r spring && useradd -r -g spring spring
USER spring:spring
WORKDIR application
COPY --from=masther-thesis-backend-build  application/master-thesis-backend.jar master-thesis-backend.jar
ENTRYPOINT ["java", "-jar", "master-thesis-backend.jar"]

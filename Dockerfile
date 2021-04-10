# build stage
FROM openjdk:14 as master-thesis-build
WORKDIR application
ARG SSL_PASSWORD=secret
COPY ./pom.xml ./pom.xml
COPY mvnw .
COPY .mvn .mvn
COPY ./src ./src
RUN ["chmod", "+x", "mvnw"]
RUN keytool -genkeypair -keyalg RSA -keysize 2048 -validity 365 \
    -dname "CN=PKocan, OU=WEiI, O=PRz, L=Rzeszow, ST=podkarpackie, C=PL" -alias master-thesis -storetype PKCS12 \
    -keystore ./src/main/resources/master-thesis.p12 -keypass $SSL_PASSWORD -storepass $SSL_PASSWORD
RUN ./mvnw clean package && cp target/master-thesis-backend.jar master-thesis-backend.jar

# production stage
FROM openjdk:14
RUN groupadd -r spring && useradd -r -g spring spring
USER spring:spring
WORKDIR application
COPY --from=master-thesis-build  application/master-thesis-backend.jar master-thesis-backend.jar
ENTRYPOINT ["java", "-jar", "master-thesis-backend.jar"]

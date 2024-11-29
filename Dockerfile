FROM alpine:latest as package
WORKDIR /base
RUN apk add --no-cache curl
RUN curl -o allure-2.19.0.tgz -L https://github.com/allure-framework/allure2/releases/download/2.19.0/allure-2.19.0.tgz && \
    tar -xvzf allure-2.19.0.tgz 

FROM openjdk:17-alpine
RUN apk add maven
RUN apk add android-tools 
COPY --from=package /base/allure-2.19.0/bin/allure /usr/local/bin/
COPY --from=package /base/allure-2.19.0/. /usr/local/
COPY pom.xml pom.xml
RUN mvn dependency:resolve
COPY . .
CMD ["mvn", "test"]
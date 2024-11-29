FROM alpine:latest as package
WORKDIR /base
RUN apk add --no-cache curl
RUN curl -o allure-2.19.0.tgz -L https://github.com/allure-framework/allure2/releases/download/2.19.0/allure-2.19.0.tgz && \
    tar -xvzf allure-2.19.0.tgz 

FROM openjdk:17-alpine
ENV BROWSERSTACK_USERNAME=nguynvnhng_qC7nHm \
    BROWSERSTACK_ACCESS_KEY=sqVMLFahABTkM4ViEsRb \
    BROWSERSTACK_APP=bs://1b8a4f08b2f81452d3f5407585fcfdc64985cd64
RUN apk add maven
RUN apk add android-tools 
COPY --from=package /base/allure-2.19.0/bin/allure /usr/local/bin/
COPY --from=package /base/allure-2.19.0/. /usr/local/
COPY pom.xml pom.xml
RUN mvn dependency:resolve
COPY . .
CMD mvn test
#CMD ["mvn", "test"]

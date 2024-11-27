FROM maven:3.8.3-openjdk-17
COPY pom.xml pom.xml
RUN mvn dependency:resolve
COPY . .
CMD ["mvn", "test"]
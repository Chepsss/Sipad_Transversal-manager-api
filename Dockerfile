FROM openjdk:11
COPY /security-manager-rest/target/security-manager-rest-0.0.1.jar /usr/local/lib/security-manager-api.jar

EXPOSE 8085

ENTRYPOINT ["java","-jar","/usr/local/lib/security-manager-api.jar"]
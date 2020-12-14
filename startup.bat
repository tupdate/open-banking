java -jar eureka-naming-server/target/eureka-naming-server-0.0.1-SNAPSHOT.jar
sleep 20
java -jar api-gateway/target/api-gateway-0.0.1-SNAPSHOT.jar
sleep 20
java -jar thirdparty-org-service/target/thirdparty-org-service-0.0.1-SNAPSHOT.jar
sleep 5
java -jar account-open-service/target/account-open-service-0.0.1-SNAPSHOT.jar
sleep 10
java -jar account-balance-service/target/account-balance-service-0.0.1-SNAPSHOT.jar
sleep 5
java -jar thirdparty-org-service/target/thirdparty-org-service-0.0.1-SNAPSHOT.jar





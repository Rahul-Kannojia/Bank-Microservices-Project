#! /bin/bash

cd F:\\Workspaces\\springboot_java_express_microservices\\eurekaserver
mvn compile jib:dockerBuild

cd F:\\Workspaces\\springboot_java_express_microservices\\accounts
mvn compile jib:dockerBuild

cd F:\\Workspaces\\springboot_java_express_microservices\\cards
mvn compile jib:dockerBuild

cd F:\\Workspaces\\springboot_java_express_microservices\\loans
mvn compile jib:dockerBuild

cd F:\\Workspaces\\springboot_java_express_microservices\\gatewayserver
mvn compile jib:dockerBuild

docker push rahulkannojia/accounts:0.0.1
docker push rahulkannojia/loans:0.0.1
docker push rahulkannojia/cards:0.0.1
docker push rahulkannojia/eurekaserver:0.0.1
docker push rahulkannojia/gatewayserver:0.0.1
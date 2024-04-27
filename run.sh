#!/bin/bash
export ENVIRONMENT=production

mvn clean install

nohup mvn -f chomik-gateway/pom.xml spring-boot:run &
nohup mvn -f chomik-storage/pom.xml spring-boot:run &
nohup mvn -f chomik-orders/pom.xml spring-boot:run &
nohup mvn -f chomik-payment/pom.xml spring-boot:run &
nohup mvn -f chomik-delivery/pom.xml spring-boot:run &

nohup mvn -f delivery-mock/pom.xml spring-boot:run &
nohup mvn -f payment-mock/pom.xml spring-boot:run &

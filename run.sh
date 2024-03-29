#!/bin/bash
mvn clean install

mvn -f chomik-gateway/pom.xml spring-boot:run > /dev/null 2>&1 &
mvn -f chomik-orders/pom.xml spring-boot:run > /dev/null 2>&1 &
mvn -f chomik-storage/pom.xml spring-boot:run > /dev/null 2>&1 &



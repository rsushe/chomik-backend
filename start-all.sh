#!/bin/bash

# List of modules
modules=(
  "chomik-gateway"
  "chomik-orders"
  "chomik-storage"
  "chomik-payment"
  "chomik-delivery"
  "delivery-mock"
  "payment-mock"
)

# Loop through each module and run the corresponding JAR file
for module in "${modules[@]}"
do
  jar_file="./${module}/target/${module}.jar"
  if [ -f "$jar_file" ]; then
    java -jar "$jar_file" &
  else
    echo "JAR file not found for module: $module"
  fi
done
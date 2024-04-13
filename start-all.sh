#!/bin/bash

# List of modules with artifactId and version
modules=(
  "chomik-gateway:0.0.1-SNAPSHOT"
  "chomik-orders:0.0.1-SNAPSHOT"
  "chomik-storage:0.0.1-SNAPSHOT"
  "chomik-payment:0.0.1-SNAPSHOT"
  "chomik-delivery:0.0.1-SNAPSHOT"
  "delivery-mock:0.0.1-SNAPSHOT"
  "payment-mock:0.0.1-SNAPSHOT"
)

# Loop through each module and run the corresponding JAR file
for module in "${modules[@]}"
do
  artifact_id=$(echo "$module" | cut -d':' -f1)
  version=$(echo "$module" | cut -d':' -f2)
  jar_file="./${artifact_id}/target/${artifact_id}-${version}.jar"
  if [ -f "$jar_file" ]; then
    echo "Running JAR file for module: $artifact_id"
    java -jar "$jar_file" &
  else
    echo "JAR file not found for module: $artifact_id"
  fi
done
#!/bin/sh

dir="./src/main/java/project_csc309_spring_2024"

for file in "$dir"/*.java; 
do
    filename=$(basename -- "$file")
    echo "\nClass Name: $filename"
    java Metrics.java "$file"
done

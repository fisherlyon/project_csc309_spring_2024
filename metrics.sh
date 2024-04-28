#!/bin/bash

dir="math_tutor_project/src/main/java/math_tutor_project"

for file in "$dir"/*.java; do
    filename=$(basename -- "$file")
    echo -e "\nClass Name: $filename"
    java Metrics.java "$file"
done

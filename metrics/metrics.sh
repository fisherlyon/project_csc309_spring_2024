#!/bin/bash

dir1="../src/main/java/project_csc309_spring_2024"
dir2="../src/main/java/project_csc309_spring_2024/multiplayer"

for file in "$dir1"/*.java; 
do
    filename=$(basename -- "$file")
    printf "\nClass Name: $filename\n"
    java Metrics.java "$file"
done

for file in "$dir2"/*.java;
do
    filename=$(basename -- "$file")
    printf "\nClassName: $filename\n"
    java Metrics.java "$file"
done

#!/usr/bin/env sh

set -e
shopt -s extglob globstar

# javac -sourcepath src -d classes src/**/*.java
javac -sourcepath src -d classes src/calc/Calc.java

# jar cvfm GraphTest01.jar MANIFEST.MF ./**/*.class

# java -jar GraphTest01.jar

java -classpath classes calc/Calc

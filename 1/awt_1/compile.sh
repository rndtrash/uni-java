#!/usr/bin/env sh

set -e
shopt -s extglob globstar

javac ./**/*.java

jar cvfm SimpleFrame.jar MANIFEST.MF ./**/*.class

# java -classpath . JavaApplication1
java -jar SimpleFrame.jar

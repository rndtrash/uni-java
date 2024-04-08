#!/usr/bin/env sh

set -e
shopt -s extglob globstar

javac ./**/*.java

jar cvfm HelloWorldFrame.jar MANIFEST.MF ./**/*.class

java -jar HelloWorldFrame.jar

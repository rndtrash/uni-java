#!/usr/bin/env sh

set -e
shopt -s extglob globstar

javac ./**/*.java

jar cvfm GraphTest01.jar MANIFEST.MF ./**/*.class

java -jar GraphTest01.jar

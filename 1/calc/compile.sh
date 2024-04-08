#!/usr/bin/env sh

javac JavaApplication1.java

jar cvfm HelloWorld.jar MANIFEST.MF *.class

# java -classpath . JavaApplication1
java -jar HelloWorld.jar

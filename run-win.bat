dir /s /B *.java | xargs javac -cp ./lib/* -d ./bin
java -cp ./bin;./lib/* com.sergihen7.Main

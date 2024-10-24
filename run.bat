find ./src -name "*.java" | xargs javac -cp ./lib/* -d ./class
java -cp ./class:./lib/* com.sergihen7.Main

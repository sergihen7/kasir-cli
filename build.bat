find ./src -name "*.java" | xargs javac -cp ./lib/* -d ./bin
jar cfm ./build/kasir-cli.jar manifest.txt -C class .
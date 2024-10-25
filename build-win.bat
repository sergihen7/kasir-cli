dir /s /B *.java | xargs javac -cp ./lib/* -d ./bin
jar cfm ./build/kasir-cli.jar MANIFEST.MF -C bin .
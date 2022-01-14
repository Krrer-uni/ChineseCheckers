# ChineseCheckers

This repo is a workplace for developement of ChineseCheckers, classic game with option to play as 2,3,4 and 6 players. Game is going to have Client-Server architecture.

Game consist of two executables, ServerMain and ClientMain. To start a game you have to run ServerMain and provide number of players that will join the game. Next you have to open that many ClientMain's for the game to start. Game will start. After the game finishes you need to close all applications and run them again to play the next game.

1. first you need to pull this repo using 

git pull https://github.com/Krrer-uni/ChineseCheckers.git

2. Compile the project using 
mvn package

3. Run the server:
java -cp .\target\ChineseCheckers-1.0-SNAPSHOT.jar Server.ServerMain

4. Run the client:
java -cp .\target\ChineseCheckers-1.0-SNAPSHOT.jar Client.ClientMain


created by Marek Świergoń and Wojtek Rymer

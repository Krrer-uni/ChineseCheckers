# ChineseCheckers

This repo is a workplace for developement of ChineseCheckers, classic game with option to play as 2,3,4 and 6 players. Game is going to have Client-Server architecture.

Game consist of two executables, ServerMain and ClientMain. To start a game you have to run ServerMain and provide number of players that will join the game. Next you have to open that many ClientMain's for the game to start. Game will start. After the game finishes you need to close all applications and run them again to play the next game.

<b>Prerequisites:</b>

- Java version 13 or newer
- Maven 3.3 or newer

1. First you need to pull this repo:
<i>git pull https://github.com/Krrer-uni/ChineseCheckers.git</i>

2. Compile the project using 
<i>mvn package</i>

<b> For Windows: </b>

3. Run the server:
<i>java -cp .\target\ChineseCheckers-1.0-SNAPSHOT.jar Server.ServerMain</i>

4. Run the client:
<i>java -cp .\target\ChineseCheckers-1.0-SNAPSHOT.jar Client.ClientMain</i>

<b> For Linux: </b>

3. Run the server:
<i>java -cp ./target/ChineseCheckers-1.0-SNAPSHOT.jar Server.ServerMain</i>

4. Run the client:
<i>java -cp ./target/ChineseCheckers-1.0-SNAPSHOT.jar Client.ClientMain</i>


created by Marek Świergoń and Wojtek Rymer

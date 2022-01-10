package Client;

public interface GameServerMediator {

     void updateBoard(int rowSource, int columnSource, int rowTarget, int columnTarget);

     void sendMove(int rowSource, int columnSource, int rowTarget, int columnTarget);

     void gameInit(int playerCount, int playerId);

     void setCurrentPlayer(int startingPlyer);

     void sendSkip();

     void gameFinished(int place);
}
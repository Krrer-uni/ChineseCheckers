package Client;

public class ChineseCheckerMediator  implements  GameServerMediator{

	private Client2ServerConnection server;
	private GamePanel gamePanel;
	
	public ChineseCheckerMediator(Client2ServerConnection server, GamePanel gamePanel) {
		this.server=server;
		this.gamePanel=gamePanel;
	}
	
	public void updateBoard(int rowSource, int columnSource, int rowTarget, int columnTarget) {
		gamePanel.updateBoard(rowSource, columnSource, rowTarget, columnTarget);
	}
	
	public void sendMove(int rowSource, int columnSource, int rowTarget, int columnTarget) {
		server.sendMove(rowSource, columnSource, rowTarget, columnTarget);
	}

	public void gameInit(int playerCount,int playerId){
		gamePanel.setBoardBuilderLayout(playerCount);
		gamePanel.setPlayerId(playerId);
	}

	public void setCurrentPlayer(int startingPlyer){
		gamePanel.setCurrentPlayer(startingPlyer);
	}

	public void sendSkip(){
		server.sendSkip();
	}
	public void gameFinished(int place){
		gamePanel.gameFinished(place);
	}
}

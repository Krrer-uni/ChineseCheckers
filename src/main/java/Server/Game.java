package Server;

import java.util.ArrayList;
import java.util.Random;

public class Game {
	private GameRules gameRules;
	private int numberPlayers;
	private ArrayList<Player> players;
	private int numberPlayersFinished;
	
	public Game(GameRules gameRules) {
		this.gameRules = gameRules;
		players = new ArrayList<Player>();
		this.numberPlayersFinished = 0;
	}
	
	public void setGameRules(GameRules gameRules) {
		this.gameRules=gameRules;
	}
	
	public GameRules getGameRules() {
		return gameRules;
	}
	
	public void setPlayers(int pl) throws WrongPlayerNumber{
		if (gameRules.isPlayerNumberGood(pl)) {
			this.numberPlayers=pl;
		}
		else throw new WrongPlayerNumber();
	}
	
	public int getNumberPlayers() {
		return numberPlayers;
	}
	
	public void addPlayer(Player p) {
		players.add(p);
	}
	
	public ArrayList<Player> getPlayerList() {
		return players;
	}
	
	
	public int getNumberPlayersFinished() {
		return numberPlayersFinished;
	}
	
	public void start() {
		Random random = new Random();
		int playerToStart = random.nextInt(numberPlayers) + 1;
		players.get(playerToStart-1).goNextState();
	}
	
	public boolean move (int x1, int y1, int x2, int y2, int playerId) {
		if(players.get(playerId-1).getState() instanceof PlayerStateMove) {
			if(gameRules.isMoveGood(x1, y1, x2, y2, playerId, numberPlayers))
				return true;
		}
		return false;
	}
	
	public boolean hasPlayerEnded(int playerId) {
		if(gameRules.hasEnded(numberPlayers, playerId)) {
			numberPlayersFinished++;
			return true;
		}
		return false;			
	}
	
	public void setWin(int playerId) {
		players.get(playerId-1).goWinState();
	}
	
	public void nextToMove(int previousId) {
		players.get(previousId-1).goNextState();
		int i = (previousId + 1) % numberPlayers;
		if (i == 0) i = numberPlayers;
		while (i != previousId) {
			if(players.get(i-1).getState() instanceof PlayerStateWait) {
				if(numberPlayers - numberPlayersFinished == 1) {
					players.get(i-1).goWinState();
					players.get(i-1).sendEndInfo(++numberPlayersFinished);
					System.out.println("THE END OF GAME");
				}
				players.get(i-1).goNextState();
				break;
			}
			i = (i + 1) % numberPlayers;
			if (i == 0) {
				i = numberPlayers;
			}
		}	
	}
	
	class WrongPlayerNumber extends Exception {

		private static final long serialVersionUID = 1L;
	}

}

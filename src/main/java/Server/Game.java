package Server;

import java.util.ArrayList;
import java.util.Random;

public class Game {
	GameRules gameRules;
	private int numberPlayers;
	ArrayList<Player> players;
	private int playerToMove;
	
	public Game (GameRules gameRules) {
		this.gameRules = gameRules;
		players = new ArrayList<Player>();
	}
	
	public void setGameRules (GameRules gameRules) {
		this.gameRules=gameRules;
	}
	
	public void setPlayers (int pl) throws WrongPlayerNumber{
		if (gameRules.isPlayerNumberGood(pl)) {
			this.numberPlayers=pl;
		}
		else throw new WrongPlayerNumber();
	}
	
	public void addPlayer(Player p) {
		players.add(p);
	}
	
	public ArrayList<Player> getPlayerList() {
		return players;
	}
	
	public int getPlayerToMove() {
		return playerToMove;
	}
	
	public void start() {
		Random random = new Random();
		this.playerToMove = random.nextInt(numberPlayers) + 1;
		players.get(playerToMove-1).goNextState();
	}
	
	public boolean move (int x1, int y1, int x2, int y2, int playerId) {
		if(playerId == playerToMove) {
			if(gameRules.isMoveGood(x1, y1, x2, y2))
				return true;
		}
		return false;
	}
	
	public void nextToMove(int previousId) {
		players.get(previousId-1).goNextState();
		int i = (previousId + 1) % numberPlayers;
		if (i == 0) i = numberPlayers;
		while (i != previousId) {
			if(players.get(i-1).getState() instanceof PlayerStateWait) {
				System.out.println("zmieniam stan");
				players.get(i-1).goNextState();
				playerToMove = i;
				break;
			}
		}
		//jak tu wyjdzie to nie ma z kim grać :(
	}
	
	class WrongPlayerNumber extends Exception {

		private static final long serialVersionUID = 1L;
	}


	public int getNumberPlayers() {
		return numberPlayers;
	}

}

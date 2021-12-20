package Server;

import java.util.ArrayList;

public class Game {
	GameRules gameRules;
	private int numberPlayers;
	ArrayList<Player> players;
	
	
	public Game () {
		players = new ArrayList<>();
	}
	
	public Game (GameRules gameRules) {
		this.gameRules = gameRules;
		players = new ArrayList<>();
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
	
	public boolean move (int x1, int y1, int x2, int y2, Player currPlayer) {
		if(gameRules.isMoveGood(x1, y1, x2, y2)) {
			//zmiana stanu
			return true;
		}
		return false;
	}
	class WrongPlayerNumber extends Exception {

		private static final long serialVersionUID = 1L;
	}


	public int getNumberPlayers() {
		return numberPlayers;
	}

}

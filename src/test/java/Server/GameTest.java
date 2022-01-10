package Server;

import static org.junit.Assert.assertEquals;

import java.net.Socket;

import org.junit.Test;

import Server.Game.WrongPlayerNumber;

public class GameTest {

	@Test
	public void canOnlyOnePlayerMoveAfterStartingGame() {
		Game game = new Game(new ChineseCheckersRules(6));
		try {
			game.setPlayers(6);
			//let's try to switch player number to wrong
			game.setPlayers(5);
		} catch (WrongPlayerNumber e) {
			System.out.println("Wrong player number");
		}
		assertEquals(game.getNumberPlayers(), 6);
		for(int i = 1; i < 7; i++) {
			Player p = new Player(new Socket(), i, game);
			game.addPlayer(p);
		}
		assertEquals(6, game.getPlayerList().size());
		
	}
	
}

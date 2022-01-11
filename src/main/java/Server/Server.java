package Server;

import java.net.ServerSocket;
import java.util.concurrent.Executors;

import Server.Game.WrongPlayerNumber;

/**
 * Class representing a server to get running 
 * and connect all the clients to.
 * @author swmar
 *
 */
public class Server {
	/**
	 * number of players that will play the game
	 */
	private transient int numberOfPlayers;
	
	/**
	 * @param numberOfPlayers number of players that will play the game
	 */
	public Server(final int numberOfPlayers) {
		this.numberOfPlayers = numberOfPlayers;
	}
	
	/**
	 * Method to run the server and establish connection with clients.
	 * @throws Exception thrown when there were issues with establishing connection
	 */
	public void run() throws Exception {
		try (var listener = new ServerSocket(21371)) {
            System.out.println("Server is Running...");
            final var pool = Executors.newFixedThreadPool(10);
            final GameRulesFactory factory = new GameRulesFactory();
            final Game game = new Game(factory.getGameRules("Chinese Checkers", numberOfPlayers));
            try {
            	game.setPlayers(numberOfPlayers);
            	int i = 1;
            	while(i<=numberOfPlayers) {
            		final Player p = new Player(listener.accept(), i, game);
            		game.addPlayer(p);
            		System.out.println("Dodano " + i + "gracza");
            		i++;
            		pool.execute(p);
            	}
            }
            catch (WrongPlayerNumber ex){
            	System.out.println("Wrong number of players!");
            }
        }
	}
}

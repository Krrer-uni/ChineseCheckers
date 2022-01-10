package Server;

import java.net.ServerSocket;
import java.util.concurrent.Executors;

import Server.Game.WrongPlayerNumber;

public class Server {
	private int numberOfPlayers;
	
	public Server(int numberOfPlayers) {
		this.numberOfPlayers = numberOfPlayers;
	}
	
	public void run() throws Exception {
		try (var listener = new ServerSocket(21371)) {
            System.out.println("Server is Running...");
            var pool = Executors.newFixedThreadPool(10);
            GameRulesFactory factory = new GameRulesFactory();
            Game game = new Game(factory.getGameRules("Chinese Checkers", numberOfPlayers));
            try {
            	game.setPlayers(numberOfPlayers);
            	int i = 1;
            	while(i<=numberOfPlayers) {
            		Player p = new Player(listener.accept(), i, game);
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

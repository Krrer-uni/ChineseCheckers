package Server;

import java.net.ServerSocket;
import java.util.Scanner;
import java.util.concurrent.Executors;
import Server.Game.WrongPlayerNumber;

public class ServerMain {

	public static void main(String[] args) throws Exception {
        try (var listener = new ServerSocket(21371)) {
            System.out.println("Server is Running...");
            var pool = Executors.newFixedThreadPool(200);
            Scanner in = new Scanner(System.in);
            Game game = new Game(new AllAllowedChineseCheckersRules());
            System.out.println("Type in number of players: ");
            int number = in.nextInt();
            try {
            	game.setPlayers(number);
            	int i = 1;
            	while(i<=number) {
            		Player p = new Player(listener.accept(), i, game);
            		i++;
            		if(p==null)
            			System.out.println("xd");
            		game.addPlayer(p);
            		System.out.println("Dodano " + i + "gracza");
            		pool.execute(p);
            	}
            }
            catch (WrongPlayerNumber ex){
            	System.out.println("Wrong number of players!");
            }
            finally {
            	in.close();
            }
        }
    }
}

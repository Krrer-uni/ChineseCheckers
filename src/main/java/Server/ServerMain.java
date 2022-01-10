package Server;

import java.util.Scanner;

public class ServerMain {

	public static void main(String[] args) throws Exception {
		Scanner in = new Scanner(System.in);
        System.out.println("Type in number of players: ");
        int number = in.nextInt();
        Server server= new Server(number);
        server.run();
        in.close();
    }
}

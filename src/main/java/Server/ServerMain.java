package Server;

import java.util.Scanner;

/**
 * Main class to run the server
 * @author Marek Świergoń
 *
 */
public class ServerMain {

	/**
	 * Main function to get number of players and get the server running.
	 * @throws Exception thrown when connection failed to establish
	 */
	public static void main(final String[] args) throws Exception {
		final Scanner in = new Scanner(System.in);
        System.out.println("Type in number of players: ");
        final int number = in.nextInt();
        in.close();
        final Server server= new Server(number);
        server.run();
    }
}

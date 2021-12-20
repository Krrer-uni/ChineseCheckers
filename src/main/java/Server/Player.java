package Server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Player implements Runnable {

	Game game;
	int playerId;
	Socket socket;
	Scanner input;
	PrintWriter output;
	//PlayerState state;
	
	public Player(Socket socket, int playerId, Game game) {
        this.socket = socket;
        this.playerId = playerId;
        this.game = game;
    }
	
	@Override
	public void run() {
		 try {
             setup();
             processCommands();
         } catch (Exception e) {
             e.printStackTrace();
         } finally {
             try {
                 socket.close();
             } catch (IOException e) {
             }
         }
	}

    private void setup() throws IOException {
        input = new Scanner(socket.getInputStream());
        output = new PrintWriter(socket.getOutputStream(), true);
        output.println("WELCOME " + playerId);
    }

    private void processCommands() {
        while (input.hasNextLine()) {
            String command = input.nextLine();
            String [] commands = command.split(" ");
            if (commands[0].equals("QUIT")) {
                return;
            } else if (commands[0].equals("MOVE")) {
                processMoveCommand(Integer.parseInt(commands[1]), Integer.parseInt(commands[2]), Integer.parseInt(commands[3]), Integer.parseInt(commands[4]));
            }
        }
    }

    private void processMoveCommand(int x1, int y1, int x2, int y2) {
        try {
        	
            if (game.move(x1, y1, x2, y2, this)) {
            	output.println("VALID_MOVE");
            }	
            for(Player opponent : game.getPlayerList()) {
            	opponent.output.println("OPPONENT_MOVED " + x1 + " " + y1 + " " + x2 + " " + y2);
            }
            /*
            if (hasWinner()) {
                output.println("VICTORY");
                opponent.output.println("DEFEAT");
            } 
            */
        } catch (IllegalStateException e) {
            output.println("MESSAGE " + e.getMessage());
        }
    }
}
	

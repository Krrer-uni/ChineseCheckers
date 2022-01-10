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
	PlayerState state;
	
	public Player(Socket socket, int playerId, Game game) {
        this.socket = socket;
        this.playerId = playerId;
        this.game = game;
        this.state = new PlayerStateWait();
    }
	
	public PlayerState getState() {
		return state;
	}
	
	public void goNextState() {
		state = state.nextState();
		if(state instanceof PlayerStateMove) {
			for(Player player : game.getPlayerList())
				player.output.println("NEXT " + playerId);
		}
	}
	
	public void goWinState() {
		state = state.winState();
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
        output.println("WELCOME " + playerId + " " + game.getNumberPlayers());
        if(playerId == game.getNumberPlayers()) game.start();
    }

    private void processCommands() {
        while (input.hasNextLine()) {
            String command = input.nextLine();
            System.out.println(command);
            String [] commands = command.split(" ");
            if (commands[0].equals("QUIT")) {
                return;
            } else if (commands[0].equals("MOVE")) {
                processMoveCommand(Integer.parseInt(commands[1]), Integer.parseInt(commands[2]), Integer.parseInt(commands[3]), Integer.parseInt(commands[4]));
            } else if (commands[0].equals("SKIP")) {
            	game.nextToMove(playerId);
            }
        }
    }

    private void processMoveCommand(int x1, int y1, int x2, int y2) {
        try {
        	
            if (game.move(x1, y1, x2, y2, playerId)) {
            	for(Player player : game.getPlayerList()) {
                	player.output.println("PLAYER " + playerId + " MOVED " + x1 + " " + y1 + " " + x2 + " " + y2);
                	
                }
            	if (game.hasPlayerEnded(playerId)) {
                    output.println("FINISH " + game.getNumberPlayersFinished());
                    game.setWin(playerId);
                }
            	game.nextToMove(playerId);
            }	
        } catch (IllegalStateException e) {
            output.println("MESSAGE " + e.getMessage());
        }
    }
}
	

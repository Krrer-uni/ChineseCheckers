package Server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * Class that is used to represent a player and listen messages from one client.
 * It implements Runnable class.
 * @see java.lang.Runnable
 * @author swmar
 *
 */
public class Player implements Runnable {
	/**
	 * an instance of Game class - a game that the player is playing
	 */
	private transient final Game game;
	
	/**
	 * id of a player assigned during connection
	 */
	private transient final int playerId;
	
	/**
	 * socket used to establish connection with a client
	 * @see java.net.Socket
	 */
	private transient final Socket socket;
	
	/**
	 * scanner used to read messages sent by a client
	 */
	private transient Scanner input ;
	
	/**
	 * PrintWriter to send messages to a client
	 */
	private transient PrintWriter output;
	
	/**
	 * State of this player, used to see if a player can move
	 * or has already ended the game 
	 * @see PlayerState
	 */
	private transient PlayerState state;
	
	/**
	 * Constructor of a Player class
	 * @param socket 
	 * @param playerId
	 * @param game
	 */
	public Player(Socket socket, int playerId, Game game) {
        this.socket = socket;
        this.playerId = playerId;
        this.game = game;
        this.state = new PlayerStateWait();
    }
	
	/**
	 * @return PlayerState instance of a player
	 */
	public PlayerState getState() {
		return state;
	}
	
	/**
	 * Method to switch player to another state 
	 * (from move to wait or other way). If a state is being switch to Move
	 * state, all playing players get information 
	 * that this player is going to move now.
	 */
	public void goNextState() {
		state = state.nextState();
		if(state instanceof PlayerStateMove) {
			for(final Player player : game.getPlayerList()) {
				if(!(player.getState() instanceof PlayerStateWin)) {
					player.output.println("NEXT " + playerId);
				}
			}
		}
	}
	
	/**
	 * Method to set state to a WinState (and end the game for the player)
	 */
	public void goWinState() {
		state = state.winState();
	}
	
	/**
	 * Method used when server is working, to listen from a client
	 */
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
	
	/**
	 * Method to set the listening up.
	 * @throws IOException
	 */
    private void setup() throws IOException {
        input = new Scanner(socket.getInputStream());
        output = new PrintWriter(socket.getOutputStream(), true);
        output.println("WELCOME " + playerId + " " + game.getNumberPlayers());
        if(playerId == game.getNumberPlayers()) {
        	game.start();
        }
    }

    /**
     * Method to interpret commands sent by a client.
     */
    private void processCommands() {
        while (input.hasNextLine()) {
            final String command = input.nextLine();
            System.out.println(command);
            final String [] commands = command.split(" ");
            if (commands[0].equals("QUIT")) {
                return;
            } else if (commands[0].equals("MOVE")) {
                processMoveCommand(Integer.parseInt(commands[1]), Integer.parseInt(commands[2]), Integer.parseInt(commands[3]), Integer.parseInt(commands[4]));
            } else if (commands[0].equals("SKIP")) {
            	game.nextToMove(playerId);
            }
        }
    }

    /**
     * Method to execute pawn move command, sent by a client.
     * If the move is good, it sends to all players information 
     * that this player has moved.
     * @param x1 coordinate x of a starting point of the move
     * @param y1 coordinate y of a starting point of the move
     * @param x2 coordinate x of a destination point of the move
     * @param y2 coordinate y of a destination point of the move
     */
    private void processMoveCommand(final int x1, final int y1, final int x2, final int y2) {
        try {
        	
            if (game.move(x1, y1, x2, y2, playerId)) {
            	for(final Player player : game.getPlayerList()) {
                	player.output.println("PLAYER " + playerId + " MOVED " + x1 + " " + y1 + " " + x2 + " " + y2);
                	
                }
            	if (game.hasPlayerEnded(playerId)) {
                    sendEndInfo(game.getNumberPlayersFinished());
                    game.setWin(playerId);
                }
            	game.nextToMove(playerId);
            }	
        } catch (IllegalStateException e) {
            output.println("MESSAGE " + e.getMessage());
        }
    }
    
    /**
     * Method to send information for a client that a game is over
     * @param place Place that this player took in a game
     */
    public void sendEndInfo(final int place) {
    	output.println("FINISHED " + place);
    }
}
	

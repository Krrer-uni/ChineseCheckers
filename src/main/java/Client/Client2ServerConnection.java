package Client;

import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * Client that connects to the server
 */
public class Client2ServerConnection {

    private final Socket socket;
    private final Scanner in;
    private final PrintWriter out;
    /**
     * Mediator that allows communication with game
     */
    private ChineseCheckerMediator mediator;

    public Client2ServerConnection(String serverAddress, int port) throws Exception {

        socket = new Socket(serverAddress, port);
        in = new Scanner(socket.getInputStream());
        out = new PrintWriter(socket.getOutputStream(), true);
    }

    /**
     * setter for mediator
     *
     * @param mediator mediator
     */
    public void setGameServerMediator(ChineseCheckerMediator mediator) {
        this.mediator = mediator;
    }

    public void listen() throws Exception {
        try {
            String response;
            while (in.hasNextLine()) {
                response = in.nextLine();
                System.out.println(response);
                String[] commands = response.split(" ");
                if (commands[0].equals("PLAYER") && commands[2].equals("MOVED")) {
                    //commands[1] - id gracza, który sie ruszyl
                    mediator.updateBoard(Integer.parseInt(commands[4]),
                            Integer.parseInt(commands[3]), Integer.parseInt(commands[6]),
                            Integer.parseInt(commands[5]));
                    //window.message("Opponent moved, your turn");
                } else if (response.startsWith("WELCOME")) {
                    mediator.gameInit(Integer.parseInt(commands[2]), Integer.parseInt(commands[1]));
                } else if (response.startsWith("NEXT")) {
                    mediator.setCurrentPlayer(Integer.parseInt(commands[1]));
                } else if (response.startsWith("FINISHED")) {
                    mediator.gameFinished(Integer.parseInt(commands[1]));
                }
            }
            out.println("QUIT");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            socket.close();
        }
    }

    /**
     * sending the move server
     *
     * @param rowSource    source row
     * @param columnSource source column
     * @param rowTarget    target row
     * @param columnTarget target column
     */
    public void sendMove(int rowSource, int columnSource, int rowTarget, int columnTarget) {
        out.println("MOVE " + rowSource + " " + columnSource + " " + rowTarget + " " + columnTarget);
        System.out.println("MOVE " + rowSource + " " + columnSource + " " + rowTarget + " " + columnTarget);
    }

    /**
     * sending skip message to the server
     */
    public void sendSkip() {
        out.println("SKIP");
        System.out.println("SKIP");
    }

    // KOMENDA
}

package Client;

import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client2ServerConnection {
	
	private Socket socket;
    private Scanner in;
    private PrintWriter out;
    //private MainWindow window;
    
    public Client2ServerConnection(String serverAddress/*, MainWindow window*/) throws Exception {

        socket = new Socket(serverAddress, 21371);
        in = new Scanner(socket.getInputStream());
        out = new PrintWriter(socket.getOutputStream(), true);
        //this.window = window;
    }
    
    public void listen() throws Exception {
    	try {
            String response = in.nextLine();
            while (in.hasNextLine()) {
                response = in.nextLine();
                String [] commands = response.split(" ");
                if (response.startsWith("VALID_MOVE")) {
                    //window.message("Valid move, please wait");
                } else if (response.startsWith("OPPONENT_MOVED")) {
                	
                    //window.move(Integer.parseInt(commands[1]), Integer.parseInt(commands[2]), Integer.parseInt(commands[3]), Integer.parseInt(commands[4]));
                    //window.message("Opponent moved, your turn");
                }
            }
            out.println("QUIT");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            socket.close();
        }
    }
    
    public void sendMove(int rowSource, int columnSource, int rowTarget, int columnTarget) {
    	out.println("MOVE " + rowSource + " " + columnSource + " " + rowTarget + " " + columnTarget);
    }
}

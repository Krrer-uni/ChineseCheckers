package Client;

public class ClientMain {
    public static void main(String[] args){
    	MainWindow window = new MainWindow();

        try {
			Client2ServerConnection connection = new Client2ServerConnection("localhost");
	        GameServerMediator mediator = new GameServerMediator(window, connection, window.getGamePanel());
	        connection.setGameServerMediator(mediator);
	        window.setGameServerMediator(mediator);
	        window.getGamePanel().setGameServerMediator(mediator);
			window.setVisible(true);
	        connection.listen();

		} catch (Exception e) {
			e.printStackTrace();
		}
    }
}

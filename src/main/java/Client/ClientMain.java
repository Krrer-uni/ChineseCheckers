package Client;

public class ClientMain {
    public static void main(String[] args){
    	MainWindow window = new MainWindow();
        window.setVisible(true);
        try {
			Client2ServerConnection connection = new Client2ServerConnection("localhost");
	        GameServerMediator mediator = new GameServerMediator(window, connection, window.getGamePanel());
	        connection.setGameServerMediator(mediator);
	        window.setGameServerMediator(mediator);
	        window.getGamePanel().setGameServerMediator(mediator);
	        connection.listen();

		} catch (Exception e) {
			e.printStackTrace();
		}
    }
}

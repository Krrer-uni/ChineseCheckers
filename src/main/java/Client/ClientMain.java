package Client;

public class ClientMain {
//	String address;
//	public ClientMain(String address){
//		this.address = address;
//	}

	public static void main(String[] args){
    	MainWindow window = new MainWindow();
    	while(true) {
			try {
				Client2ServerConnection connection = new Client2ServerConnection("localhost", 21371);
				ChineseCheckerMediator mediator = new ChineseCheckerMediator(connection, window.getGamePanel());
				connection.setGameServerMediator(mediator);
				window.getGamePanel().setGameServerMediator(mediator);
				window.setVisible(true);
				connection.listen();
			} catch (Exception e) {
//			e.printStackTrace();
				System.out.println(e.getMessage());
			}
		}
    }
}

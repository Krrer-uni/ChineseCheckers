package Client;

public class ClientMain {
    public static void main(String[] args){
    	//MainWindow window = new MainWindow();
        //window.setVisible(true);
        try {
			Client2ServerConnection connection = new Client2ServerConnection("localhost");
	        connection.listen();
		} catch (Exception e) {
			e.printStackTrace();
		}

    }
}

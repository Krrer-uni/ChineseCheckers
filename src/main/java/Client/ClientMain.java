package Client;

public class ClientMain {
    public static void main(String[] args){
        System.out.println("test zaliczony");
        try {
			Client2ServerConnection connection = new Client2ServerConnection("localhost");
	        connection.listen();
		} catch (Exception e) {
			e.printStackTrace();
		}

    }
}

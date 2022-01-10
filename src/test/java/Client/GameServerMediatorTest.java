package Client;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.net.Socket;

import org.junit.Before;
import org.junit.Test;

import Server.ChineseCheckersRules;
import Server.Game;
import Server.Player;
import Server.Server;

public class GameServerMediatorTest {
	
	@Before
	public void cos() {
		
	}
	
	@Test
	public void test() {
		ServerTests serverTest;
		try {
			serverTest = new ServerTests();
			serverTest.run();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
        System.out.println("kał");
	}
	
	
	class ServerTests implements Runnable {
		private Server server;

	    public ServerTests() throws IOException {
	        // listen on any free port
	        server = new Server(2);
	    }
		public void run() {
	        try {
				server.run();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
}

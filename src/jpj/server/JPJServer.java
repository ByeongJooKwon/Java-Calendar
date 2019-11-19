package jpj.server;

import		java.net.*;
import		java.util.*;
import 		jpj.server.dao.*;

public class JPJServer {
	ServerSocket	server;
	HashSet			clientList;
	JPJDao			dao;
	
	public JPJServer() {
		try {
			server = new ServerSocket(5555);
			clientList = new HashSet();
			dao = new JPJDao();
		}
		catch(Exception e) {
			System.out.println(e);
			System.exit(0);
		}
		while(true) {
			try {
				Socket	socket = server.accept();
				JPJClientThread	t = new JPJClientThread(this, socket);
				clientList.add(t);
				t.start();
			}
			catch(Exception e) {
				
			}
		}
	}

	public static void main(String[] args) {
		new JPJServer();
	}
}

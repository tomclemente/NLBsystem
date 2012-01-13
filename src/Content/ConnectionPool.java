package Content;

import java.util.List;
import pack.*;


import java.util.ArrayList;


public class ConnectionPool {

	private List<Server> connections = createConnections();
	//private Queue[] q_server;
	
	RoundRobin rr = new RoundRobin();
	
	private List<Server> createConnections() {
		rr.q_server = new Queue[rr.serverLimit];
	    List<Server> conns = new ArrayList<Server>(rr.serverLimit);
	    for (int i = 0; i < rr.serverLimit; i++) {
	    //conns.add(rr.q_server[i]);
	    conns.add(rr.server[i]);
	    }	
	    return conns;
	  }
	
	public Server getConnection() throws InterruptedException {
		  synchronized (connections) {
		    while (connections.isEmpty()) {
		      connections.wait();
		    }
		    return connections.remove(0);
		  }
		}
	
	public void returnConnection(Server conn) {
		  synchronized (connections) {
		    connections.add(conn);
		    connections.notify();
		    

		  }
		}

}

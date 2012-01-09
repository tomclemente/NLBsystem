package workspace;

import pack.*;
import java.lang.Math;
import java.util.*;

import pack.CurrentTime;
import pack.Entity;
import pack.Queue;
import pack.timeTable;


public class Algorithm {
	
	public Algorithm () {
	Entity computer[], retrieved, nextPacket, prevPacket;
	timeTable table;
	CurrentTime CurrentTime;
	Server server[];
	
	
	Queue nlb, serverfarm, global, q_server[];
	int eventArrived, x, nexTime, depart, index=0;
	int clock=0, daylen=9999999, requests=20, serverLimit=Server.getServerCount();
	long startTime=0, endTime, totalTime;
	nlb = new Queue();
	serverfarm = new Queue();
	global = new Queue();

	q_server = new Queue[serverLimit];
	server = new Server[serverLimit];
	
	for(x=0; x<serverLimit; x++){
		server[x] = new Server(x,0,"dummy");
		q_server[x] = new Queue();
	}
	
	
	//for testing purposes
	server[0] = new Server(1, 1, "Server1");
	server[1] = new Server(2, 1, "Server2");
	server[2] = new Server(3, 1, "Server3");
	//for testing purposes
	
	computer = new Entity[requests+2];
	
	for(x=0; x<requests; x++) {
		//gihimo-an ug entities
		computer[x] = new Entity(x, new String("" + x));
		
		}
	
	}
	
	
	public static int WRR(Server server[]) {
		
		
		int index =	Server.getHighestWeight(server);
		
		return index;
			
		
	}
	
	
}

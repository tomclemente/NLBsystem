package workspace;

import java.util.ArrayList;

import pack.CurrentTime;
import pack.Entity;
import pack.Queue;
import pack.timeTable;

public class RoundRobin {
	
	public Queue q_server[];
	public int serverLimit = 3;
	public Server server[];
	
	
	public RoundRobin() {
		//Entity computer[];
		Entity retrieved;
		Entity nextPacket = null;
		Entity prevPacket;
		timeTable table;
		CurrentTime CurrentTime;
		//User user[];
		
	
	
		int eventArrived;
	
		
		Queue nlb,serverfarm,global;
		CurrentTime = new CurrentTime();
		int x,nextTime,depart,index = 0;
		int clock = 0;
		int daylen = 888000;
		int requests = 100;
		//int requests = 20;

		long startTime=0, endTime, totalTime;
		nlb = new Queue();
		serverfarm = new Queue();
		global = new Queue();
		
	
		//creates an arraylist with the Entity object
		ArrayList<Entity> computer = new ArrayList<Entity>(requests+2);
		
		//queueing server
		q_server = new Queue[3];
	
		server = new Server[serverLimit];
		
		for(x=0; x<3; x++) {
			server[x] = new Server(x,0,"dummy");	}
		
		for(x=0; x<3; x++) {
			q_server[x] = new Queue();		}
		
		server[0] = new Server(1,1,"Server1");
		server[1] = new Server(2,1,"Server2");
		server[2] = new Server(3,1,"Server3");
	
		//ctr = requests;
		for(x=0; x<requests; x++)
		computer.add(new Entity(x, new String("" + x)));

		
		//computer = new Entity[requests+2];
	/*	for(x=0; x<requests;x++)	{
		computer[x] = new Entity(x, new String("" + x));
		}
	*/
		
		//adds new Entity to the last two
		computer.add(new Entity(x, new String("Start")));
		computer.add(new Entity(x, new String("End")));
		
	//	computer[requests] = new Entity(requests, new String("Start")); //set the start of the day
	//	computer[requests+1] = new Entity(requests+1, new String("End")); //set the end of the day
		
		
		//gi queue padung sa NLB 
		for(x=0; x<requests; x++)	{
			nlb.addTo(computer.get(x));	}

				
		serverfarm.addTo(computer.get(requests)); 
		//ang start ug end gi sud sa serverfarm
		serverfarm.addTo(computer.get(requests+1));
		
		computer.get(requests).event = 1; //set arrival for start 
		computer.get(requests+1).event = 10; //set end for end of the day

		table = new timeTable();
		table.setim(computer.get(requests), clock);
		table.setim(computer.get(requests+1), daylen);
		table.printTable();
		retrieved = table.scan();
		clock = retrieved.time;	
		eventArrived = retrieved.event;
		
		while(eventArrived != 10 && !nlb.isEmpty()) {
			
			
		switch(eventArrived){
		
		case 1: 
				//start of the day
				startTime = System.currentTimeMillis();
				serverfarm.erase(retrieved);
		
				global.addTo(retrieved);
				nextTime = clock + (int) Math.floor(Math.random() * 1000); 

				nextPacket = (Entity) nlb.randomSelect(); //ang queue gihimog entity
				table.setim(nextPacket, nextTime);
				nextPacket.event = 2;
				prevPacket = nextPacket;
				break;
		
		
		case 2: //arrival of packet sa serverfarm
				System.out.println(retrieved.value + " arrived at NLB: " + CurrentTime.getMinute(System.currentTimeMillis()) + "minutes and "+ CurrentTime.getSecond(System.currentTimeMillis()) + "seconds");
				prevPacket = nextPacket;
				
				//packet arrived to the server farm
				serverfarm.addTo(retrieved);
				
				//index = Server.getHighestWeight(server);
				
				//determine if the current index maxrequest, add delay
				q_server[index].addTo(retrieved);
				//System.out.println("indexnum" +index);
				
				System.out.println("index: "+index);
				System.out.println("requests: " +server[index].getRequest());
				
				
				//determine first the highest weight available
				//decide if any servers are available
				//decide the biggest available weight..
					//check for availability in the highest server weight
					//if the same weight, deduct.. 
					//if unavailable move to next weight.
					
				
				    depart = (int)  Math.floor(Math.random() * 3);
	                if(depart != 0){ /* some will never depart*/
	                  nextTime = clock +
	                             (int) Math.floor(Math.random() * 600);
	                  table.setim(retrieved,nextTime);
	                  retrieved.event = 3; /*departure*/
	                }
	                
	    
	                nextTime = clock + (int) Math.floor(Math.random() * 1000);
	             
	                /* 
	                if(index==0) {
	                	if((server[0].getRequest())==0) {
	                		System.out.println("tom:" +server[0].getRequest());
	                		nextPacket = prevPacket;	
	                	}
	                	else {
	                		nextPacket = (Entity) nlb.randomSelect();
	                		server[index].consumeRequest();
	                	}
	                }
	                // if index is not zero
	                
	 
                	if(index!=0) {
                		nextPacket = (Entity) nlb.randomSelect();
                		server[index].consumeRequest();
                	}
                	*/
	                
	           
//if no servers are available, wait();
//ConnectionPool cons = new ConnectionPool();
	                
	                nextPacket = (Entity) nlb.randomSelect();
	                server[index].consumeRequest();
	                
	                //System.out.println("nextpacket:" +nextPacket);
	                nextPacket.event = 2;
            		table.setim(nextPacket, nextTime);
            		index=(index+1)%3;
            		
            		//full request timeout
            		
	                break;
		case 3: //departure of packet
		  	  		server[index].allocateRequest();
			  	   
		                
			//  System.out.println(retrieved.value + " left at time :" +
			  		System.out.println(retrieved.value + " left at NLB: " + CurrentTime.getMinute(System.currentTimeMillis()) + "minutes and "+ CurrentTime.getSecond(System.currentTimeMillis()) + "seconds");	
			//System.currentTimeMillis());
			//  serverfarm.erase(retrieved);
			  		nlb.erase(retrieved);
			  		break;
		
		
		}
		  /*get the next entity, find the next clock, find what the event
        associated to that entity is*/
		
		retrieved = table.scan();
		clock = retrieved.time;
		eventArrived = retrieved.event;
		

		
	}
		serverfarm.erase(retrieved);
		global.addTo(retrieved);
		
		
		
		while(table.size() != 0) {
			retrieved = table.scan();
			global.addTo(retrieved);
			
		}
		endTime = System.currentTimeMillis();
		//System.out.println("SA"+endTime);
		totalTime = endTime - startTime;
	
		 System.out.println("stoped at :" + retrieved.value + " time: " + clock+ " event: " +eventArrived);
		 System.out.println("No items " + (requests + 2));
		 System.out.println("No items found " + (nlb.size() +serverfarm.size() + global.size())); /*Print the sizes of all the queues*/
		 System.out.println("NLB size " + nlb.size());
		 System.out.println("serverfarm size " + serverfarm.size());
		 System.out.println("server1 size " + q_server[0].size());
		 System.out.println("server2 size " + q_server[1].size());
		 System.out.println("server3 size " + q_server[2].size());
		 System.out.println("Simulation time "+ CurrentTime.getMinute(totalTime) +" minutes and " +CurrentTime.getSecond(totalTime) + " seconds.");

	}
	
	

}

package pack;

import java.lang.Math;
import java.util.*;


class Controller {

	public static void main(String args[]) {
		Entity computer[];
		Entity retrieved;
		Entity nextPacket;

		timeTable table;
		CurrentTime curr_time;
		
		Server server[];
		
		
	
	int eventArrived;
	
	Queue nlb,serverfarm,global,q_server[];
	curr_time = new CurrentTime();
	int x,nextTime,depart,ctr,index = 0;
	int clock = 0;
	int daylen = 99999;
	int requests = 900;
	int serverLimit = 3;
	long startTime=0, endTime, totalTime;
	nlb = new Queue();
	serverfarm = new Queue();
	global = new Queue();
	
	q_server = new Queue[3];
	server = new Server[serverLimit];
	for(x=0; x<3; x++) {
		server[x] = new Server(x,0,"dummy");
	}
	for(x=0; x<3; x++) {
		q_server[x] = new Queue();
		
	}
	server[0] = new Server(1,1,"Server1");
	server[1] = new Server(2,10,"Server2");
	server[2] = new Server(3,1,"Server3");

	ctr = requests;
	computer = new Entity[requests+2];
	for(x=0; x<requests;x++){
		computer[x] = new Entity(x, new String("" + x));}
	computer[requests] = new Entity(requests, new String("Start")); //set the start of the day
	computer[requests+1] = new Entity(requests+1, new String("End")); //set the end of the day
	
	
	//gi queue padung sa NLB
	for(x=0; x<requests; x++) {
		nlb.addTo(computer[x]);
	
	}
	
	serverfarm.addTo(computer[requests]); //ang start ug end gi sud sa serverfarm
	serverfarm.addTo(computer[requests+1]);
	
	computer[requests].event = 1; //set arrival for start 
	computer[requests+1].event = 10; //set end for end of the day
	
	table = new timeTable();
	table.setim(computer[requests], 0);
	table.setim(computer[requests+1], daylen);
	table.printTable();
	retrieved = table.scan();
	clock = retrieved.time;	
	eventArrived = retrieved.event;
	while(eventArrived != 10) {
		ctr--;
	//	secondTime = (int) System.currentTimeMillis();
		switch(eventArrived){
		
		case 1: //start of the day
			startTime = System.currentTimeMillis();
			serverfarm.erase(retrieved);
		
			global.addTo(retrieved);
			/* nextTime = clock +
					(int) Math.floor(Math.random() * 1000); */

			//dummy=curr_time.getHour(System.currentTimeMillis());
			//dummy2 = System.currentTimeMillis();
			//System.out.println("dummy2"+dummy2);
			//System.out.println(dummy + "seconds");
			System.out.println("WES");
			nextTime = clock + (int) System.currentTimeMillis();
		
			nextPacket = (Entity) nlb.randomSelect(); //ang queue gihimog entity
			table.setim(nextPacket, nextTime);
			nextPacket.event = 4;
			break;
		
		
		case 4: //arrival of packet sa serverfarm
				System.out.println(retrieved.value + " arrived at NLB:" + curr_time.getMinute(System.currentTimeMillis()) + "minutes and "+ curr_time.getSecond(System.currentTimeMillis()) + "seconds");
				
				serverfarm.addTo(retrieved);
				index = Algorithm.WRR(server);
				System.out.println("weeee2");
				System.out.println("WEAAS");
				q_server[index].addTo(retrieved);
				server[index].consumeRequest();
				
				
				
				//determine first the highest weight available
				//decide if any servers are available
				//decide the biggest available weight..
					//check for availability in the highest server weight
					//if the same weight, deduct.. 
					//if unavailable move to next weight.
	
				
				    depart = (int)  Math.floor(Math.random() * 3);
	                if(depart != 0){ /* some will never depart*/
	                  nextTime = clock +
	                             (int) Math.floor(Math.random() * 60);
	                  table.setim(retrieved,nextTime);
	                  retrieved.event = 3; /*departure*/
	                }
	                nextTime = clock +
	                           (int) Math.floor(Math.random() * 1000);
	                nextPacket = (Entity) nlb.randomSelect();
	                nextPacket.event = 4; /*arrival*/
	                table.setim(nextPacket, nextTime);

					
	                break;
		case 3: //departure of packet
			  server[index].allocateRequest();
			  System.out.println(retrieved.value + " left at time :" +
			  System.currentTimeMillis());
			  serverfarm.erase(retrieved);
			  nlb.addTo(retrieved);
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
	totalTime = endTime - startTime;
	
		System.out.println("stoped at :" + retrieved.value + 
                " time: " + clock+ " event: " +
                    eventArrived);
 System.out.println("No items " + (requests + 2));
 System.out.println("No items found " + (nlb.size() +
		 serverfarm.size() + global.size()));
                        /*Print the sizes of all the queues*/
 System.out.println("NLB size " + nlb.size());
 System.out.println("serverfarm size " + serverfarm.size());
 System.out.println("server1 size " + q_server[0].size());
 System.out.println("server2 size " + q_server[1].size());
 System.out.println("server3 size " + q_server[2].size());
 System.out.println("Simulation time "+ CurrentTime.getMinute(totalTime) +" minutes and " +CurrentTime.getSecond(totalTime) + " seconds.");

	}


}
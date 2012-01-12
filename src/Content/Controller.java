package Content;

import java.io.*;

public class Controller {
	public static int ServerCount = 0;
	public static int flag = 0;
	
	
	
	/* User */
	public static int UserCount;

	/**
	 * Server
	 */
	static String ServerName;
	static String ServerIP;
	static int ServerWeight;
	static Server temporaryServer[] = new Server[1000];

	/**
	 * @param args
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws IOException, InterruptedException {
		
		int AlgoChoice; // Scheduling Algorithm
		int temp; // Temporary Counter for loop
		
		BufferedReader test = new BufferedReader(new InputStreamReader(System.in)); // Read Line
		
		/**
		 * User
		 */
		String UserName,UserIP,Destination;
		int Request;
		int Bandwidth;
		User temporaryUser[] = new User[1000];
		float UserDelayNLB,UserDelayServer;
		boolean LifeStatus;
		
		/**
		 * Program Starts Here c(' . 'c)
		 */
		System.out.println("How Many Server do you want? ");
		ServerCount = Integer.parseInt(test.readLine());
		System.out.println("\n");
		
		/* Gets Information for the Server */
		for(temp = 0; temp <= ServerCount-1; temp++){
			System.out.println("Server Name: ");
			ServerName = test.readLine();
			
			System.out.println("Server IP: ");
			ServerIP = test.readLine();
			
			System.out.println("Server Weight:");
			ServerWeight = Integer.parseInt(test.readLine());
			
			temporaryServer[temp] = new Server((temp+1),ServerName,ServerIP,ServerWeight);
			System.out.println("\n");
		}
		
		for(temp = 0; temp <= ServerCount-1; temp++){
			System.out.println("Server #: "+temporaryServer[temp].getServerNumber());
			System.out.println("Server Name: "+temporaryServer[temp].getServerName());
			System.out.println("Server I.P. Address: "+temporaryServer[temp].getServeripadd());
			System.out.println("Server Weight: "+temporaryServer[temp].getServerWeight());
			System.out.println("Server Maximum Connections: "+temporaryServer[temp].getServerMaxConn());
			System.out.println("\n");
		}
		
		System.out.println("How many users do you want? ");
		UserCount = Integer.parseInt(test.readLine());
		System.out.println("\n");
		
		/* Gets Information for the User */
		for(temp = 0; temp <= UserCount-1; temp++){
			System.out.println("User Name: ");
			UserName = test.readLine();
			
			System.out.println("User IP: ");
			UserIP = test.readLine();
			
			System.out.println("Number of Request: ");
			Request = Integer.parseInt(test.readLine());
			
			System.out.println("[1] 512 Kbps");
			System.out.println("[2] 1 Mbps");
			System.out.println("[3] 2 Mbps");
			System.out.println("[4] 3 Mbps");
			System.out.println("Bandwidth: ");
			Bandwidth = Integer.parseInt(test.readLine());
			switch(Bandwidth){
				case 1:
					Bandwidth = 512 * 1000;
					break;
				case 2:
					Bandwidth = 1 * 1000000;
					break;
				case 3:
					Bandwidth = 2 * 1000000;
					break;
				case 4:
					Bandwidth = 3 * 1000000;
					break;
				default:
					Bandwidth = 0;
					break;
			}
			
			UserDelayNLB = ((1500 * 1000) * Request) / (Bandwidth / 8);
			UserDelayServer = -1;
			Destination = null;
			LifeStatus = true;
			
			temporaryUser[temp] = new User((temp+1),UserName,UserIP,Request,Bandwidth,UserDelayNLB,UserDelayServer,Destination,LifeStatus);
			System.out.println("\n");
		}
		
		for(temp = 0; temp <= UserCount-1; temp++){
			
			System.out.println("User #: "+temporaryUser[temp].getUserNumber());
			System.out.println("User Name: "+temporaryUser[temp].getUserName());
			System.out.println("User I.P. Address " +temporaryUser[temp].getUserIP());
			System.out.println("User Number of Request: "+temporaryUser[temp].getUserRequest());
			System.out.println("User Bandwidth: "+temporaryUser[temp].getUserBandwidth()+" bps");
			System.out.println("User Delay To NLB: " +temporaryUser[temp].getUserDelayNLB());
			System.out.println();
			System.out.println("\n");
		}

		// Choose Scheduling Algorithm
		System.out.println("Choose Scheduling Algorithm:");
		System.out.println("[1] Weighted Round-Robin");
		System.out.println("[2] Least Connection");
		System.out.println("[3] Minimum-Expected Delay");
		
		System.out.println("Choice: ");
		AlgoChoice = Integer.parseInt(test.readLine());
		NLB.setSchedule(AlgoChoice);
		switch(AlgoChoice){
		case 1:
			System.out.println("Weighted Round-Robin Scheduling Algorithm");
			break;
		case 2:
			System.out.println("Least Connection Scheduling Algorithm");
			break;
		case 3:
			System.out.println("Minimum Expected Delay Scheduling Algorithm");
			break;
		default:
			System.out.println("Empty!");
			break;
		}
		
		/* Simulation Starts Here */
		System.out.println("Simulation starts in 3...");
		Thread.sleep(1000);
		for(int x = 2; x>0; x--){
			System.out.println(x);
			Thread.sleep(1000);
		}
		
		// The Process
		
		while(true){
			System.out.println("\n\n\n\n\n\n\n");
			for(int count = 0; count <= UserCount - 1; count++){
			
				Runnable runnable1 = new UserToNLB(temporaryUser[count],flag,count);
				Thread thread1 = new Thread(runnable1);
				//thread1.stop();
				thread1.start();
				
				Runnable runnable2 = new NLBToServer(temporaryUser[count],flag,count);
				Thread thread2 = new Thread(runnable2);
				//thread2.stop();
				thread2.start();
				
				
				if(flag == 1)
					flag = 2;
			}
			Thread.sleep(1000);
			NLB.setSchedule(AlgoChoice);
		}
	}
}

package pack;

import java.lang.reflect.Array;

public class Server {

	public int id;
	public String name;
	public int weight;
	public int max_requests;
	public Server(int id,int weight, String name) {
		this.id = id;
		this.name = name;
		this.weight = weight;
		
			switch(weight) {
			case 10: max_requests=10000; break;
			case 9: max_requests=8000; break;
			case 8: max_requests=6000; break;
			case 7: max_requests=4000; break;
			case 6: max_requests=2000; break;
			case 5: max_requests=1000; break;
			case 4: max_requests=500; break;
			case 3: max_requests=250; break;
			case 2: max_requests=100; break;
			case 1: max_requests=30; break;
			default:
			
			}
	}
	
	
	public boolean availability() {
		boolean truth;
		if(max_requests>0) {
			truth = true;
		}
		else {
			truth = false;
		}
		return truth;	
	}
	
	public int getRequest() {
		return max_requests;
	}
	public int getId() {
		
		return id;
	}
	public void consumeRequest() {
		max_requests--;
	}
	public void allocateRequest() {
		
		max_requests++;
		
	}
	public int getWeight() {
		return weight;
	}
	
	@SuppressWarnings("null")
	public static void sort(Server server[]){
		int N = server.length;
		
		int dummy[] = null;
		
		System.out.println("WEEE "+((Server) server[1]).getWeight());
		
		for(int x=0; x<=2; x++) {
	//	((Server) server[x]).getWeight().sort();
	//	Arrays.sort(((Server) server[]).getWeight());
			//Arrays.sort((Server) server).getWeight() );
	//	dummy[x]=((Server) server[x]).getWeight();
		
		}
		
		for(int x=0; x<N; x++) {
			if( ((Server) server[x]).getWeight() > ((Server) server[x+1]).getWeight()) {
				
			}
		}
		
		/* 	for(int x=0; x<N; x++) {
			 for (int j=x; j>0; j--) {
				
				 if( ((Server) server[j-1]).getWeight().compareTo((Server) server[j]).getWeight() > 0);
				 
					if(server[j-1].compareTo(server[j]) > 0)
		             exch(server, j, j-1);
		         else break;
			 }
			
		}*/
		
	}
	
	public static int getHighestWeight(Server server[]){
		int y = server.length;
		
		int server_var = server[0].getWeight();
		for(int x=0; x<y; x++) {
			//System.out.println("waax" +x);
			for(int j=x; j>0; j--) {
				System.out.println("waa" +j);
				if( ((int) server[j-1].getWeight() > ((Server)server[j]).getWeight() )) {
					server_var = server[j-1].getWeight();
				}
				else {
					server_var = server[j].getWeight();
				}
				
			}
			
		
		}
		//System.out.println("WEE:" +y);
		if( ((int) server[y-1].getWeight() > ((Server)server[y-2]).getWeight() )) {
			server_var = server[y-1].getWeight();
		}
		
		
		return server_var;
	}
}

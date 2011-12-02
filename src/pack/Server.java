package pack;


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
			max_requests=1000;
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
	
	
	public static int getHighestWeight(Server server[]){
		int y = server.length;
		int x_val = 1;
		int current_index = 0;
		
		for(int x=0; x<y; x++) {
		
			
			if(((Server) server[x]).getWeight()>=x_val) {
				
				if((server[x].getRequest())>0) {
				x_val=((Server)server[x]).getWeight();
				current_index=x;
				}
			}
		}
		
		return current_index;
	}
}

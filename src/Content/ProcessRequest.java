package Content;

public class ProcessRequest implements Runnable{
	
	float ProcessingTime;
	User tempUser;
	
	public ProcessRequest(float ProcessingTime,User tempUser){
		this.ProcessingTime = ProcessingTime;
		this.tempUser = tempUser;
	}
	
	public void run(){
		//least connections
		
	}
}

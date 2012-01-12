//import java.util.LinkedList;
package Content;


public class User{
	/**
	 * Current Connections
	 */
		private int UserNumber;
		private String UserIP;
		private String UserName;
		private int Request;
		private int Bandwidth;
		private float UserDelayNLB;
		private float UserDelayServer;
		private String Destination;
		private boolean LifeStatus;
		
	
	public User(int UserNumber, String UserName,String UserIP, int Request,int Bandwidth,float UserDelayNLB,float UserDelayServer,String Destination,boolean LifeStatus){ 
		this.UserNumber = UserNumber;
		this.UserName = UserName;
		this.UserIP = UserIP;
		this.Request = Request;
		this.Bandwidth = Bandwidth;
		this.UserDelayNLB = UserDelayNLB;
		this.UserDelayServer = UserDelayServer;
		this.Destination = Destination;
		this.LifeStatus = LifeStatus;
	}
	
	public void checkIPAddress(String computerIPAdd){
		//Check IP Address format and its protocol.
	}
	
	public void Limitation(){
		//Check limitation for inputs
	}
	public void checkUserStatus(){
		//Check if deleted or not.
	}
	
	public int getUserNumber(){
		return this.UserNumber;
	}
	
	public String getUserName(){
		return this.UserName;
	}
	
	public String getUserIP(){
		return this.UserIP;
	}
	
	public int getUserRequest(){
		return this.Request;
	}
	
	public int getUserBandwidth(){
		return this.Bandwidth;
	}
	
	public void setUserDestination(String Destination){
		this.Destination = Destination;
	}
	
	public String getUserDestination(){
		return this.Destination;
	}
	
	public void setUserDelayNLB(float UserDelayNLB){
		this.UserDelayNLB = UserDelayNLB;
	}

	public float getUserDelayNLB() {
		return this.UserDelayNLB;
	}
	
	public void setUserDelayServer(float UserDelayServer){
		this.UserDelayServer = UserDelayServer;
	}

	public float getUserDelayServer() {
		return this.UserDelayServer;
	}
	
	public boolean getLifeStatus(){
		return this.LifeStatus;
	}
	
	public void setLifeStatus(boolean LifeStatus){
		this.LifeStatus = LifeStatus;
	}
}

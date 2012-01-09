package workspace;

import pack.*;

//import java.util.LinkedList;


public class User{
	/**
	 * Current Connections
	 */
		private int UserNumber;
		private String UserIP;
		private String UserName;
		private int Request;
		private int Bandwidth;
		private float Delay;
	
	public User(int UserNumber, String UserName,String UserIP, int Request,int Bandwidth,float Delay){ 
		this.UserNumber = UserNumber;
		this.UserName = UserName;
		this.UserIP = UserIP;
		this.Request = Request;
		this.Bandwidth = Bandwidth;
		this.Delay = Delay;
	}
	
	
	
	public void setSizeRequest(){
		// 1500kB
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
	
	public void setDelay(float Delay){
		this.Delay = Delay;
	}

	public float getDelay() {
		return this.Delay;
	}
}

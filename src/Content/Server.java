package Content;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class Server {
	
	/**
	 * Server Information
	 */
	private String ServerName;
	private String ServerIP;
	private Queue Connection;
	private int ServerNumber;
	private static int ServerWeight;
	private int ServerMaxConn;
	private float ServerDelayToNLB;
	private float ServerDelayFromNLB;
	
	private Queue incomingrequest = new LinkedList();
	
	/**
	 *  Information:
	 *  
	 *  Delay = Size / Bandwidth
	 *  1500 KiloBytes
	 *  
	 */
	
	/**
	 * Constructor
	 */
	public Server(int ServerNumber,String ServerName,String ServerIP,int ServerWeight){
		this.Connection = new LinkedList();
		this.ServerNumber = ServerNumber;
		this.ServerName = ServerName;
		this.ServerIP = ServerIP;
		this.ServerWeight = ServerWeight;
		setServerMaxConn(ServerNumber,ServerWeight);
		this.ServerMaxConn = getServerMaxConn();
	}
	/* sa User ni gikan
	public void CurrentConnection(String UserIP, String UserHostName, int Request){
		this.UserIP = UserIP;
		this.UserHostName = UserHostName;
		this.Request = Request;
	}
	*/
	public void checkServerStat(){
		Connection.element();
	}
	
	public void insertReq(User tempUser){
		//processReq(tempUser);
		Connection.offer(tempUser);
	}
	
	public int getServerCurrentSize(){
		return Connection.size();
	}
	
	public int getTotalReq(){
		int TotalReq = 0;
		
		Iterator itq = Connection.iterator();
	       while (itq.hasNext()) {
	    	   TotalReq = TotalReq + ((User)itq.next()).getUserRequest();
	       }
	       return TotalReq;
	}
	
	public int[] getReq(){
		int TempServerConnections[] = new int[ServerMaxConn];
		int count = 0;
		//String temp;
		Iterator itq = Connection.iterator();
	       while (itq.hasNext()) {
	    	   //temp = (String) itq.next();
	    	   TempServerConnections[count] =  ((User)itq.next()).getUserRequest();
	    	   count++;
	       }
	       return TempServerConnections;
	}
	
	public static void processReq(User tempUser){
		float ProcessingTime;
	
		ProcessingTime = (tempUser.getUserRequest()*100) / ServerWeight;
		
		ProcessRequest tempProcessRequest = new ProcessRequest(ProcessingTime, tempUser);
		//	ProcessingTime = tempUser.getUserRequest()  / 0; 
		//if(Connection.peek() != null){
		//	String tempRequest;
		//	tempRequest = (String) Connection.peek();
		//Request = Integer.parseInt(tempRequest);
		//}
	}
	
	public void replyReq(){
		
	}
	
	public void setServerMaxConn(int ServerNumber,int ServerWeight){
		switch(ServerWeight){
		case 1:
			ServerMaxConn = 1000;
			break;
		case 2:
			ServerMaxConn = 2000;
			break;
		case 3:
			ServerMaxConn = 3000;
			break;
		case 4:
			ServerMaxConn = 4000;
			break;
		case 5:
			ServerMaxConn = 5000;
			break;
		case 6:
			ServerMaxConn = 6000;
			break;
		case 7:
			ServerMaxConn = 7000;
			break;
		case 8:
			ServerMaxConn = 8000;
			break;
		case 9:
			ServerMaxConn = 9000;
			break;
		case 10:
			ServerMaxConn = 10000;
			break;
		default:
			ServerMaxConn = 0;
			break;
		}
	}
	
	public void setDelay(){
		
	}
	
	public int getDelay(){
		return 0;
	}
	
	
	public int getServerNumber(){
		return this.ServerNumber;
	}
	
	public String getServerName(){
		return this.ServerName;
	}
	
	public String getServeripadd(){
		return this.ServerIP;
	}
	
	public int getServerWeight(){
		return this.ServerWeight;
	}
	public void consumeRequest() {
		this.ServerMaxConn--;
	}
	public void allocateRequest() {
		
		this.ServerMaxConn++;
		
	}
	
	public int getServerMaxConn(){
		return this.ServerMaxConn;
	}
	
	public int getServerNumCurrentConnection(){
		return Connection.size();
	}
}
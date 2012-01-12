package Content;

public class NLB {
//	private int ServerCount = 0;
//	private static int SchedulingAlgorithm = 0;
	public static int Schedule;
	public static int ReqGoingToServer = 0;
	
	//public NLB(/*int option*/){
		//setSchedAlgo(option);
		/**
		 * Constructor
		 */
	//}
	
	public static void incomingRequest(){
		ReqGoingToServer++;
	}
	
	public static void outcomingRequest(){
		ReqGoingToServer--;
	}
	
	public static int getSchedule(){
		return Schedule;
	}
	
	public static void setSchedule(int SchedulingAlgorithm){
		if(SchedulingAlgorithm == 1){
			
		}
		else if(SchedulingAlgorithm == 2){
			LCS tempLCS = new LCS();
			Schedule = tempLCS.LCSSchedule();
		}
		else if(SchedulingAlgorithm == 3){
			MED tempMED = new MED();
			Schedule = tempMED.MEDSchedule();
		}
		else{
			
		}
	}
	
	public static void assignReqToServ(User tempUser){
		tempUser.setUserDestination(Controller.temporaryServer[Schedule - 1].getServeripadd());
		tempUser.setUserDelayServer(((1500 * 1000) * tempUser.getUserRequest()) / (8000000 / 8));
		//Check if ma kaya pa sa server ang connection through max conn
		if(Controller.temporaryServer[Schedule - 1].getServerMaxConn() == Controller.temporaryServer[Schedule - 1].getServerNumCurrentConnection())
			tempUser.setLifeStatus(false);
		else{	
			Controller.temporaryServer[Schedule - 1].insertReq(tempUser);
			incomingRequest();
		}
	}
	
	public boolean checkInReq(boolean flag){
		if(flag)
			return true;
		else
			return false;
	}
	
	public boolean chekOutReq(boolean flag){
		if(flag)
			return true;
		else
			return false;
	}
	
	public boolean checkServerConn(int MaximumConn,int CurrentConn){
		if(MaximumConn == CurrentConn)
			return true;
		else
			return false;
		// Check if Server is full
	}
	
	public void setNextServer(){
		
	}
	
	public void getNextServer(){
		
	}
	
	public void checkIPAdd(){
		
	}
	
	public void checkServerStat(){
		
	}
}

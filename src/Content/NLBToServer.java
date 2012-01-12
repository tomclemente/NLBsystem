package Content;

public class NLBToServer implements Runnable{
	
	User temporaryUser;
	int flag,count;
	
	public NLBToServer(User tempUser,int tempflag,int tempcount){
		temporaryUser = tempUser;
		flag = tempflag;
		count = tempcount;
	}
	
	public void run(){
		if((temporaryUser.getUserDelayServer() >= 0) && (temporaryUser.getUserDelayNLB() == -1) && (flag == 2)){
			System.out.println("User Request No.: "+(count+1)+" going to Server IP. "+temporaryUser.getUserDestination()+" | Delay: "+temporaryUser.getUserDelayServer());
			temporaryUser.setUserDelayServer(temporaryUser.getUserDelayServer() -1);
			if(temporaryUser.getUserDelayServer() < 0){
				for(int x = 0; x < Controller.ServerCount; x++){
					if(Controller.temporaryServer[x].getServeripadd().equals(temporaryUser.getUserDestination()) == true){
						Server.processReq(temporaryUser);
					}
				}
			}
		}
	}
}

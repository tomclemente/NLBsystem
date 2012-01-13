package Content;

public class UserToNLB implements Runnable{
	
	User temporaryUser;
	int flag,count;
	
	public UserToNLB(User tempUser,int tempflag,int tempcount){
		temporaryUser = tempUser;
		flag = tempflag;
		count = tempcount;
	}
	
	public void run(){
		if((temporaryUser.getUserDelayNLB() >= 0) && (temporaryUser.getUserDelayServer() == -1) && (flag == 0 || flag == 2)){
			System.out.println("User Request No: "+(count+1)+" going to NLB | Delay: "+temporaryUser.getUserDelayNLB());
			temporaryUser.setUserDelayNLB(temporaryUser.getUserDelayNLB() -1);
			if(temporaryUser.getUserDelayNLB() < 0){
				NLB.assignReqToServ(temporaryUser);
				System.out.println("Schedule for User No. "+(count+1)+" is Server No."+NLB.getSchedule());
				Controller.flag = 1;
				
			}
		}
	}
}

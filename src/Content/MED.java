package Content;

public class MED {

	public static int ServerCount = Controller.ServerCount;
	static int[] delay = new int [ServerCount];
	int[] currentConnections = new int [ServerCount];
	static int[] Server = new int [ServerCount];
	static int[] ServerWeight = new int[ServerCount];
	static int tempServerIndex;
	public int MEDSchedule(){
		for(int count = 0; count<=ServerCount - 1; count++){
			ServerWeight[count] = 11;
			currentConnections[count] = 11;
			Server[count] = 11;
		}
		
		for(int count = 0; count<=ServerCount - 1; count++){
			currentConnections[count] = Controller.temporaryServer[count].getTotalReq();
			Server[count] = Controller.temporaryServer[count].getServerNumber();
			ServerWeight[count] = Controller.temporaryServer[count].getServerWeight();
		}
		
		for(int x=0; x<=ServerCount - 1; x++){
			delay[x] = (currentConnections[x] + 1) / ServerWeight[x];
		}
		
		Sort(delay);
		
		return Server[tempServerIndex];
	}
	
	public static int Sort(int[] arr){		
		for(int y=0; y<ServerCount; y++){
			if(delay[y] < delay[tempServerIndex]){
				tempServerIndex = y;
			}
			else{
				
			}
		}
		
		return tempServerIndex;
	}
}

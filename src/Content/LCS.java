package Content;

public class LCS {
	
	public static int ServerCount = Controller.ServerCount;
	
	int[] currentConnections = new int [ServerCount];
	static int[] ServerNumber = new int [ServerCount];
	static int[] tempServer = new int[ServerCount];
	
	public int LCSSchedule(){
			
		for(int count = 0; count<=ServerCount - 1; count++){
			tempServer[count] = 11;
			currentConnections[count] = 11;
			ServerNumber[count] = 11;
		}
		
		for(int count = 0; count<=ServerCount - 1; count++){
			currentConnections[count] = Controller.temporaryServer[count].getTotalReq();
			ServerNumber[count] = Controller.temporaryServer[count].getServerNumber();
			tempServer[count] = Controller.temporaryServer[count].getServerWeight();
		}
		
		currentConnections = Sort (currentConnections,tempServer,ServerNumber); 
		
		return ServerNumber[0];
	}
	
public static int[] Sort(int[] arr,int arr2[],int arr3[]){
		
		int j,k,l,m,n,o,a;
		a=0;
		for(;;){
			for(j=0,l=0,n=0; j<arr.length-1; j++,l++,n++){
				if(arr[j] > arr[j+1]){
					a=0;
					k = arr[j+1];
					m = arr2[l+1];
					o = arr3[n+1];
					
					arr[j+1] = arr[j];
					arr2[l+1] = arr2[l];
					arr3[n+1] = arr3[n];
					
					arr[j] = k;
					arr2[l] = m;
					arr3[n] = o;
				}
			}
			if(a==10){
				break;
			}
			a++;
		}
		
		for(int z = 0; z<arr.length-1; z++){
			if(arr[z] >= 11){
				arr[z] = 0;
			}
		}
		
		tempServer = arr2;
		ServerNumber = arr3;
		return arr;
	}
}

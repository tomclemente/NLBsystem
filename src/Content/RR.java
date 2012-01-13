package Content;

public class RR {
	
	private static int ServerCount = Controller.ServerCount;
	static int[] Server = new int [ServerCount];
	private int index, x=0;
	
	protected int RR() {
		
		x=x+1;
		if(x==ServerCount) x=0; 
		return Server[x%ServerCount];
		
	
		
		
	}

}

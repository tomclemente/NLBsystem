package pack;

import java.util.*;

public class CurrentTime{
	


	public void GetCurrentTime() {
		String am_pm;

		Calendar calendar = new GregorianCalendar();
		int hour = calendar.get(Calendar.HOUR);
		int minute = calendar.get(Calendar.MINUTE);
		int second = calendar.get(Calendar.SECOND);
		  if(calendar.get(Calendar.AM_PM) == 0)
		  am_pm = "AM";
		  else
		  am_pm = "PM";
		  System.out.println("Current Time : " + hour + ":" 
		+ minute + ":" + second + " " + am_pm);
		
		
	}
 
	public int getSecond(long time){
		
	
		int seconds = (int) ((time/1000) % 60);
		return seconds;
		
	}
	
	public int getMinute(long time) {
		
		int minutes = (int) ((time/(1000*60)) % 60);
		return minutes;
	}
	
	public int getHour(long time) {
		int hours = (int) ((time/(1000*60*60)) % 24);
		return hours;
		
	}
  
  
  
}
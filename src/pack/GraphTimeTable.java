package pack;

import java.awt.*;

/**
 * This Class is used to store the timetable of events.
 * It also provides a Graphical interface
 * Example creation:
 * <pre>
 *    GraphTimeTable table;
 *    Panel clockPanel = new Panel()
 *    Frame myFrame = new Frame();
 *    table = new GraphTimeTable(clockPanel,"Clock time(in sec): ");
 *    myFrame.add(clockPanel);
 * </pre>
 * GraphTimeTable adds all its awt components into the container specified
 * in the first argument of the constructor (a Panel in our example).
 * Because it extends class timeTable all methods in timeTable still apply.
 *
 * @version 1.1 Jul 29 1996
 * @author  Nikos Massios
 */

public class GraphTimeTable extends timeTable{
   private Label messageLabel;
   private noFlickerLabel myLabel;


/**
  * The constuctor method for GraphTimeTable.
  * @param mainCont the Container on which all components are going to
  * be added. This container has to be allocated in by the caller. 
  * @param message a string message to be displayed.
  */
   public GraphTimeTable(Container mainCont,String message){
	super();
	myLabel = new noFlickerLabel("0");
	messageLabel = new Label(message);

	mainCont.setLayout(new GridLayout(1,0));
	mainCont.add(messageLabel);
	mainCont.add(myLabel);
   }    


/**
 * This method removes enitites from the front of the timetable
 * @return  the entity in the front of the timetable
 */
   public Entity scan(){
     Entity returnEntity;

     returnEntity = super.scan();
     myLabel.setText(String.valueOf(returnEntity.time));
     return(returnEntity);
   }

}




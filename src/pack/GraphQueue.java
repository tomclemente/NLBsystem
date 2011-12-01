package pack;

import java.awt.*;

/**
 * This Class is used to store objects(Entities in our case) in a queue 
 * fashion.
 * To create:
 * <pre>
 *    Panel Label = new Panel();
 *    GraphQueue storeq;
 *    storeq = new GraphQueue("Store q size: ",Labels,200);
 * </pre>
 * Since GraphQueue extends Queue all the methods in Queue that are not
 * overriden still apply. GraphQueue also gives a graphical interface
 * to show how full the Queue is. The Queue component is added on the\
 * container specified in the second argument of the constructor. 
 * (a Panel in our example);
 *
 * @version 1.0 Jul 25 1996
 * @author  Nikos Massios
 */

public class GraphQueue extends Queue{
	private Panel queuePanel;
	private Label messageLabel;
	private QueueCanvas queueCanvas;
	private int sleepTime = 7; /*Time to sleep in update in 
	                             milliseconds*/

/**
 * This method is called to change the sleep time. 
 * The sleep time might have to be changed in different platforms
 * The sleep is needed for updateSize to guarantee that the update of
 * QueueCanvas is called
 * @param newSleep is thew new value of sleep.
 */
	public void changeSleep(int newSleep){
	   sleepTime = newSleep;
        }

/**
 * The constructor for GraphQueue
 * @param sizeMessage  The string to be printed in the messageLabel.
 * @param queuesCont   The container to add my queuePanel too.
 * @param maxSize      the maximum queue size(usefull for resizing)
 */
	public GraphQueue(String sizeMessage, Container queuesCont, int maxSize){
	        queuePanel = new Panel();
	        messageLabel = new Label(sizeMessage);
	        queueCanvas = new QueueCanvas(maxSize);

	        queuePanel.setLayout(new GridLayout(1,0));
	        queuePanel.add(messageLabel);
	        queuePanel.add(queueCanvas);
	        queuesCont.add(queuePanel);
	}

/* This method is used to modify the canvas*/
	private void updateSize(){
	    Thread myThread;

	    queueCanvas.setCurrent(size());
	    myThread = Thread.currentThread();
	    queueCanvas.repaint((long) 1);
	    try{
  	      myThread.sleep((long) sleepTime);
            }
            catch(java.lang.InterruptedException e){}
	}

/**
 * The method to add objects in the end of the queue
 * @param item the object to add
 * @return the object to return
 */
	public Object addTo(Object item){
	    Object retrn;
	    retrn = super.addTo(item);
	    updateSize();
	    return(retrn);
	}

/**
 * The method to remove objects from the front of the queue
 * @return the returned Object 
 */
	public Object bHead(){
	    Object first;
	    first = super.bHead();
	    updateSize();
	    return(first);
	}	

/**
 * The method to remove an object from the Queue
 * @param objectToErase the object to be erased
 * @return true if the object was erased
 *         false if the object was not in the queue
 */
        public boolean erase(Object objectToErase){
     	    boolean retrn;
	
	    retrn = super.erase(objectToErase);
	    updateSize();
            return(retrn);
        }

        public Object randomSelect(){
	    Object retrn;

	    retrn = super.randomSelect();
	    updateSize();
	    return(retrn);
        }
}

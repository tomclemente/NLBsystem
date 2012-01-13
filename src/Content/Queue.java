package Content;
import java.util.Vector;

/**
 * This Class is used to store objects(Entities in our case) in a queue fashion
 * To create:
 * <pre>
 *    Queue storeq;
 *    storeq = new Queue();
 * </pre>
 *
 * @version 1.0 Jul 18 1996
 * @author  Nikos Massios
 */

public class Queue extends Vector{

/**
 * The method to add objects in the end of the queue
 * @param item		the objects to be added
 * @return 		the objects that was added
 */
	public Object addTo(Object item){
	    addElement(item);
	    return(item);
	}

/**
 * The method to remove objects from the front of the queue
 * @return		the first object in the Queue
 */
	public Object bHead(){
	    Object first;
	    first = firstElement();
	    removeElementAt(0);

	    return(first);
	}	

/**
 * The method to remove an object from the Queue
 * @param objectToErase	the object to erase
 * @return true 	if item was removed
 *         false 	if item was not removed
 */
        public boolean erase(Object objectToErase){
        int index;

        if(contains(objectToErase)){
          index = indexOf(objectToErase);

          removeElementAt(index);
          return(true);
        }
        else
          return(false);
        }

/**
 * This method picks an item at random from the queue and removes it.
 * @return  The item that was selected
 */
        public Object randomSelect(){
	    int randomIndex;
            Object returnObject;
	    
	    randomIndex  = (int) Math.floor(Math.random() * size());
            returnObject = elementAt(randomIndex);
            removeElementAt(randomIndex);
	    return(returnObject);
        }
}

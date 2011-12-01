package pack;
import java.util.Vector;
import java.util.NoSuchElementException;


/**
 * This Class is used to store the timetable of events.
 * For example:
 * <pre>
 *    timeTable table;
 *    table = new timeTable();
 * </pre>
 *
 * @version 1.0 Jul 18 1996
 * @author  Nikos Massios
 */

public class timeTable extends Vector{

/**
 * The method to add entities in the timetable. Adds them in the correct
 * place
 * @param newEntity		The new entity to add in the timetable
 * @param time 		The time associated to that entity in the 
 *                              time table
 */
   public void setim(Entity newEntity, int time){
     int i = 0;
    
     newEntity.time = time;
     if(isEmpty())
	addElement(newEntity);
     else{
        while((size() >= i+1)&&(((Entity) elementAt(i)).time < newEntity.time))
          i++;
        if(i <= size())
          insertElementAt(newEntity,i);        
        else
          addElement(newEntity);
     }
   }


/**
 * This method prints the contents of the time table. It is useful for
 * debuging
 */
   public void printTable(){
      int i = 0;

      while(i<size()){
        System.out.println("Entity " + ((Entity) elementAt(i)).value +
                           " at pos " + i);
        i++;
      }

   }


/**
 * This method removes enitites from the front of the timetable
 * @return 			The first entity in the timetable
 */
  public Entity scan(){
    Entity returnEntity;


     returnEntity = (Entity) firstElement();
     removeElementAt(0);
     return(returnEntity);
   }

/**
 * This method is used to find out whether there are more items at the
 * same time
 * @param removedEntity		The entity that was just removed
 * @return 			true if more entities at that time
 *				false otherwise
 */
   public boolean moreEntities(Entity removedEntity){

     try{
       if(removedEntity.time == ((Entity) firstElement()).time)
           return(true);
       else
           return(false);
     }
     catch(NoSuchElementException e){
     }
     return(false);

   }

/**
 * This method removes an entity from the timetable
 * @param entityToDelete			The entity to be removed
 * @return true if entity was deleted
 *         false if it does not exist
 */
   public boolean delete(Entity entityToDelete){
     int index;
     
     if(contains(entityToDelete)){
       index = indexOf(entityToDelete);

       removeElementAt(index);
       return(true);
     }
     else
       return(false);
   }        

/**
 * This method changes the time associated to an entity in the timetable.
 * @param entityToChange the entity to reschedule
 * @param newTime	 the new time for the entity
 * @return 		 true if entity exists in the timetable
 *                       false if entity does not exist
 */
   public boolean retim(Entity entityToChange, int newTime){
     int index;

     if(contains(entityToChange)){
       index = indexOf(entityToChange);
       removeElementAt(index);
       setim(entityToChange, newTime);
       return(true);
     }
     else 
       return(false);
   }

/**
  * This method does nothing. 
  * It is added because it exists in the Fortran code for Siman
  * @param item 	some entity (not used)
  */
   public void adjust(Entity item){

   }

}




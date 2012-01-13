package Content;
/**
 * This Class is used to store information related to an entity.
 * To create an entity:
 * <pre>
 *    timeTable table;
 *    table = new timeTable();
 * </pre>
 *
 * @version 1.0 Jul 29 1996
 * @author  Nikos Massios
 */

public class Entity{

/**
 * The id of an Entity
 */
   public int id;

/**
 * The time associated to an entity in the timeTable
 */
   public int time;

/**
 * The event to happen at that time.
 */
   public int event;

/**
 * The value an entity has (can be any type of object)
 */
   public Object value;



/**
 * The constuctor method for this class
 * @param nid; 	        An id number for the entity;
 * @param nvalue;       A value associated with it.
 */
   public Entity(int nid, Object nvalue){ id = nid; value = nvalue;}
}

package pack;
import java.awt.*;

/**
 * This Class is used to display the moving bar component in GraphQueue.
 * To create a QueueCanvas:
 * <pre>
 *    QueueCanvas queueCanvas;
 *    queueCanvas = new QueueCanvas(maxSize);
 * </pre>
 * Where maxSize is the maximum size we expect the Queue to reach.
 *
 * @version 1.0 Jul 26 1996
 * @author  Nikos Massios
 */

public class QueueCanvas extends Canvas{
   private int max;
   private int current;
   private Dimension offDimension;
   private Image offImage;
   private Graphics offGraphics;
   private Dimension minSize;

/**
 * This is the constructor for QueueCanvas
 * @param size the maximum number of elements the Queue will ever have
 */
   public QueueCanvas(int size){
	super();

	current = 0;
	max = size;
   }


/**
 * This method is called to calculate the minimum size of the Canvas
 * to be allocated
 */
   private void measure(){
	int x,y;
	int stringHeight;
	FontMetrics fm = this.getFontMetrics(this.getFont());
	if (fm == null) return;

	stringHeight = fm.getHeight();
	y = stringHeight*2;
	x = fm.stringWidth(String.valueOf(current))+
	    fm.stringWidth(String.valueOf(max))+
	    fm.stringWidth(String.valueOf("0"));
	minSize = new Dimension(x,y);
   }

/**
 * This method is called when the peer of the canvas has to be created
 * using the canva's toolkit. Most applications should not need to call it
 * directly. It is overidden here to also calculate the minimum size for
 * the window.
 */
   public void addNotify(){super.addNotify(); measure();}

/**
 * This method is used to change the size of the queue to be displayed
 * @param curr  The current number of objects in the Queue
 */
   public void setCurrent(int curr){
        current = curr;
   }


/**
 * This method is reporting the minimum size the Canvas should be.
 * It does not need to be called by our applications.
 * @return the minimum size of the canvas as a Dimention.
 */
   public Dimension minimumSize(){
	return minSize;	
   }


/**
 * This method is reporting the prefered size of the Canvas.
 * It returns exactly the same result as minimum size.
 * It does not need to be called by our applications.
 * @return the prefered size of the canvas as a Dimention.
 */
   public synchronized Dimension preferredSize(){
	return minimumSize();
   }


/**
 * This method is called to repaint the canvas. 
 * @param g the graphics context.
 */
   public void paint(Graphics g){
	update(g);
   }


/**
 * This method is called whenever a repaint is forced by the
 * user program. 
 * @param g the graphics context.
 */
   public void update(Graphics g){   
	Dimension d = size();
        Rectangle box;
	int stringWidth;
	int stringHeight;
	int endX;
	int x,y;
	FontMetrics fontMetrics = g.getFontMetrics();

        if( (offGraphics == null) || 
            (d.width != offDimension.width) ||
            (d.height != offDimension.height)){
	   offDimension = d;
	   offImage = createImage(d.width, d.height);
           offGraphics = offImage.getGraphics();
        }

  	//Erase the previous image
        offGraphics.setColor(getBackground());
        offGraphics.fillRect(0,0,d.width,d.height);

        //Paint the Rectangle
        box = g.getClipRect();
	offGraphics.setColor(new Color(0,0,255));
	stringWidth = fontMetrics.stringWidth(String.valueOf(max));
	endX = box.x + box.width - stringWidth/2;
	offGraphics.fill3DRect(box.x,box.y,(int)((endX)*current/max),
                               box.height-5,true);

        // Draw the first and last index
	offGraphics.setColor(new Color(255,255,255));
        offGraphics.drawString(String.valueOf(max),endX - stringWidth/2,
                               box.y+box.height-5);
	offGraphics.drawString("0",box.x, box.y+box.height-5);

        // Draw the current number of objects in the queue
	stringHeight = fontMetrics.getAscent();
	stringWidth = fontMetrics.stringWidth(String.valueOf(current));
	offGraphics.drawString(String.valueOf(current),box.x+ (((endX) * current)/max) - stringWidth/2, box.y+stringHeight);	


        //Paint image into the screen
        g.drawImage(offImage, 0, 0, this);
	stringHeight = fontMetrics.getHeight();
	y = stringHeight*2;
	x = fontMetrics.stringWidth(String.valueOf(current))+
	    fontMetrics.stringWidth(String.valueOf(max))+
	    fontMetrics.stringWidth(String.valueOf("0"));
	minSize = new Dimension(x,y);
   }

}

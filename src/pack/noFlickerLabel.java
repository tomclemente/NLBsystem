package pack;
import java.awt.*;

/**
 * This Class is used to display a label that does not flicker.
 * Example creation:
 * <pre>
 *    noFlickerLabel myLabel;
 *    myLabel = new noFlickerLabel("0");
 * </pre>
 * The noFlickerLabel class uses canvas to create and diplay a flicker
 * free label
 *
 * @version 1.0 Jul 25 1996
 * @author  Nikos Massios
 */
public class noFlickerLabel extends Canvas{
  private String printString;
  private Dimension offDimension;
  private Image offImage;
  private Graphics offGraphics;
  private int sleepTime = 7;


/**
 * This is the constructor method for class noFlickerLabel.
 * @param myString the Label to initialise the Label to.
 */
  public noFlickerLabel(String myString){
    printString = myString;
  }

/**
 * This method changes the text the label is suposed to display
 * @param myString the new string to be displayed
 */
  public void setText(String myString){
    Thread myThread;

    printString = myString;
    myThread = Thread.currentThread();
    this.repaint((long) 1);
    try{
       myThread.sleep((long) sleepTime);
    }
    catch(java.lang.InterruptedException e){}


  }

/**
 * The method to be called when the label is first created
 * @param g the Graphics context
 */
  public void paint(Graphics g){
    update(g);
  }

/**
 * The method to be called whenever repaint() is called
 * @param g The graphics context
 */
  public void update(Graphics g){
    Dimension d = size();
    Rectangle box;
    int stringWidth;
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
   
    //Write The string
    box = g.getClipRect();
    stringWidth = fontMetrics.stringWidth(String.valueOf(printString));
    offGraphics.setColor(new Color(0,0,0));
    offGraphics.drawString(printString, (box.x+box.width - stringWidth)/2,
	                   (box.y+box.height)/2);


    g.drawImage(offImage, 0, 0, this);
  }
}

//TODO: ASAP: Make everything work with the outline/shape making stuff. OR TETRIS WLIL NEVER WORK
//TODO: Refactor all of this
package actors;

import gameengine.GameModel;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Shape;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.GeneralPath;
import java.awt.image.PixelGrabber;
import java.util.*;

import javax.swing.ImageIcon;
import physics.Direction;
import physics.PhysicsVector;
import actions.*;

/**
 * 
 * @author Michael Yu
 * 
 */
public abstract class Actor
{

    private GameModel myModel; // Model
    private Image myImage; // Image
    private String myImageString;
    private Area myShape; // Bounding Shape
    private AffineTransform myXform; // Transform
    private Point myPosition; // Position
    private double myHeading; // Heading
    private Dimension mySize; // Size
    private PhysicsVector myVelocity; // Velocity
    protected Map<Integer, List<Action>> myKeyEvents; // KeyEvents
    protected Map<String, List<Action>> myInteractions; // Interaction
    protected Action myDefaultBehavior; // Default Action
    public boolean hasChanged; // Flag - Changed?
    private int maxDimension;
    private int myHealth;

    public Actor(String image, Dimension size, Point position, GameModel model, PhysicsVector velocity)
    {
        myHeading = 0;
        
        setImage(image);
        setSize(size.width, size.height);
        setShape(makeShape(myImage));
        myPosition = position;
        myModel = model;
        myVelocity = velocity; // TODO: Make
        // these
        // parameters
        // or
        // something
        // myKeyEvents = new HashMap<String, List<Action>>();
        myKeyEvents = new HashMap<Integer, List<Action>>();
        myInteractions = new HashMap<String, List<Action>>();
        loadBehavior();
        myXform = new AffineTransform();
        // TODO: make all this readable from a file
    }
    
    public void setHeading(double heading)
    {
        hasChanged = true;
        myHeading = heading;
    }
    
    public double getHeading()
    {
        return myHeading;
    }
    

    public Actor(String image, Dimension d, Point p,
            GameModel model)
    {
        this(image, d, p, model,  new PhysicsVector(new Direction(-1, -1), 10));
    }

    protected abstract void loadBehavior();

    public void act(KeyEvent myLastKeyPressed)
    {
        if (hasChanged)
            myXform = getTransform();
            
        hasChanged = false;
        if (myDefaultBehavior != null)
        {
            myDefaultBehavior.execute(this);
        }
        
        for (Integer e : myKeyEvents.keySet())
        {
            if (myLastKeyPressed == null)
            {
                ;
            } else if (myLastKeyPressed.getKeyCode() == e)
            {
                for (Action a : myKeyEvents.get(e))
                    a.execute(this);
            }
        }

    }

    public void interact(Actor other)
    {
        for (String s : myInteractions.keySet())
        {
            if (other.getClass().getCanonicalName().equals(s))
            {
                for (Action a : myInteractions.get(s))
                {
                    a.execute(this, other);
                }
            }
        }
        
        if (hasChanged)
            myXform = getTransform();
    }

    public void setPosition(Point p)
    {
        hasChanged = true;
        myPosition = p;
    }

    public void setVelocity(PhysicsVector v)
    {
        hasChanged = true;
        myVelocity = v;
    }

    public int getHealth()
    {
        return myHealth;
    }

    public void setHealth(int newHealth)
    {
        myHealth = newHealth;
    }

    public Point getPosition()
    {
        return myPosition;
    }

    public PhysicsVector getVelocity()
    {
        return myVelocity;
    }

    public String getImageString()
    {
        return myImageString;
    }

    public void setImage(String newImage)
    {
        hasChanged = true;
        myImage = new ImageIcon(newImage).getImage();
        myImageString = newImage;
    }

    public void remove()
    {

        getModel().remove(this);
    }

    public Dimension getSize()
    {
        return mySize;
    }

    public void setSize(int width, int height)
    {
        hasChanged = true;
        mySize = new Dimension(width, height);
        maxDimension = Math.max(getSize().width, getSize().height);
    }

    public Shape getShape()
    {
        return myShape.createTransformedArea(getTransform());
    }

    /**
     * Create an outline around the given image.
     * 
     * If the image defines a transparent region, then the outline will trace a
     * convex polygon around that edge. Otherwise, it will simply create a
     * rectangular region around the image.
     */
    private Shape makeShape(java.awt.Image image)
    {
        int h = image.getHeight(null);
        int w = image.getWidth(null);
        int[] pixels = new int[w * h];
        try
        {
            PixelGrabber grabber = new PixelGrabber(image, 0, 0, w, h, pixels,
                    0, w);
            grabber.grabPixels();
        } catch (InterruptedException e)
        {
        }

        // scan line algorithm to find edges at transparent boundaries:
        // stores the intersection points of the scan lines with edges
        // stored in a [y,x] manner since algorithm goes row by row
        List<List<Integer>> pts = new ArrayList<List<Integer>>(h);
        Point start = null;
        Point end = new Point();
        for (int y = 0; y < h; y++)
        {
            pts.add(new ArrayList<Integer>());
            int lastAlpha = 0;
            for (int x = 0; x < w; x++)
            {
                int alpha = (pixels[y * w + x] >> 24) & 0xff;
                // at an edge?
                if ((alpha != 0 && lastAlpha == 0)
                        || (alpha == 0 && lastAlpha != 0))
                {
                    pts.get(y).add(x);
                    if (start == null)
                    {
                        start = new Point(x, y);
                    }
                    end.setLocation(x, y);
                }
                lastAlpha = alpha;
            }
            // sprite overlaps the right side
            if (lastAlpha != 0)
            {
                pts.get(y).add(w - 1);
                end.setLocation(w - 1, y);
            }
        }

        // now create convex trace around image
        GeneralPath total = new GeneralPath();
        total.moveTo(start.x, start.y);
        int lastX = start.x;
        for (int y = start.y + 1; y <= end.y; y++)
        {
            List<Integer> row = pts.get(y);
            if (!row.isEmpty())
            {
                lastX = row.get(0);
            }
            total.lineTo(lastX, y);
        }
        total.lineTo(end.x, end.y);
        lastX = end.x;
        for (int y = end.y - 1; y >= start.y; y--)
        {
            List<Integer> row = pts.get(y);
            if (!row.isEmpty())
            {
                lastX = row.get(row.size() - 1);
            }
            total.lineTo(lastX, y);
        }
        total.closePath();
        total.transform(AffineTransform.getScaleInstance(1.0 / w, 1.0 / h));

        return total;
    }

    protected void setShape(Shape shape)
    {
        if (shape != null)
        {
            myShape = new Area(shape);
        }
    }

    /**
     * Reports shape's attributes as a single transform.
     */
    protected AffineTransform getTransform()
    {

            myXform.setToIdentity();
            // apply shape's attributes in proper order no matter how user set
            // them
            myXform.translate(myPosition.getX(), myPosition.getY());
            myXform.rotate(myHeading);
            myXform.scale(mySize.getWidth(), mySize.getHeight());
            myXform.translate(-0.5, -0.5);
            return myXform;
    }

    /**
     * Describes how to draw the shape on the screen.
     * 
     * Currently, draws the shape as an image.
     */

    
    public void paint (Graphics pen)
    {
        Graphics2D pen2D = (Graphics2D)pen;
        java.awt.geom.AffineTransform old = pen2D.getTransform();
        pen2D.transform(getTransform());
        pen2D.drawImage(myImage, 0, 0, 1, 1, null);
        pen2D.setTransform(old);
    }

    /**
     * Returns shape's greatest possible left-most coordinate.
     */
    public int getMaxLeft()
    {
        return getPosition().x - maxDimension / 2;
    }

    /**
     * Returns shape's greatest possible top-most coordinate.
     */
    public int getMaxTop()
    {
        return getPosition().y - maxDimension / 2;
    }

    /**
     * Returns shape's greatest possible right-most coordinate.
     */
    public int getMaxRight()
    {
        return getPosition().x + maxDimension / 2;
    }

    /**
     * Reports shape's greatest possible bottom-most coordinate.
     * 
     * @return bottom-most coordinate
     */
    public int getMaxBottom()
    {
        return getPosition().y + maxDimension / 2;
    }
    
    /**
     * Returns shape's left-most coordinate.
     */
    public int getLeft()
    {
        return getPosition().x - getSize().width / 2;
    }

    /**
     * Returns shape's top-most coordinate.
     */
    public int getTop()
    {
        return getPosition().y - getSize().height / 2;
    }

    /**
     * Returns shape's right-most coordinate.
     */
    public int getRight()
    {
        return getPosition().x + getSize().width  / 2;
    }

    /**
     * Reports shape's bottom-most coordinate.
     * 
     * @return bottom-most coordinate
     */
    public int getBottom()
    {
        return getPosition().y + getSize().height/ 2;
    }
    
    public GameModel getModel(){
        return myModel;
    }

}

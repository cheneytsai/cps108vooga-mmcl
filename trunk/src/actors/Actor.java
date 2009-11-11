package actors;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.GeneralPath;
import java.awt.image.PixelGrabber;
import java.util.*;

import javax.swing.ImageIcon;
import view.LevelViewer;

import actions.*;
import model.*;

public abstract class Actor {
    
    private GameModel myModel;
    private Image myImage;
    private Shape myShape;
    private Point myPosition;
    private Dimension mySize;
    private PhysicsVector myVelocity;
    private PhysicsVector myAcceleration;
    protected Map<String, Action> myKeyEvents;
    protected Map<String, Action> myInteractions;
    protected Action myDefaultBehavior;
    public boolean hasChanged;
    
    public Actor(String image, Dimension size, Point position, GameModel model)
    {
        setImage(image);
        setSize(size.width, size.height);
        myShape = makeShape(myImage);
        myPosition = position;
        myModel = model;
        myVelocity = new PhysicsVector(new Direction(-1, 1), 5);
        myAcceleration = new PhysicsVector(new Direction(0, 0), 0);
        myKeyEvents = new HashMap<String, Action>();
        myInteractions = new HashMap<String, Action>();
        loadBehavior();
        //TODO: make all this readable from a file
    }
    
    protected abstract void loadBehavior();
    
    public void act(String myLastKeyPressed)
    {
        
        hasChanged = false;
        for (String s : myKeyEvents.keySet())
        {
            if (myLastKeyPressed.equalsIgnoreCase(s))
            {
                myKeyEvents.get(s.toLowerCase()).execute(this);
                hasChanged = true;
            }
        }
        
        if (myDefaultBehavior != null)
        {
            myDefaultBehavior.execute(this);      
            hasChanged = true;
        }
    }
    
    public void interact(Actor other)
    {
        for (String s : myInteractions.keySet())
        {
            if (other.getClass().getCanonicalName().equals(s))
            {
                myInteractions.get(s).execute(this, other);
            }
        }
    }
    
    public void setPosition(Point p)
    {
        myPosition = p;
    }
    
    public void setVelocity(PhysicsVector v)
    {
        myVelocity = v;
    }
    
    public Point getPosition()
    {
        return myPosition;
    }
    
    public PhysicsVector getVelocity()
    {
        return myVelocity;
    }
    
    public Image getImage()
    {
        return myImage;
    }
    
    public void setImage(String newImage)
    {
        myImage = new ImageIcon(newImage).getImage();
    }
    
    public void remove()
    {
        myModel.remove(this);
    }   

    /**
     * Returns shape's size.
     */
    public Dimension getSize ()
    {
        return mySize;
    }


    /**
     * Resets shape's size.
     */
    public void setSize (int width, int height)
    {
        mySize = new Dimension(width, height);
    }
    
    public Shape getShape()
    {
        return myShape;
    }
    
    /**
     * Create an outline around the given image.
     * 
     * If the image defines a transparent region, then the outline will trace a convex
     * polygon around that edge. Otherwise, it will simply create a rectangular region
     * around the image.
     */
    private Shape makeShape (java.awt.Image image)
    {
        int h = image.getHeight(null);
        int w = image.getWidth(null);
        int[] pixels = new int[w * h];
        try
        {
            PixelGrabber grabber = new PixelGrabber(image, 0, 0, w, h, pixels, 0, w);
            grabber.grabPixels();
        }
        catch (InterruptedException e)
        {}

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
                if ((alpha != 0 && lastAlpha == 0) ||
                    (alpha == 0 && lastAlpha != 0))
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
            if (! row.isEmpty())
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
            if (! row.isEmpty())
            {
                lastX = row.get(row.size() - 1);
            }
            total.lineTo(lastX, y);
        }
        total.closePath();
        total.transform(AffineTransform.getScaleInstance(1.0 / w, 1.0 / h));

        return total;
    }

    public void paint(Graphics pen)
    {
        pen.drawImage(myImage,
                myPosition.y, myPosition.x, 
                getSize().width, getSize().height,
                null);      
    }
   
}

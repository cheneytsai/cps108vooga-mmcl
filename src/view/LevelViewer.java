package view;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;
import javax.swing.ImageIcon;
import javax.swing.Timer;
import actors.Actor;
import actors.Ball;
import model.GameModel;
import util.resources.ResourceManager;

/**
 * 
 * 
 * @author Lisa Gutermuth
 */
@SuppressWarnings("serial")
public class LevelViewer extends Canvas //implements ActionListener
{
    private String myGameName;
    private int myScore;
    private GameModel myGameModel;
    private Collection<Actor> myActors;
    // animate 25 times per second if possible
    public static final int DEFAULT_DELAY = 1000 / 25;  // in milliseconds

    
    public LevelViewer(String gameName, String levelName, int score, Canvas canvas)
    {
        myCanvas = canvas;
        myGameName = gameName;
        myScore = score;
        myCanvas.removeAll();
        myCanvas.setActive(this);

        if (myCanvas.getMouseListeners().length > 0)
        {
            myCanvas.removeMouseListener(myCanvas.getMouseListeners()[0]);
        }
        
        icon = new ImageIcon(ResourceManager.getString(levelName+ ".background"));
        myCanvas.repaint();
        
        
        myGameModel = new GameModel(this);
        myActors = myGameModel.getActors();
//        Timer timer = new Timer(DEFAULT_DELAY, this);
//        timer.start();

    }


    public void paintComponent(Graphics pen)
    {        
        pen.drawImage(icon.getImage(), 0, 0, mySize.width, mySize.height, null);
        pen.setFont(SCOREBOARD_FONT);
        pen.setColor(Color.WHITE);
        pen.drawString(ResourceManager.getString("Title").substring(0,10),0,20);
        pen.drawString(myGameName,0,40);
        pen.drawString(ResourceManager.getString("Score") + myScore, 800, 20);

        ImageIcon iconUse = new ImageIcon("src/images/ball.gif"); 
        pen.drawImage(iconUse.getImage(), 500, 600,16, 16, null);
        iconUse = new ImageIcon("src/images/paddle.gif");
        pen.drawImage(iconUse.getImage(), 500, 620,40, 16, null);
        iconUse = new ImageIcon("src/images/brick1.gif");
        pen.drawImage(iconUse.getImage(), 500, 100,40, 16, null);
        iconUse = new ImageIcon("src/images/brick2.gif");
        pen.drawImage(iconUse.getImage(), 400, 100,40, 16, null);
        iconUse = new ImageIcon("src/images/brick3.gif");
        pen.drawImage(iconUse.getImage(), 300, 100,40, 16, null);
        iconUse = new ImageIcon("src/images/brick4.gif");
        pen.drawImage(iconUse.getImage(), 200, 100,40, 16, null);
        iconUse = new ImageIcon("src/images/brick5.gif");
        pen.drawImage(iconUse.getImage(), 600, 100,40, 16, null);
        iconUse = new ImageIcon("src/images/brick6.gif");
        pen.drawImage(iconUse.getImage(), 700, 100,40, 16, null);
    }
    
    public String getGameName()
    {
        return myGameName;
    }
    
    
//    /**
//     * Never called by you directly, instead called by Java runtime
//     * when area of screen covered by this container needs to be 
//     * displayed (i.e., creation, uncovering, change in status)
//     * 
//     * @param pen smart pen to draw on the canvas with
//     */
//    public void paint (Graphics pen)
//    {
//        for (Actor current : myActors)
//        {
//            current.paint(pen);
//        }
//    }
//    
//    /**
//     * Called by each step of timer.
//     */
//    public void animate ()
//    {
//        myGame.update(this);
//
//        myIterator = myActors.listIterator();
//        while (myIterator.hasNext())
//        {
//            myCurrent = myIterator.next();
//            if (myActorsToRemove.contains(myCurrent))
//            {
//                myActorsToRemove.remove(myCurrent);
//                myIterator.remove();
//            }
//            else
//            {
//                myCurrent.update(this);
//            }
//        }
//        myIterator = null;
// 
//        // clear out updates made during animation
//        for (Actor current : myActorsToRemove)
//        {
//            myActors.remove(current);
//        }
//        myActorsToRemove.clear();
//    }
//    
//    @Override
//    public void actionPerformed(ActionEvent arg0)
//    {
//        animate();
//        // let Java runtime know panel needs to be repainted        
//        repaint();
//    }
}

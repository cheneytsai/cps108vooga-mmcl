package view;

//TODO make abstract

import gameengine.GameModel;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseListener;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Canvas extends JPanel

{
    protected Dimension mySize = new Dimension(960, 650);
    protected Canvas myCanvas;
    private Canvas myActive;
    protected GameModel myModel;
    protected String myGameName;
    private JFrame myFrame;

    protected static final Font TITLE_FONT = new Font("TAHOMA", Font.BOLD, 75);
    protected static final Font OPTION_FONT = new Font("TAHOMA", Font.BOLD, 40);
    protected static final Font SCOREBOARD_FONT = new Font("TAHOMA",
            Font.PLAIN, 20);
    protected static final Font END_FONT = new Font("TAHOMA", Font.PLAIN, 60);
    protected static final int AVG_PIXELS_PER_LETTER = 30;
    protected ImageIcon icon;

    public Canvas()
    {
        myActive = this;
        setSize(mySize);
    }

    public void paintComponent(Graphics g)
    {
        if (myActive != null)
        {
            myActive.paintComponent(g);
        }
        super.paintComponent(g);
        setOpaque(false);
    }

    public void setActive(Canvas toUse)
    {
        if (myActive instanceof LevelViewer)
        {
            ((LevelViewer) myActive).stopTimer();
        }
        myActive = toUse;
    }

    public String getGameName()
    {
        return myActive.getGameName();
    }

    public MouseListener mouseListener()
    {
        return myActive.mouseListener();
    }

    public int getWidth()
    {
        return mySize.width;
    }

    public int getHeight()
    {
        return mySize.height;
    }

    public Canvas getActive()
    {
        return myActive;
    }
    
//    public void setGamePlayMenuVisibility(boolean visibility)
//    {
//        myFrame.setGamePlayMenuVisibility(visibility);
//    }
}

package view;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseListener;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Canvas extends JPanel
{

    protected Dimension mySize = new Dimension(960, 720);
    protected static final Font TITLE_FONT =
            new Font("TAHOMA", Font.BOLD, 75);
    protected static final Font OPTION_FONT =
            new Font("TAHOMA", Font.BOLD, 40);
    protected static final Font SCOREBOARD_FONT =
        new Font("TAHOMA",Font.PLAIN,20);
    protected static final int AVG_PIXELS_PER_LETTER = 30;
    protected ImageIcon icon;
    protected Canvas myCanvas;
    private Canvas active;
    protected int myScore;


    public Canvas()
    {
        myCanvas = this;
        setSize(mySize);
    }


    public void paintComponent(Graphics g)
    {
        if (active != null)
        {
            active.paintComponent(g);
        }
        super.paintComponent(g);
        setOpaque(false);
    }


    public void setActive(Canvas toUse)
    {
        active = toUse;
    }

    public Canvas getActive()
    {
        return active;
    }

    public String getGameName()
    {
        return active.getGameName();
    }
    
    public MouseListener mouseListener()
    {
        return active.mouseListener();
    }


    public void updateScore(int increment)
    {
        myScore += increment;
    }
}

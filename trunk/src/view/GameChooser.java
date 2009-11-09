package view;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import util.resources.ResourceManager;

/*
 * stuff that actually chooses the game.
 */
@SuppressWarnings("serial")
public class GameChooser extends Canvas
{

    private Canvas myCanvas;


    public GameChooser(Canvas canvas)
    {
        myCanvas = canvas;
        myCanvas.removeAll();
        myCanvas.setActive(this);

        if (myCanvas.getMouseListeners().length > 0)
        {
            myCanvas.removeMouseListener(myCanvas.getMouseListeners()[0]);

        }
        myCanvas.addMouseListener(mouseListener());
        icon = new ImageIcon(ResourceManager.getString("StartingBackground"));

        myCanvas.repaint();
    }


    public void paintComponent(Graphics pen)
    {
        // Set Background
        pen.drawImage(icon.getImage(), 0, 0, mySize.width, mySize.height, null);

        // Set Pen
        Graphics2D myPen = (Graphics2D) pen;
        myPen.setColor(Color.BLUE);

        // Draw Title
        myPen.setFont(DEFAULT_TITLE_FONT);
        myPen.drawString(ResourceManager.getString("Title"), 100, 100);

        // Draw Options
        myPen.setFont(DEFAULT_OPTION_FONT);
        final String[] menuOptions =
                ResourceManager.getString("GameChooser").split(",");
        int counter;
        for (counter = 0; counter < menuOptions.length; counter++)
        {
            myPen.drawString(menuOptions[counter], 375, 250 + 100 * counter);
        }
        myPen.drawString(ResourceManager.getString("Quit"), 375,
                250 + 100 * counter);

        setOpaque(false);
    }


    public MouseAdapter mouseListener()
    {
        MouseAdapter myMouseAdapter = new MouseAdapter()
        {
            public void mouseClicked(MouseEvent e)
            {
                if (e.getX() > 375
                        && e.getX() < 375 + 12 * AVG_PIXELS_PER_LETTER)
                {
                    if (e.getY() > 200 && e.getY() < 250)
                    {
                        new GameMenu("Arkanoid", myCanvas);
                    }
                    else if (e.getY() > 500 && e.getY() < 550)
                    {
                        System.exit(0);
                    }
                }
            }
        };
        return myMouseAdapter;
    }
    
    public String getGameName()
    {
        return null;
    }
}
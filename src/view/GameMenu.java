package view;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;

import model.GameModel;
import util.resources.ResourceManager;

@SuppressWarnings("serial")
public class GameMenu extends Canvas
{
    private String myGameName;
//    private GameModel myGameModel;


    public GameMenu(String gameName, Canvas canvas)
    {
        myCanvas = canvas;
        myCanvas.removeAll();
        myCanvas.setActive(this);
//        myGameModel = new GameModel(this);

        if (myCanvas.getMouseListeners().length > 0)
        {
            myCanvas.removeMouseListener(myCanvas.getMouseListeners()[0]);

        }
        myCanvas.addMouseListener(mouseListener());

        myGameName = gameName;
        icon = new ImageIcon(ResourceManager.getString(gameName));

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
        myPen.drawString(myGameName, (mySize.width - AVG_PIXELS_PER_LETTER
                * myGameName.length()) / 2, 100);

        // Draw Options
        myPen.setFont(DEFAULT_OPTION_FONT);
        final String[] menuOptions =
                ResourceManager.getString("GameMenuOptions").split(",");
        int counter;
        for (counter = 0; counter < menuOptions.length; counter++)
        {
            myPen.drawString(menuOptions[counter], 375, 250 + 75 * counter);
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
                        new LevelViewer(myGameName, myGameName + "level1", myCanvas);
                    }
                    else if (e.getY() > 500 && e.getY() < 550)
                    {
                        new GameChooser(myCanvas);
                    }
                    else if (e.getY() > 600 && e.getY() < 650)
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
        return myGameName;
    }

}

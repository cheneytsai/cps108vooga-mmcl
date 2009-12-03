package view;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import actions.Quit;
import util.resources.ResourceManager;

@SuppressWarnings("serial")
public class GameMenu extends Canvas
{
    private String myGameName;


    public GameMenu(String gameName, Canvas canvas)
    {
        myCanvas = canvas;
        myCanvas.setActive(this);

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
        myPen.setColor(Color.BLACK);

        // Draw Title
        myPen.setFont(TITLE_FONT);
        myPen.drawString(myGameName, (mySize.width - AVG_PIXELS_PER_LETTER
                * myGameName.length()) / 2, 100);

        // Draw Options
        myPen.setFont(OPTION_FONT);
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
                        new LevelViewer(myGameName, 1,0, myCanvas);
                    }
                    else if (e.getY() > 300 && e.getY() < 350)
                    {
                        //will display the instructions for the current game
                    }
                    else if (e.getY() > 400 && e.getY() < 450)
                    {
                        //will display the high scores for the current game
                    }
                    else if(e.getY() > 450 && e.getY() < 500)
                    {
                        String[] levels = {"1","2"};
                        String level = (String)JOptionPane.showInputDialog(
                                null,
                                "Pick a level to edit: \n",
                                "Choose a Level",
                                JOptionPane.PLAIN_MESSAGE,
                                null,levels,null);
                        new EditorCanvas(myGameName, Integer.parseInt(level),0,myCanvas);
                    }
                    else if (e.getY() > 500 && e.getY() < 550)
                    {
                        new GameChooser(myCanvas);
                    }
                    else if (e.getY() > 600 && e.getY() < 650)
                    {
                        new Quit().execute();
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

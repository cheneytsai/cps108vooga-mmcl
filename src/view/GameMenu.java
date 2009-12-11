package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import actions.Quit;
import arkanoid.ArkanoidModel;
import util.reflection.Reflection;
import util.reflection.ReflectionException;
import util.resources.ResourceManager;

/**
 * Displays the menu options for the currently selected game.
 * 
 * @author Lisa Gutermuth
 *
 */

@SuppressWarnings("serial")
public class GameMenu extends Canvas
{
    private String myGameName;
    private final int DEFAULT_START_LEVEL = 0;

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
        final String[] menuOptions = ResourceManager.getString(
                "GameMenuOptions").split(",");
        int counter;
        for (counter = 0; counter < menuOptions.length; counter++)
        {
            myPen.drawString(menuOptions[counter], 375, 200 + 65 * counter);
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
                    if (e.getY() > 160 && e.getY() < 200)
                    {
                        File folder = new File("src/"+myGameName+"/savedGames");
                        File[] listOfFiles = folder.listFiles();
                        String[] fileNames = new String[listOfFiles.length];
                        for(int i = 1; i < listOfFiles.length; i++)
                        {
                            fileNames[i] = listOfFiles[i].toString();
                        }
                        fileNames[0] = "New Game";
                        String fileName =
                                (String) JOptionPane.showInputDialog(null,
                                        "Pick a game to play:", "New Game Mode",
                                        JOptionPane.PLAIN_MESSAGE, null, fileNames, null);
                        if(fileName == null)
                        {
                            JOptionPane.showMessageDialog(null,
                                    "You have not chosen a game to play!", "Error", 0);
                        } else if (fileName.equals("New Game"))
                        {
                            Reflection.createInstance(myGameName.toLowerCase() + "." + myGameName + "Model", 
                                myGameName, "",DEFAULT_START_LEVEL,myGameName.toLowerCase() + "." +myGameName+"LevelViewer",myCanvas);
                        }
                        else
                        {

                            try
                            {
//                                For PCs
                                Reflection.createInstance(myGameName.toLowerCase() + "." + myGameName + "Model", 
                                        myGameName, fileName.replace("src\\"+myGameName+"\\savedGames\\","").replace(".txt","")
                                        ,DEFAULT_START_LEVEL,myGameName.toLowerCase() + "." +myGameName+"LevelViewer",myCanvas);
                            }
                            catch(ReflectionException r)
                            {
//                                For Macs
                                Reflection.createInstance(myGameName.toLowerCase() + "." + myGameName + "Model", 
                                        myGameName, fileName.replace("src/"+myGameName+"/savedGames/","").replace(".txt","")
                                        ,DEFAULT_START_LEVEL,myGameName.toLowerCase() + "." +myGameName+"LevelViewer",myCanvas);
                            }
                        }
                    } else if (e.getY() > 225 && e.getY() < 265)
                    {
                        new ReplayView(myGameName, myCanvas);
                    } else if (e.getY() > 290 && e.getY() < 330)
                    {
                        new InstructionView(myGameName, myCanvas);
                    } else if (e.getY() > 355 && e.getY() < 395)
                    {
                        new ScoresView(myGameName, myCanvas);
                    } else if (e.getY() > 420 && e.getY() < 460)
                    {
                        String[] levels = ResourceManager.getString(
                                myGameName + "Levels").split(",");
                        String level = (String) JOptionPane.showInputDialog(
                                null, "Pick a level to edit: \n",
                                "Choose a Level", JOptionPane.PLAIN_MESSAGE,
                                null, levels, null);
                        try
                        {
                            int showLevel = levels.length;
                            for(int i = 0; i < levels.length; i++)
                            {
                                if(level.equals(levels[i]))
                                {
                                    showLevel = i;
                                }
                            }
                            Reflection.createInstance(myGameName.toLowerCase() + "." + myGameName + "Model", 
                                    myGameName, "",showLevel,"view.EditorCanvas",myCanvas);
                        } catch (NullPointerException n)
                        {
                            JOptionPane.showMessageDialog(null,
                                    "You have not chosen a game to edit!", "Error", 0);
                        }
                    } else if (e.getY() > 485 && e.getY() < 525)
                    {
                        new GameChooser(myCanvas);
                    } else if (e.getY() > 550 && e.getY() < 590)
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

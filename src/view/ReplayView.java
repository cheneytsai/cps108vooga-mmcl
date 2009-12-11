package view;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import util.resources.ResourceManager;

/**
 * 
 * Displays a replay of a previously played game.
 * 
 * Reads from a text file to retrieve all state from a previously played game.
 * Draws the images at their given locations in order to simulate the game.
 * 
 * @author Megan Heysham
 * @author Lisa Gutermuth
 * 
 */

@SuppressWarnings("serial")
public class ReplayView extends LevelViewer implements ActionListener
{
    private Scanner myInput;
    private ArrayList<Object[]> myActors;

    public ReplayView(String gameName, Canvas canvas)
    {
        super(gameName, null, canvas, null);
        myActors = new ArrayList<Object[]>();

        File folder = new File("src/" + gameName + "/replays");
        File[] listOfFiles = new File[folder.listFiles().length - 1];
        System.arraycopy(folder.listFiles(), 1, listOfFiles, 0, folder
                .listFiles().length - 1);
        File file = (File) JOptionPane.showInputDialog(null,
                "Pick a game to replay:", "Replay Mode",
                JOptionPane.PLAIN_MESSAGE, null, listOfFiles, null);
        
        if (file == null)
        {
            JOptionPane.showMessageDialog(this,
                    "You have not chosen a game to replay!", "Error", 0);
            new GameMenu(gameName, canvas);
        } else
        {
            try
            {
                myInput = new Scanner(file);
            } catch (FileNotFoundException e)
            {
                System.out.println(e);
            }
        }

        icon = new ImageIcon(ResourceManager.getString(gameName
                + "level.background"));

        update();
        myTimer = new Timer(DEFAULT_DELAY, this);
        myTimer.start();

        repaint();
        myCanvas.repaint();

    }

    public void update()
    {
        myActors = new ArrayList<Object[]>();
        String[] allInfo = new String[9];
        Object[] myActor;
        while (myInput.hasNextLine())
        {
            String thisLine = myInput.nextLine();
            if (thisLine.equals("update"))
            {
                break;
            }
            myActor = new Object[3];
            allInfo = thisLine.split(" ");

            myActor[0] = allInfo[1];
            myActor[1] = new Dimension(Integer.parseInt(allInfo[2]), Integer
                    .parseInt(allInfo[3]));
            myActor[2] = new Point(Integer.parseInt(allInfo[4]), Integer
                    .parseInt(allInfo[5]));

            myActors.add(myActor);
        }
        if (!myInput.hasNextLine())
        {
            myTimer.stop();
        }
    }

    public void paint(Graphics pen)
    {
        for (Object[] actor : myActors)
        {
            Image icon = new ImageIcon((String) actor[0]).getImage();
            pen.drawImage(  icon,
                            (int) (((Point) actor[2]).x - .5 * ((Dimension) actor[1]).width),
                            (int) (((Point) actor[2]).y - .5 * ((Dimension) actor[1]).height),
                            ((Dimension) actor[1]).width,
                            ((Dimension) actor[1]).height, null);
        }

    }

    public void paintComponent(Graphics pen)
    {
        pen.drawImage(icon.getImage(), 0, 0, mySize.width, mySize.height, null);
        paint(pen);
    }

    public void actionPerformed(ActionEvent arg0)
    {
        update();
        myCanvas.repaint();
    }
}

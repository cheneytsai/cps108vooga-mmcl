package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import util.resources.ResourceManager;

/**
 * This will be used instead of GameChooser and LevelViewer
 * Ultimate goal: read in menu options and actions from a file.
 * @author meganheysham
 *
 */

@SuppressWarnings("serial")
public class Menu extends Canvas{
    
    private Canvas myCanvas;
    private String myName;
    

    public Menu(String name, Canvas canvas)
    {
        myName = name;
        myCanvas = canvas;
        myCanvas.removeAll();
        myCanvas.setActive(this);

        if (myCanvas.getMouseListeners().length > 0)
        {
            myCanvas.removeMouseListener(myCanvas.getMouseListeners()[0]);

        }
        myCanvas.addMouseListener(mouseListener());
        
        icon = new ImageIcon(ResourceManager.getString(myName));

        myCanvas.repaint();
        
    }


    public void paintComponent(Graphics pen)
    {
        try {
            Scanner s = new Scanner(new File("src/resources/" + myName + ".menu"));
        
        // Set Background
        pen.drawImage(new ImageIcon(s.next()).getImage(), 0, 0, myCanvas.getWidth(), myCanvas.getHeight(), null);

        // Set Pen
        Graphics2D myPen = (Graphics2D) pen;
        myPen.setColor(new Color(s.nextInt(), s.nextInt(), s.nextInt()));

        // Draw Title
        myPen.setFont(TITLE_FONT);
        myPen.drawString(ResourceManager.getString(myName), myCanvas.getWidth() - AVG_PIXELS_PER_LETTER
                * myName.length() / 2, 100);

        // Draw Options
        myPen.setFont(OPTION_FONT);
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(myCanvas, "Menu File Not Found", "Error", 0);
        }

        setOpaque(false);
    }


    public MouseAdapter mouseListener()
    {
        MouseAdapter myMouseAdapter = new MouseAdapter()
        {
            public void mouseClicked(MouseEvent e)
            {
               //create events for each option
            }
        };
        return myMouseAdapter;
    }
    
    public String getName()
    {
        return myName;
    }

}

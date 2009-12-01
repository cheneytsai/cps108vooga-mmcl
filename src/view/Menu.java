package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.Map;
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
    private String myFile;
    private Map<String, Point> myOptions;
    private String myBackground;
    private Color myTextColor;
    public Menu(String name, Canvas canvas)
    {
        myName = name;
        myFile = "src/resources/" + myName + ".menu";
        myCanvas = canvas;
        myCanvas.removeAll();
        myCanvas.setActive(this);

        if (myCanvas.getMouseListeners().length > 0)
        {
            myCanvas.removeMouseListener(myCanvas.getMouseListeners()[0]);

        }
        myCanvas.addMouseListener(mouseListener());
        
//        icon = new ImageIcon(ResourceManager.getString(myName));

        myCanvas.repaint();
        
    }
    
    public void fillOptions(){
        try {
            Scanner s = new Scanner(new File(myFile));
            myBackground = s.nextLine();
            myTextColor = new Color(s.nextInt(), s.nextInt(), s.nextInt());
            while(s.hasNext()){
                myOptions.put(s.next(),new Point(s.nextInt(),s.nextInt()));
            }

        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(myCanvas, "Menu File Not Found", "Error", 0);
        }
    }


    public void paintComponent(Graphics pen)
    {
                
        // Set Background
        pen.drawImage(new ImageIcon(myBackground).getImage(), 0, 0, myCanvas.getWidth(), myCanvas.getHeight(), null);

        // Set Pen
        Graphics2D myPen = (Graphics2D) pen;
        myPen.setColor(myTextColor);

        // Draw Title
        myPen.setFont(TITLE_FONT);
        myPen.drawString(ResourceManager.getString(myName), myCanvas.getWidth() - AVG_PIXELS_PER_LETTER
                * myName.length() / 2, 100);

        // Draw Options
        myPen.setFont(OPTION_FONT);
        for(String option: myOptions.keySet()){
            myPen.drawString(option, myOptions.get(option).x, myOptions.get(option).y);
        }
       

        setOpaque(false);
    }
    
    public Map<String, Point> getOptionMap(){
        return Collections.unmodifiableMap(myOptions);
    }


    public MouseAdapter mouseListener()
    {
        MouseAdapter myMouseAdapter = new MouseAdapter()
        {
            public void mouseClicked(MouseEvent e)
            {
               for(String option: getOptionMap().keySet()){
                   if (e.getX() > 375
                           && e.getX() < 375 + 12 * AVG_PIXELS_PER_LETTER)
                   {
                       if (e.getY() > 200 && e.getY() < 250)
                       {
                           new GameMenu("Arkanoid", myCanvas);
                       }
                   }
               }
            }
        };
        return myMouseAdapter;
    }
    
    public String getName()
    {
        return myName;
    }

}

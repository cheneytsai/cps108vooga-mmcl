package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import actors.Actor;

import util.resources.ResourceManager;

@SuppressWarnings("serial")
public class InstructionView extends Canvas
{
    Image MMCL;
    boolean revealed;
    
    public InstructionView(String gameName, Canvas canvas)
    {
        myCanvas = canvas;
        myCanvas.setActive(this);
        myGameName = gameName;
        revealed = false;

        myCanvas.addMouseListener(mouseListener());
        
        icon = new ImageIcon(ResourceManager.getString(gameName));
        MMCL = new ImageIcon(ResourceManager.getString("BonusLevelPowerupImage")).getImage();

        myCanvas.repaint();
    }

    public void paintComponent(Graphics pen)
    {
        pen.drawImage(icon.getImage(), 0, 0, mySize.width, mySize.height, null);
        pen.setFont(TITLE_FONT);
        pen.drawString(myGameName + " Instructions", 100, 100);
        pen.setFont(SCOREBOARD_FONT);
        
        pen.drawImage(MMCL,900,590,30,30,null);
        drawInstructions(pen);
        
        if(revealed)
        {
            paintCheats(pen);
        }
    }

    private void drawInstructions(Graphics pen){
        try
        {
            Scanner s = new Scanner(new File("src/resources/" + myGameName.toLowerCase()+"/instructions.txt"));
            int i = 0;
            while(s.hasNext()){
                pen.drawString(s.nextLine(), 50, 150 + 20*i);
                i++;
            }
        } catch (FileNotFoundException e)
        {
            JOptionPane.showMessageDialog(this, "This game has no instruction file",
                    "Error", 0);
        }
    }

    private void paintCheats(Graphics pen)
    {
        try
        {
            Scanner in = new Scanner(new File(ResourceManager.getString(myGameName+"Cheats")));
            int i = 0;
            pen.setColor(Color.YELLOW);
            while(in.hasNext()){
                pen.drawString(in.nextLine(), 50, 500 + 30*i);
                i++;
            }
        }
        catch (FileNotFoundException e)
        {
            JOptionPane.showMessageDialog(this, "Sorry, this game has no cheats.",
                    "What a Cheater", 0);
        }
    }
    
    public MouseAdapter mouseListener()
    {
        MouseAdapter myMouseAdapter = new MouseAdapter()
        {
            public void mouseClicked(MouseEvent e)
            {
                if (e.getX() >= 900 && e.getX() <= 930)
                {
                    if (e.getY() >= 590 && e.getY() <= 620)
                    {
                        revealed = !revealed;
                        myCanvas.repaint();
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

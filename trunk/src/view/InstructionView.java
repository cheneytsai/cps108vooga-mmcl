package view;

import java.awt.Graphics;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import util.resources.ResourceManager;

@SuppressWarnings("serial")
public class InstructionView extends Canvas
{
    
    public InstructionView(String gameName, Canvas canvas)
    {
        myCanvas = canvas;
        myCanvas.setActive(this);
        myGameName = gameName;

        icon = new ImageIcon(ResourceManager.getString(gameName));

        myCanvas.repaint();
    }

    public void paintComponent(Graphics pen)
    {
        pen.drawImage(icon.getImage(), 0, 0, mySize.width, mySize.height, null);
        pen.setFont(TITLE_FONT);
        pen.drawString(myGameName + " Instructions", 100, 100);
        pen.setFont(SCOREBOARD_FONT);
        drawInstructions(pen);
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
            // TODO Auto-generated catch block
            JOptionPane.showMessageDialog(this, "This game has no instruction file",
                    "Error", 0);
        }
    }
    // private void getInstructions()
    // {
    // try
    // {
    // Scanner in = new Scanner(new
    // File(ResourceManager.getString(myGameName+"Scores")));
    // List<String[]> lines;
    // myScoreInfo = new HashMap<Integer,List<String[]>>();
    // String line;
    // String[] lineArray;
    //            
    // while(in.hasNextLine())
    // {
    // in.skip("");
    // line = in.nextLine();
    // lineArray = line.split("\t\t");
    //                
    // lines = new ArrayList<String[]>();
    // if(myScoreInfo.get(Integer.parseInt(lineArray[1])) != null)
    // {
    // lines = myScoreInfo.get(Integer.parseInt(lineArray[1]));
    // myScoreInfo.remove(Integer.parseInt(lineArray[1]));
    // }
    // lines.add(lineArray);
    // myScoreInfo.put(Integer.parseInt(lineArray[1]),lines);
    // }
    // sortScores(myScoreInfo);
    // }
    // catch(FileNotFoundException e)
    // {
    // System.out.println("FileNotFound");
    // }
    // }
    //    

    public String getGameName()
    {

        return myGameName;
    }
}

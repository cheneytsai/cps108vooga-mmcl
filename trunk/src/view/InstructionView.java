package view;

import java.awt.Color;
import java.awt.Graphics;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import javax.swing.ImageIcon;
import util.resources.ResourceManager;

@SuppressWarnings("serial")
public class InstructionView extends Canvas
{
    
    public InstructionView(String gameName,Canvas canvas)
    {
        myCanvas = canvas;
        myCanvas.setActive(this);
        myGameName = gameName;
        
        icon = new ImageIcon(ResourceManager.getString("InstructionsBackground"));
//        getScores();
        
        myCanvas.repaint();
    }
    
    public void paintComponent(Graphics pen)
    {
        pen.drawImage(icon.getImage(), 0, 0, mySize.width, mySize.height, null);
        pen.setFont(TITLE_FONT);
        pen.drawString(myGameName+" Instructions",100, 100);
        
//        drawScores(pen);
    }
    
//    private void getInstructions()
//    {
//        try
//        {
//            Scanner in = new Scanner(new File(ResourceManager.getString(myGameName+"Scores")));
//            List<String[]> lines;
//            myScoreInfo = new HashMap<Integer,List<String[]>>();
//            String line;
//            String[] lineArray;
//            
//            while(in.hasNextLine())
//            {
//                in.skip("");
//                line = in.nextLine();
//                lineArray = line.split("\t\t");
//                
//                lines = new ArrayList<String[]>();
//                if(myScoreInfo.get(Integer.parseInt(lineArray[1])) != null)
//                {
//                    lines = myScoreInfo.get(Integer.parseInt(lineArray[1]));
//                    myScoreInfo.remove(Integer.parseInt(lineArray[1]));
//                }
//                lines.add(lineArray);
//                myScoreInfo.put(Integer.parseInt(lineArray[1]),lines);
//            }
//            sortScores(myScoreInfo);
//        }
//        catch(FileNotFoundException e)
//        {
//            System.out.println("FileNotFound");
//        }
//    }
//    
//    private void sortScores(Map<Integer,List<String[]>> lines)
//    {
//        List<Integer> listToSort = new ArrayList<Integer>(); 
//        listToSort.addAll(lines.keySet());
//        Collections.sort(listToSort,Collections.reverseOrder());
//        mySortedKeys = listToSort;
//    }
//    
//    private void drawScores(Graphics pen)
//    {
//        pen.setFont(SCOREBOARD_FONT);
//        pen.setColor(Color.WHITE);
//        
//        int numberToPrint = 0;
//        
//        for(int i = 0; i < mySortedKeys.size(); i++)
//        {
//            List<String[]> nameDateList = myScoreInfo.get(mySortedKeys.get(i));
//            for(String[] nameDate : nameDateList)
//            {
//                pen.drawString((numberToPrint+1)+".    "+nameDate[0]+"    "+nameDate[2]+"    "+nameDate[1],
//                        120,200+numberToPrint*30);
//                numberToPrint++;
//                if(numberToPrint == 10)
//                    break;
//            }
//            if(numberToPrint == 10)
//                break;
//        }
//    }
    
    public String getGameName() {
        
        return myGameName;
    }
}

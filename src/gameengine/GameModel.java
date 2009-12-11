//TODO: Make this abstract?

package gameengine;

import view.Canvas;
import view.LevelViewer;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import javax.swing.JOptionPane;

import conditions.ConditionChecker;
import util.reflection.*;
import util.resources.ResourceManager;
import actions.Direction;
import actions.UpdateScore;
import actors.Actor;
import actors.Grid;
import actors.PhysicsVector;
import actors.Wall;

/**
 * 
 * 
 * @author Lisa Gutermuth
 */
public class GameModel
{

    protected List<Actor> myActorList;
    private Canvas myCanvas;
    protected ConditionChecker myConditions;
    private Random myRandom;
    protected int myScore;
    protected String[] myLevelProgression;
    private LevelViewer myLevelViewer;
    private int myCurrentLevel;
    protected PrintWriter myPrinter;

    public GameModel(String gameName, String resumeName,int level, String viewType, Canvas canvas)
    {
        try
        {
            myPrinter = new PrintWriter(new FileWriter("src/"
                    + gameName.toLowerCase() + "/replays/"
                    + new GregorianCalendar().getTime().toString().replace(":", "_") + ".txt"));
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        myCanvas = canvas;
        myActorList = new ArrayList<Actor>();
        myScore = 0;
        myCurrentLevel = level;
        myRandom = new Random();
        myLevelProgression = ResourceManager.getString(gameName+resumeName + "Levels")
                .split(",");
        myConditions = (ConditionChecker) Reflection.createInstance(gameName
                .toLowerCase()
                + "." + gameName + "Conditions", this);

        myLevelViewer = (LevelViewer) Reflection.createInstance(viewType,
                gameName, myLevelProgression[myCurrentLevel], canvas, this);
        initializeActors();
        myLevelViewer.startGame();
    }

    protected void initializeActors()
    {
        try
        {
            Scanner input = new Scanner(new File(ResourceManager
                    .getString(myLevelViewer.getGameName() + "level"
                            + myLevelViewer.getLevelName())));
            while (input.hasNext())
            {   
                input.skip("");
                addActor((Actor) Reflection.createInstance(input.next(), input.next(),
                        new Dimension(input.nextInt(), input.nextInt()),
                        new Point(input.nextInt(), input.nextInt()), this , 
                        new PhysicsVector(new Direction(input.nextDouble(), input.nextDouble()), input.nextDouble())));
            
            }
        } catch (FileNotFoundException e)
        {
            System.out.println("File not found");
        }
    }

    public void update(KeyEvent myLastKeyPressed)
    {
		hotKeyCheck(myLastKeyPressed);
        writeStateToFile(myPrinter);
        myPrinter.println("update");   

        //System.out.println("UPDATED");
        for (int k = 0; k < myActorList.size(); k++)
            {
                myActorList.get(k).act(myLastKeyPressed);
            }


            myConditions.checkConditions();
//            for (int k = 0; k < myActorList.size(); k++)
//            {
//                myActorList.get(k).hasChanged = false;
//            }
            // Reset All to no movement
        
    }

    protected void hotKeyCheck(KeyEvent myLastKeyPressed)
    {
        if (myLastKeyPressed != null && myLastKeyPressed.getKeyCode() == KeyEvent.VK_S)
        {
            new UpdateScore(100, this).execute();
        }
    }
    
    public void clearActors()
    {
        myActorList.clear();
    }

    public void remove(Actor actor)
    {
        myActorList.remove(actor);
    }

    public void addActor(Actor actor)
    {
        myActorList.add(actor);
    }

    public List<Actor> getActors()
    {
        return myActorList;
    }

    public void updateScore(int increment)
    {
        myScore += increment;
    }

    public Canvas getCanvas()
    {
        return myCanvas;
    }

    public Random getRandom()
    {
        return myRandom;
    }

    public void loadNextLevel()
    {
        myCurrentLevel++;
        if (myLevelProgression.length > myCurrentLevel)
        {
            myLevelViewer.setLevelName(myLevelProgression[myCurrentLevel]);
            myActorList.clear();
            initializeActors();
            myLevelViewer.startGame();
        } else
        {
            loadEnd("Win");
        }
    }

    public void loadEnd(String endCondition)
    {
        myLevelViewer.loadEnd(endCondition);
    }

    public int getScore()
    {
        return myScore;
    }

    public void clearScore()
    {
        myScore = 0;
    }

    public LevelViewer getLevelViewer()
    {
        return myLevelViewer;
    }

    public void saveState()
    {
        try
        {
            myLevelViewer.stopTimer();
            
            String name = (String) JOptionPane.showInputDialog("Name your save file", null);
            if (name == null || name.length() == 0)
            {
                name = new GregorianCalendar().getTime().toString().replace(":","_");
            }
            String fileName = "src/"
                + myLevelViewer.getGameName().toLowerCase()
                + "/savedGames/" + name+ ".txt";

            PrintWriter pw = new PrintWriter(new FileWriter(fileName));
            writeStateToFile(pw);
            pw.close();
            
            String newProgression = "";
            for(int i = myCurrentLevel+1; i < myLevelProgression.length; i++)
            {
                newProgression += ","+myLevelProgression[i];
            }
            FileWriter output = new FileWriter("src/resources/English.properties",true);
            output.append("\n"+myLevelViewer.getGameName()+"level"+name+" = "+fileName);
            output.append("\n"+myLevelViewer.getGameName()+name+"Levels = "+name+newProgression);
            output.close();
            
        } catch (IOException e)
        {
            e.printStackTrace();
        }

    }

    /**
     * @param pw
     */
    private void writeStateToFile(PrintWriter printer)
    {
        for (Actor actor : myActorList)
        {
            if (!(actor instanceof Wall) && !(actor instanceof Grid))
            {
                printer.println(actor.getClass().getCanonicalName() + " "
                        + actor.getImageString() + " " + actor.getSize().width
                        + " " + actor.getSize().height + " "
                        + actor.getPosition().x + " " + actor.getPosition().y
                        + " " + actor.getVelocity().getDirection().xShift()
                        + " " + actor.getVelocity().getDirection().yShift()
                        + " " + actor.getVelocity().getMagnitude());
                printer.flush();
            }
        }
    }
    
   


}
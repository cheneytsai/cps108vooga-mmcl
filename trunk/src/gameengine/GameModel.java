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

import conditions.ConditionChecker;
import util.reflection.*;
import util.resources.ResourceManager;
import actors.Actor;
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
    private String[] myLevelProgression;
    private LevelViewer myLevelViewer;
    private int myCurrentLevel;
    protected PrintWriter myPrinter;

    public GameModel(String gameName, int level, String viewType, Canvas canvas)
    {
        try
    {
        myPrinter = new PrintWriter(new FileWriter("src/" + gameName.toLowerCase() + "/replays/"+ new GregorianCalendar().getTime()+".txt"));
    } catch (IOException e)
    {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }
        myCanvas = canvas;
        // myCanvas.setGamePlayMenuVisibility(true);
        myActorList = new ArrayList<Actor>();
        myScore = 0;
        myCurrentLevel = level;
        myRandom = new Random();
        myLevelProgression = ResourceManager.getString(gameName + "Levels")
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
            while (input.hasNextLine())
            {
                input.skip("");
                addActor((Actor) Reflection.createInstance(input.next(), input
                        .next(),
                        new Dimension(input.nextInt(), input.nextInt()),
                        new Point(input.nextInt(), input.nextInt()), this));
            }
        } catch (FileNotFoundException e)
        {
            System.out.println("File not found");
        }
    }

    public void update(KeyEvent myLastKeyPressed)
    {
        
        myPrinter.println("update");
        writeStateToFile(myPrinter);
        
        // Set Flag for movement here
        for (int k = 0; k < myActorList.size(); k++)
        {
            Point tempPos = myActorList.get(k).getPosition();
            myActorList.get(k).act(myLastKeyPressed);
            if (myActorList.get(k).getPosition() != tempPos)
            {
                myActorList.get(k).hasMoved = true;
            }
        }

        myConditions.checkConditions();

        // Reset All to no movement
        for (int k = 0; k < myActorList.size(); k++)
        {
            myActorList.get(k).hasMoved = false;
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
            PrintWriter pw = new PrintWriter(new FileWriter(
                    "src/" + myLevelViewer.getGameName().toLowerCase() + "/savedGames/"+ new GregorianCalendar().getTime()+".txt"));
            writeStateToFile(pw);
            pw.close();
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
            if (!(actor instanceof Wall))
            {
                printer.println(actor.getClass().getCanonicalName() + " "
                        + actor.getImageString() + " "
                        + actor.getSize().width + " "
                        + actor.getSize().height + " "
                        + actor.getPosition().x + " "
                        + actor.getPosition().y + " "
                        + actor.getVelocity().getDirection().xShift() + " "
                        + actor.getVelocity().getDirection().yShift() + " "
                        + actor.getVelocity().getMagnitude());
                printer.flush();
            }
        }
    }
}
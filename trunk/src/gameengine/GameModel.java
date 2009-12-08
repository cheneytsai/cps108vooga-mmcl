//TODO: Make this abstract?

package gameengine;

import view.Canvas;
import view.EditorCanvas;
import view.LevelViewer;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import conditions.ConditionChecker;
import util.reflection.*;
import util.resources.ResourceManager;
import actors.Actor;

/**
 * 
 * 
 * @author Lisa Gutermuth
 */
public class GameModel {

    protected List<Actor> myActorList;
    public Canvas myCanvas;
    private boolean gameOver;
    protected ConditionChecker myConditions;
    private Random myRandom;

    public GameModel(Canvas canvas) {
        myCanvas = canvas;
        myActorList = new ArrayList<Actor>();
        gameOver = false;
        myRandom = new Random();
    }

    public void update(KeyEvent myLastKeyPressed) 
    {
        for (int k = 0; k < myActorList.size(); k++) {
            Point tempPos = myActorList.get(k).getPosition();
            myActorList.get(k).act(myLastKeyPressed);
            if (myActorList.get(k).getPosition() != tempPos) {
                myActorList.get(k).hasMoved = true;
            }
        }
        // Set Flag for movement here

        myConditions.checkConditions();
        for (int k = 0; k < myActorList.size(); k++) {
            myActorList.get(k).hasMoved = false;
        }
        // Reset All to no movement
    }

    public void loadNextLevel() {
        myCanvas.loadNextLevel();
    }

    public void lose() {

    }

    public void clearActors() {
        myActorList.clear();
    }

    protected void initializeActors() {
        try {
            Scanner input = new Scanner(new File(ResourceManager.getString(myCanvas.getGameName()+"level"
                                                                            +myCanvas.getLevelNum())));
            while (input.hasNextLine()) {
                input.skip("");
                addActor((Actor) Reflection.createInstance(input.next(), input.next(),
                        new Dimension(input.nextInt(), input.nextInt()),
                        new Point(input.nextInt(), input.nextInt()), this));
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } 
//        catch (MissingResourceException e) {
//            myCanvas.loadEnd("Win");
//        }
    }

    public void remove(Actor actor) {
        myActorList.remove(actor);
    }

    public void addActor(Actor actor) {
        myActorList.add(actor);
    }

    public List<Actor> getActors() {
        return myActorList;
    }

    public void updateScore(int i) {
        // TODO: Generalize this into something that can update any game state
        // (make a map of info name -> values)
        myCanvas.updateScore(i);
    }

    public void newView(Canvas canvas) {
        gameOver = true;
        if (myActorList != null ) {
            myActorList.clear();
//            myCanvas.stopTimer();
        }
        myCanvas = canvas;
        if (canvas instanceof LevelViewer || canvas instanceof EditorCanvas) {
            initializeActors();
        }
    }

    public boolean gameOver() {
        return gameOver;
    }
    
    public Canvas getCanvas()
    {
        return myCanvas;
    }


    public Random getRandom() {
        return myRandom;
    }

    public void loadBonusLevel(int level)
    {
        myCanvas.loadBonusLevel(level);
        
    }

}
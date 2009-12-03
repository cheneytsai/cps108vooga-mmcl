package model;

import view.Canvas;
import view.EditorCanvas;
import view.LevelViewer;
import java.awt.Dimension;
import java.awt.Point;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import conditions.ConditionChecker;

import util.reflection.*;
import util.resources.ResourceManager;
import actors.Actor;
import actors.Ball;

/**
 * 
 * 
 * @author Lisa Gutermuth
 */
public class GameModel {

    private List<Actor> myActorList;
    public Canvas myCanvas;
    private boolean gameOver;

    public GameModel() {
    }

    public GameModel(Canvas canvas) {
        myCanvas = canvas;
        myActorList = new ArrayList<Actor>();
//        initializeActors();
    }

    public void update(String myLastKeyPressed) 
    {
        int stationary = myActorList.size();
        for (int k = 0; k < myActorList.size(); k++) {
            Point tempPos = myActorList.get(k).getPosition();
            myActorList.get(k).act(myLastKeyPressed);
            if (myActorList.get(k).getPosition() != tempPos) {
                myActorList.get(k).hasMoved = true;
            }
            else
            {
                stationary--;
            }
        }
        // Set Flag for movement here
        if(stationary+4 == myActorList.size())
        {
            loadNextLevel();
        }
        ConditionChecker.checkConditions(myActorList, this);
        for (int k = 0; k < myActorList.size(); k++) {
            myActorList.get(k).hasMoved = false;
        }
        // Reset All to no movement
    }

    private void loadNextLevel()
    {
        myCanvas.loadNextLevel();
    }

    public void resetBall() {
        myActorList.set(0, new Ball("src/images/ball.gif",
                new Dimension(16, 16), new Point(myCanvas.getSize().width / 2,
                        myCanvas.getSize().height / 2 + 100), this));
    }

    public void clearActors() {
        myActorList.clear();
    }

    private void initializeActors() {
        // TODO: Make this read in through a file -> add new levels
        try {
            System.out.println(myCanvas.getLevelNum());
            Scanner input = new Scanner(new File(ResourceManager.getString(myCanvas.getGameName()+"level"
                                                                            +myCanvas.getLevelNum())));

            while (input.hasNextLine()) {
                addActor((Actor) Reflection.createInstance(input.next(), input
                        .next(),
                        new Dimension(input.nextInt(), input.nextInt()),
                        new Point(input.nextInt(), input.nextInt()), this));
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
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
        myCanvas.updateScore(i);
    }
    
    public void newView(Canvas canvas) {
        gameOver = true;
        if(myActorList != null)
        {
            myActorList.clear();
        }
        myCanvas = canvas;
        if(canvas instanceof LevelViewer || canvas instanceof EditorCanvas)
        {
            initializeActors();
        }    
    }
    
    public boolean gameOver()
    {
        return gameOver;
    }
    
    public void setGameOver(boolean toSet)
    {
        gameOver = toSet;
    }

}
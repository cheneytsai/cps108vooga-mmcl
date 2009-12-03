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
import java.util.MissingResourceException;
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

    public GameModel(Canvas canvas) {
        myCanvas = canvas;
        myActorList = new ArrayList<Actor>();
        gameOver = false;
    }

    public void update(String myLastKeyPressed) {
        hotkeyCheck(myLastKeyPressed);
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

    /**
     * This is a cheat for testing purposes. It makes it so the tester can
     * easily jump to the next level.
     * 
     * @param myLastKeyPressed
     */
    private void hotkeyCheck(String myLastKeyPressed) {
        if (myLastKeyPressed != null && myLastKeyPressed.equalsIgnoreCase("l")) {
            loadNextLevel();
        }
    }

    public void loadNextLevel() {
        myCanvas.loadNextLevel();
    }

    public void lose() {

    }

    public void clearActors() {
        myActorList.clear();
    }

    private void initializeActors() {
        try {
            Scanner input = new Scanner(new File(ResourceManager
                    .getString(myCanvas.getGameName() + "level"
                            + myCanvas.getLevelNum())));

            while (input.hasNextLine()) {

                addActor((Actor) Reflection.createInstance(input.next(), input
                        .next(),
                        new Dimension(input.nextInt(), input.nextInt()),
                        new Point(input.nextInt(), input.nextInt()), this));
            }
        } catch (FileNotFoundException e) {
            // gameOver = true;
            System.out.println("File not found");
        } catch (MissingResourceException e) {
            // gameOver = true;
            if (!myCanvas.getGameName().equals("Win")) {
                myCanvas.loadEnd();
            }
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
        // (make a map of info name -> values)
        myCanvas.updateScore(i);
    }

    public void newView(Canvas canvas) {
        gameOver = true;
        if (myActorList != null) {
            myActorList.clear();
        }
        myCanvas = canvas;
        if (canvas instanceof LevelViewer || canvas instanceof EditorCanvas) {
            initializeActors();
        }
    }

    public boolean gameOver() {
        return gameOver;
    }

    public void setGameOver(boolean toSet) {
        gameOver = false;
    }

    public void win() {

        myCanvas.loadEnd();
    }

}
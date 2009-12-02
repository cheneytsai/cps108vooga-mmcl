package model;

import view.Canvas;
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

public class GameModel {

    private List<Actor> myActorList;
    public Canvas myCanvas;

    public GameModel() {
    }

    public GameModel(Canvas canvas) {
        myCanvas = canvas;
        myActorList = new ArrayList<Actor>();
        initializeActors();
    }

    public void update(String myLastKeyPressed) {

        for (int k = 0; k < myActorList.size(); k++) {
            Point tempPos = myActorList.get(k).getPosition();
            myActorList.get(k).act(myLastKeyPressed);
            if (myActorList.get(k).getPosition() != tempPos) {
                myActorList.get(k).hasMoved = true;
            }
        }
        // Set Flag for movement here
        ConditionChecker.checkConditions(myActorList, this);
        for (int k = 0; k < myActorList.size(); k++) {
            myActorList.get(k).hasMoved = false;
        }
        // Reset All to no movement
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
            Scanner input = new Scanner(new File(ResourceManager.getString("ArkanoidLevel1")));
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

}
package actors;

import java.awt.Dimension;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import actions.*;

import model.GameModel;

public class Paddle extends Actor {

    public Paddle(String image, Dimension size, Point position, GameModel model) {
        super(image, size, position, model);
    }

    @Override
    protected void loadBehavior() {
        //TODO: Add some methods to remove making lists all the time in this method
        myDefaultBehavior = null;
        List<Action> a = new ArrayList<Action>();
        a.add(new Move(new PhysicsVector(new Direction(-1, 0), 10)));
        myKeyEvents.put("a", a);
        List<Action> d = new ArrayList<Action>();
        d.add(new Move(new PhysicsVector(new Direction(1, 0), 10)));
        myKeyEvents.put("d", d);
        List<Action> rightWall = new ArrayList<Action>();
        rightWall.add(new Move(new PhysicsVector(new Direction(-1,0),10)));
        myInteractions.put(Wall.class.getCanonicalName(), rightWall);
        List<Action> leftWall = new ArrayList<Action>();
        leftWall.add(new Move(new PhysicsVector(new Direction(1,0),10)));
        myInteractions.put(LeftWall.class.getCanonicalName(), leftWall);
    }

}

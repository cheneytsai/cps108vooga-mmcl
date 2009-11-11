package actors;

import java.awt.Dimension;
import java.awt.Point;

import actions.*;

import model.GameModel;

public class Paddle extends Actor {

    public Paddle(String image, Dimension size, Point position, GameModel model) {
        super(image, size, position, model);
    }

    @Override
    protected void loadBehavior() {
        myDefaultBehavior = null;
        myKeyEvents.put("a", new Move(new PhysicsVector(new Direction(-1, 0), 3)));
        myKeyEvents.put("d", new Move(new PhysicsVector(new Direction(1, 0), 3)));
    }

}

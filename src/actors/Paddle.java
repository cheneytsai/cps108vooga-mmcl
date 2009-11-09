package actors;

import java.awt.Point;

import actions.*;

import model.GameModel;

public class Paddle extends Actor {

    public Paddle(String image, Point position, GameModel model) {
        super(image, position, model);
    }

    @Override
    protected void loadBehavior() {
        myDefaultBehavior = null;
        myKeyEvents.put("a", new Move(new Direction(-1, 0), 3));
        myKeyEvents.put("d", new Move(new Direction(1, 0), 3));
        
    }

}

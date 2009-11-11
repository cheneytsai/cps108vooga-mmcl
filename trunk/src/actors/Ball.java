package actors;

import java.awt.Dimension;
import java.awt.Point;

import actions.Bounce;
import actions.Direction;
import actions.Move;
import actions.Remove;

import model.GameModel;

public class Ball extends Actor {

    public Ball(String image, Dimension size, Point position, GameModel model) {
        super(image, size, position, model);
    }

    @Override
    protected void loadBehavior() {
        myDefaultBehavior = new Move(getVelocity());
        myInteractions.put(Paddle.class.getCanonicalName(), new Bounce());
        myInteractions.put(Brick.class.getCanonicalName(), new Bounce());


    }

}

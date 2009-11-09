package actors;

import java.awt.Dimension;
import java.awt.Point;

import actions.Bounce;
import actions.Move;

import model.GameModel;

public class Ball extends Actor {

    public Ball(String image, Dimension size, Point position, GameModel model) {
        super(image, size, position, model);
    }

    @Override
    protected void loadBehavior() {
        myDefaultBehavior = new Move(getVelocity().getDirection(), getVelocity().getMagnitude());
        
        myInteractions.put(Paddle.class.getCanonicalName(), new Bounce());

    }

}

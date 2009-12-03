package actors;

import java.awt.Dimension;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import actions.Action;
import actions.Direction;
import actions.Move;
import actions.Remove;
import model.GameModel;

/**
 * 
 * @author Lisa Gutermuth
 * 
 */
public class Powerup extends Actor {
    public Powerup(String image, Dimension size, Point position, GameModel model) {
        super(image, size, position, model);
    }

    @Override
    protected void loadBehavior() {
        myDefaultBehavior = new Move(new PhysicsVector(new Direction(0, 1), 10));
        List<Action> bottomWall = new ArrayList<Action>();
        bottomWall.add(new Remove());
        myInteractions.put(BottomWall.class.getCanonicalName(), bottomWall);
        List<Action> hitPaddle = new ArrayList<Action>();
        hitPaddle.add(new Remove());
        myInteractions.put(Paddle.class.getCanonicalName(), hitPaddle);

    }

    protected void updateInteractions(List<Action> hitPaddle) {
        myInteractions.remove(Paddle.class.getCanonicalName());
        myInteractions.put(Paddle.class.getCanonicalName(), hitPaddle);
    }

}

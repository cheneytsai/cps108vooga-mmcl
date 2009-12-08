package actors;

import gameengine.GameModel;

import java.awt.Dimension;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import util.resources.ResourceManager;

import actions.Action;
import actions.Bounce;
import actions.NaturalMove;
import actions.Reset;
import actions.UpdateHealth;


/**
 * 
 * @author Michael Yu
 * 
 */
public class Ball extends Actor {

    private int defaultHealth = 2;
    
    public Ball(String image, Dimension size, Point position, GameModel model) {
        super(image, size, position, model);
        setHealth(defaultHealth);

    }

    public Ball(Point position, GameModel model) {
        super(ResourceManager.getString("BallImage"), new Dimension(16, 16),
                position, model);
        setHealth(defaultHealth);
    }

    @Override
    protected void loadBehavior() {
        myDefaultBehavior = new NaturalMove();
        List<Action> bounce = new ArrayList<Action>();
        bounce.add(new Bounce());
        bounce.add(myDefaultBehavior);
        myInteractions.put(Paddle.class.getCanonicalName(), bounce);
        myInteractions.put(Brick.class.getCanonicalName(), bounce);
        myInteractions.put(Wall.class.getCanonicalName(), bounce);
        myInteractions.put(LeftWall.class.getCanonicalName(), bounce);
        List<Action> bottomWall = new ArrayList<Action>();
        bottomWall.add(new Reset());
        bottomWall.add(new UpdateHealth(myModel,-1));
        myInteractions.put(BottomWall.class.getCanonicalName(), bottomWall);

    }

}

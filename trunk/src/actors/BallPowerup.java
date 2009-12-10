package actors;

import gameengine.GameModel;

import java.awt.Dimension;
import java.awt.Point;
import java.util.List;
import util.resources.ResourceManager;
import actions.Action;
import actions.Add;

/**
 * 
 * @author Lisa Gutermuth
 * 
 */

public class BallPowerup extends Powerup
{
    public BallPowerup(Point position, GameModel model)
    {
        super(ResourceManager.getString("BallPowerupImage"), new Dimension(16,
                16), position, model);
    }

    @Override
    protected void loadBehavior()
    {
        super.loadBehavior();
        List<Action> hitPaddle = myInteractions.get(Paddle.class
                .getCanonicalName());
        hitPaddle.add(new Add(getModel(), Ball.class.getCanonicalName()));
        updateInteractions(hitPaddle);
    }
}

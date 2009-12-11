package actors;

import gameengine.GameModel;

import java.awt.Dimension;
import java.awt.Point;
import java.util.List;

import physics.PhysicsVector;
import util.resources.ResourceManager;
import actions.Action;
import actions.Resize;

public class EnlargePowerup extends Powerup
{
    public EnlargePowerup(Point position, GameModel model,
            PhysicsVector velocity)
    {
        super(ResourceManager.getString("EnlargePowerupImage"), new Dimension(
                16, 16), position, model, velocity);
    }

    public EnlargePowerup(String image, Dimension size, Point position,
            GameModel model, PhysicsVector velocity)
    {
        super(image, size, position, model, velocity);

    }

    @Override
    protected void loadBehavior()
    {
        super.loadBehavior();
        List<Action> hitPaddle = myInteractions.get(Paddle.class
                .getCanonicalName());
        hitPaddle.add(new Resize(1.5));
        updateInteractions(hitPaddle);
    }
}

package actors;

import java.awt.Dimension;
import java.awt.Point;
import java.util.List;
import model.GameModel;
import util.resources.ResourceManager;
import actions.Action;
import actions.Resize;

public class EnlargePowerup extends Powerup
{
    public EnlargePowerup(Point position, GameModel model)
    {
        super(ResourceManager.getString("EnlargePowerupImage"),new Dimension(16,16),position,model);
    }

    @Override
    protected void loadBehavior() {
        super.loadBehavior();
        List<Action> hitPaddle = myInteractions.get(Paddle.class.getCanonicalName());
        hitPaddle.add(new Resize(1.5));
        updateInteractions(hitPaddle);
    }
}

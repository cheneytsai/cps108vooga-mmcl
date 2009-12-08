package actors;

import java.awt.Dimension;
import java.awt.Point;
import java.util.List;
import util.resources.ResourceManager;
import actions.Action;
import actions.BonusLevel;
import model.GameModel;

public class BonusLevelPowerup extends Powerup
{

    public BonusLevelPowerup(Point position, GameModel model) {
        super(ResourceManager.getString("BonusLevelPowerupImage"), new Dimension(
                16, 16), position, model);
    }

    @Override
    protected void loadBehavior() {
        super.loadBehavior();
        List<Action> hitPaddle = myInteractions.get(Paddle.class
                .getCanonicalName());
        hitPaddle.add(new BonusLevel(myModel));
        updateInteractions(hitPaddle);
    }
}

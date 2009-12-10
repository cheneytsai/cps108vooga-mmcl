package actors;

import gameengine.GameModel;

import java.awt.Dimension;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import actions.Action;
import actions.UpdateHealth;
import actions.UpdateScore;

/**
 * 
 * @author Michael Yu
 * 
 */
public class Brick extends Actor
{

    public Brick(String string, Dimension dimension, Point point,
            GameModel gameModel)
    {
        super(string, dimension, point, gameModel);
        setHealth(getModel().getRandom().nextInt(2) + 1);
    }

    @Override
    protected void loadBehavior()
    {
        List<Action> ball = new ArrayList<Action>();
        ball.add(new UpdateHealth(getModel(), -1));
        // ball.add(new RandomAdd(myModel));
        ball.add(new UpdateScore(100, getModel()));
        myInteractions.put(Ball.class.getCanonicalName(), ball);
    }
}

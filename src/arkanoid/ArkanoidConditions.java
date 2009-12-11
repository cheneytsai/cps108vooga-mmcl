package arkanoid;

import gameengine.GameModel;
import conditions.ConditionChecker;
import conditions.NumberOf;
import actions.Lose;
import actions.NextLevel;
import actors.Ball;
import actors.Brick;

/**
 * ConditionChecker for Arkanoid
 * 
 * @author Michael Yu
 * 
 */

public class ArkanoidConditions extends ConditionChecker
{

    public ArkanoidConditions(GameModel model)
    {
        super(model);
    }

    @Override
    protected void loadConditions()
    {

        myConditions.put(new NumberOf(myModel, Ball.class.getName(), 0),
                new Lose(myModel));
        myConditions.put(new NumberOf(myModel, Brick.class.getName(), 0),
                new NextLevel(myModel));
    }

}

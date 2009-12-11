//TODO: Write this
package conditions;

import gameengine.GameModel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import utilities.CollisionChecker;

import actions.Action;
import actors.Actor;

/**
 * 
 * @author Michael Yu
 * 
 */

public abstract class ConditionChecker
{

    protected GameModel myModel;
    private List<Actor> myActors;
    protected Map<Condition, Action> myConditions;

    public ConditionChecker(GameModel model)
    {
        myModel = model;
        myConditions = new HashMap<Condition, Action>();
        loadConditions();
    }

    public void checkConditions()
    {
        myActors = myModel.getActors();

        CollisionChecker.checkCollisions(myActors);
        conditionsCheck();

    }

    private void conditionsCheck()
    {
        for (Condition c : myConditions.keySet())
        {
            if (c.evaluate())
            {
                myConditions.get(c).execute();
            }
        }
    }

    protected abstract void loadConditions();

}

package conditions;

import gameengine.GameModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import utilities.CollisionChecker;

import actions.Action;
import actors.Actor;

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
        myActors = new ArrayList<Actor>();
        List<Actor> actors = myModel.getActors();
        for (Actor a : actors)
        {
            myActors.add(a);
        }

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

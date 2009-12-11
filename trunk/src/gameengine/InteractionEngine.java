package gameengine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import conditions.Condition;
import actions.Action;
import actors.Actor;

public class InteractionEngine
{

    protected GameEngine myEngine;
    protected List<Actor> myActors;
    protected Map<List<Condition>, List<Action>> myInteractions;

    public InteractionEngine(GameEngine engine)
    {
        myEngine = engine;
        myActors = myEngine.getActors();
        myInteractions = new HashMap<List<Condition>, List<Action>>();
    }

    public void loadInteractions(Map<List<Condition>, List<Action>> newInteractions)
    {
        myInteractions = newInteractions;
    }
    
    
    public void addInteractions(List<Condition> c, List<Action> a)
    {
        myInteractions.put(c, a);
    }
    
    public void clearInteractions()
    {
        myInteractions.clear();
    }
    
    public void doInteractions()
    {
        myActors = myEngine.getActors();
        checkConditions();
    }

    public void changeGameEngine(GameEngine newEngine)
    {
        myEngine = newEngine;
        myActors = myEngine.getActors();
    }


    private void checkConditions()
    {
        for (List<Condition> conditionSet : myInteractions.keySet())
        {
            for (int i = 0; i < conditionSet.size(); i++)
            {
                if (!conditionSet.get(i).evaluate())       //if any condition evaluate as false, break
                {
                    break;
                }
                if (i == conditionSet.size()-1)             //activates if all true
                {

                    for (Action a : myInteractions.get(conditionSet))       //iterates through every action
                    {
                        a.execute();
                    }
                }
                    
            }
            
        }
    }



}

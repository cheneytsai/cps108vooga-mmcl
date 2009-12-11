package cheney;

import conditions.Condition;


public class PreInitialize implements Condition
{

    private GameEngine myEngine;

    public PreInitialize(String[] params, GameEngine engine)
    {
        myEngine = engine;

    }

    public boolean evaluate()
    {
        return !myEngine.doneInitialize();
    }

}

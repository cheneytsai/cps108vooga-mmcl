package conditions;

import gameengine.GameEngine;


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

package conditions;

import gameengine.GameEngine;


public class Always implements Condition
{

    private GameEngine myEngine;

    public Always(String[] params, GameEngine engine)
    {

    }

    public boolean evaluate()
    {
        return true;
    }

}

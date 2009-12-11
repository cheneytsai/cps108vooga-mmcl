package cheney;

import conditions.Condition;

public class Never implements Condition
{

    private GameEngine myEngine;

    public Never(String[] params, GameEngine engine)
    {

    }

    public boolean evaluate()
    {
        return false;
    }

}

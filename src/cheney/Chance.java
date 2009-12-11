package cheney;

import conditions.Condition;

public class Chance implements Condition
{

    private double myProbability = (int) .5;

    public Chance(String[] params, GameEngine engine)
    {
        myProbability = Double.parseDouble(params[1]);
    }

    public boolean evaluate()
    {
        int num = (int) (Math.random() + myProbability);
        if (num == 1)
            return true;
        else
            return false;
    }

}

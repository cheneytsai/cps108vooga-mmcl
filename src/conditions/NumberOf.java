package conditions;

import gameengine.GameModel;

import java.util.List;

import actors.Actor;

/**
 * Determines whether there are a given number of a given Actor type in a given
 * GameModel.
 * 
 * @author Michael Yu
 * 
 */

public class NumberOf implements Condition
{

    private GameModel myModel;
    private String myType;
    private int numberOf;

    public NumberOf(GameModel model, String type, int number)
    {
        myModel = model;
        myType = type;
        numberOf = number;

    }

    public boolean evaluate()
    {
        int total = 0;
        List<Actor> actors = myModel.getActors();
        for (Actor a : actors)
        {
            if (a.getClass().getName().equals(myType))
                total++;

        }
        return total == numberOf;
    }

}

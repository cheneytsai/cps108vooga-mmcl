package cheney;

import conditions.Condition;
import utilities.CollisionChecker;
import actors.Actor;

public class Collision implements Condition
{

    private GameEngine myEngine;
    private String actor1;
    private String actor2;

    public Collision(String[] params, GameEngine engine)
    {
        myEngine = engine;
        actor1 = params[1];
        actor2 = params[2];

    }

    public boolean evaluate()
    {
        for (Actor a : myEngine.getActorsByID(actor1))
        {
            if(CollisionChecker.checkCollision(a, myEngine.getActorsByID(actor2)))
                return true;
        }
        return false;

    }

}

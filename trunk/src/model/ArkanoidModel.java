package model;

import java.awt.Dimension;
import java.awt.Point;

import actions.Add;
import actions.Direction;
import actions.Update;
import actors.Actor;
import actors.Ball;
import actors.Paddle;
import actors.PhysicsVector;
import view.Canvas;
import conditions.ArkanoidConditions;

public class ArkanoidModel extends GameModel {

    public ArkanoidModel(Canvas canvas) {
        super(canvas);
        myConditions = new ArkanoidConditions(this);
    }

    protected void hotkeyCheck(String myLastKeyPressed)
    {
        if(myLastKeyPressed != null)
        {
            if(myLastKeyPressed.equalsIgnoreCase("l"))
            {
                loadNextLevel();
            }
            else if(myLastKeyPressed.equalsIgnoreCase("k"))
            {
                Actor paddleActor = null;
                for(Actor actor : myActorList)
                {
                    if(actor instanceof Paddle)
                    {
                        paddleActor = actor;
                        break;
                    }
                }
                if(paddleActor != null)
                {
                    new Add(this,Ball.class.getCanonicalName()).execute(paddleActor);
                }
                myActorList.get(myActorList.size()-1).setVelocity(new PhysicsVector(new Direction(1,1),10));
            }
            else if(myLastKeyPressed.equalsIgnoreCase("j"))
            {
                new Update(this,10).execute();
            }
        }
    }
    
    public void lose() {
        updateScore(-50);
        myActorList.add(new Ball("src/images/ball.gif", new Dimension(16, 16),
                new Point(myCanvas.getSize().width / 2,
                        myCanvas.getSize().height / 2 + 100), this));
    }
    
    public void update(String myLastKeyPressed)
    {
        hotkeyCheck(myLastKeyPressed);
        super.update(myLastKeyPressed);
    }
}

package arkanoid;

import gameengine.GameModel;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.KeyEvent;

import actions.Add;
import actions.Direction;
import actions.UpdateScore;
import actors.Actor;
import actors.Ball;
import actors.BottomWall;
import actors.LeftWall;
import actors.Paddle;
import actors.PhysicsVector;
import actors.Wall;
import view.Canvas;

public class ArkanoidModel extends GameModel
{

    public ArkanoidModel(String gameName,int level,String viewType,Canvas canvas)
    {
        super(gameName,level,viewType,canvas);
    }

    protected void hotkeyCheck(KeyEvent myLastKeyPressed)
    {
        if (myLastKeyPressed != null)
        {
            if (myLastKeyPressed.getKeyCode() == KeyEvent.VK_B)
            {
                Actor paddleActor = null;
                for (Actor actor : myActorList)
                {
                    if (actor instanceof Paddle)
                    {
                        paddleActor = actor;
                        break;
                    }
                }
                if (paddleActor != null)
                {
                    new Add(this, Ball.class.getCanonicalName())
                            .execute(paddleActor);
                    myActorList.get(myActorList.size() - 1).setVelocity(
                            new PhysicsVector(new Direction(1, 1), 10));
                }
            } else if (myLastKeyPressed.getKeyCode() == KeyEvent.VK_S)
            {
                new UpdateScore(100, this).execute();
            }
        }
    }

    public void update(KeyEvent myLastKeyPressed)
    {
        if (myLastKeyPressed!=null && myPreviousKeys[0] == KeyEvent.VK_L && myLastKeyPressed.getKeyCode() == KeyEvent.VK_L)
        {
            super.update(null);
            myPreviousKeys[0] = KeyEvent.VK_L;
            myPreviousKeys[1] = KeyEvent.VK_L;
        } 
        else
        {
            hotkeyCheck(myLastKeyPressed);
            super.update(myLastKeyPressed);
        }

    }

    protected void initializeActors()
    {
        super.initializeActors();
        addActor(new Wall("src/images/brick3.gif", new Dimension(960, 16),
                new Point(480, -5), this));
        addActor(new LeftWall("src/images/brick3.gif", new Dimension(16, 650),
                new Point(-5, 325), this));
        addActor(new Wall("src/images/brick3.gif", new Dimension(16, 650),
                new Point(965, 325), this));
        addActor(new BottomWall("src/images/brick3.gif",
                new Dimension(960, 16), new Point(480, 666), this));
    }
}

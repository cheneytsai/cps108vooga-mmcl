package arkanoid;

import gameengine.GameModel;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.KeyEvent;

import physics.Direction;
import physics.PhysicsVector;

import actions.Add;
import actors.Actor;
import actors.Ball;
import actors.BottomWall;
import actors.LeftWall;
import actors.Paddle;
import actors.Wall;
import view.Canvas;

/**
 * A GameModel for Arkanoid.
 * 
 * @author Lisa Gutermuth
 * @author Megan Heysham
 * 
 */

public class ArkanoidModel extends GameModel
{

    public ArkanoidModel(String gameName, String resumeName, int level,
            String viewType, Canvas canvas)
    {
        super(gameName, resumeName, level, viewType, canvas);
    }

    protected void hotkeyCheck(KeyEvent myLastKeyPressed)
    {
        super.hotKeyCheck(myLastKeyPressed);
        if (myLastKeyPressed != null
                && myLastKeyPressed.getKeyCode() == KeyEvent.VK_B)
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
        }

    }

    public void update(KeyEvent myLastKeyPressed)
    {
        super.update(myLastKeyPressed);
        hotkeyCheck(myLastKeyPressed);
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
                new Dimension(960, 16), new Point(480, 666), this,
                new PhysicsVector(new Direction(1, 1), 0)));
    }
}

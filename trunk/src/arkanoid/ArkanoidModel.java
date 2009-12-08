package arkanoid;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.KeyEvent;

import model.GameModel;

import actions.Add;
import actions.BonusLevel;
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

public class ArkanoidModel extends GameModel {

    public ArkanoidModel(Canvas canvas) {
        super(canvas);
        myConditions = new ArkanoidConditions(this);
    }

    protected void hotkeyCheck(KeyEvent myLastKeyPressed)
    {
        if(myLastKeyPressed != null)
        {
            if(myLastKeyPressed.getKeyCode() == KeyEvent.VK_L)
            {
                loadNextLevel();
            }
            else if(myLastKeyPressed.getKeyCode() == KeyEvent.VK_B)
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
                    myActorList.get(myActorList.size()-1).setVelocity(new PhysicsVector(new Direction(1,1),10));
                }
            }
            else if(myLastKeyPressed.getKeyCode() == KeyEvent.VK_S)
            {
                new UpdateScore(this,10).execute();
            }
            else if(myLastKeyPressed.getKeyCode() == KeyEvent.VK_G)
            {
                new BonusLevel(this,11).execute();
            }
        }
    }
    
    public void lose() {
        myCanvas.loadEnd("Lose");

    }
    
    public void update(KeyEvent myLastKeyPressed)
    {
        hotkeyCheck(myLastKeyPressed);
        super.update(myLastKeyPressed);
    }
    
    public void initializeActors(){
        super.initializeActors();
        addActor(new Wall("src/images/brick3.gif", new Dimension(960, 16), new Point(480, -5), this));
        addActor(new LeftWall("src/images/brick3.gif", new Dimension(16, 720), new Point(-5, 360), this));
        addActor(new Wall("src/images/brick3.gif", new Dimension(16, 720), new Point(965, 360), this));
        addActor(new BottomWall("src/images/brick3.gif", new Dimension(960, 16), new Point(480, 725), this));
    }
}

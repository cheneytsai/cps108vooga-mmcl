package model;

import view.Canvas;
import java.awt.Dimension;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import utilities.CollisionChecker;
import actors.Actor;
import actors.Ball;
import actors.Paddle;


public class GameModel {

    private List<Actor> myActorList;
    private Canvas myCanvas;

    public GameModel(Canvas canvas) 
    {
        myCanvas = canvas;
        myActorList = new ArrayList<Actor>();
        initializeActors();
    }

    public void update(String myLastKeyPressed) 
    {
        for (int k = 0; k < myActorList.size(); k++)
            myActorList.get(k).act(myLastKeyPressed);
        
        CollisionChecker.checkCollisions(myActorList);

    }

    private void initializeActors() {
        Ball b = new Ball("src/images/ball.gif", new Dimension(16, 16), new Point(
                myCanvas.getSize().width / 2, myCanvas.getSize().height / 2),
                this);
        myActorList.add(b);

        Paddle p = new Paddle("src/images/paddle.gif", new Dimension(80, 16),
                new Point(myCanvas.getSize().width / 2,
                        myCanvas.getSize().height - 50), this);
        myActorList.add(p);

    }

    public void remove(Actor actor) 
    {
        myActorList.remove(actor);

    }

    public List<Actor> getActors()
    {
        return myActorList;
    }

}

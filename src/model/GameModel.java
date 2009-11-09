package model;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

import actions.Direction;
import actors.Actor;
import actors.Ball;
import actors.Paddle;
import actors.PhysicsVector;

public class GameModel {
    
    private List<Actor> myActorList;
    private Canvas myCanvas;
    
    public GameModel(Canvas canvas)
    {
        myCanvas = canvas;
        myActorList = new ArrayList<Actor>();
        initializeActors();
    }
    
    private void run()
    {
        Timer time = new Timer();
        
    }
    
    private void initializeActors()
    {
        Ball b = new Ball("images/ball.gif", new Dimension(16, 16),
                new Point(myCanvas.getSize().width / 2, 
                          myCanvas.getSize().height / 2),
                          this);
        myActorList.add(b);

   Paddle p = new Paddle("images/paddle.gif", new Dimension(80, 16),
                  new Point(myCanvas.getSize().width / 2,
                            myCanvas.getSize().height - 50),
                  this
                  );
   myActorList.add(p);
   
    }
    
    public void remove(Actor actor) {
        myActorList.remove(actor);
        
    }

}

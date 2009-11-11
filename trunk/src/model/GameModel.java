package model;

import view.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import utilities.CollisionChecker;
import actors.Actor;
import actors.Ball;
import actors.BottomWall;
import actors.Brick;
import actors.Paddle;
import actors.Wall;


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
        {
            myActorList.get(k).act(myLastKeyPressed);
        }
        
        CollisionChecker.checkCollisions(myActorList);
        
    }

    public void resetBall()
    {
        System.out.println("called");
        myActorList.add(new Ball("src/images/ball.gif", new Dimension(16, 16), new Point(
                myCanvas.getSize().width / 2 +100 , myCanvas.getSize().height * 5 / 6 - 32),this));
    }
    
    private void initializeActors() 
    {
        //TODO: Make this read in through a file
        Ball b = new Ball("src/images/ball.gif", new Dimension(16, 16), new Point(
                myCanvas.getSize().width / 2 +100 , myCanvas.getSize().height * 5 / 6 - 32),this);

        myActorList.add(b);

        Paddle p = new Paddle("src/images/paddle.gif", new Dimension(80, 16),
                new Point(myCanvas.getSize().width / 2 + 100,
                        myCanvas.getSize().height * 5 / 6 ), this);
        myActorList.add(p);
        
        Wall top = new Wall("src/images/brick3.gif",new 
                Dimension(myCanvas.getSize().width*3,8),new Point(0,0),this);
        Wall left = new Wall("src/images/brick3.gif",new 
                Dimension(8,myCanvas.getSize().height*3),new Point(0,0),this);
        Wall right  = new Wall("src/images/brick3.gif",new 
                Dimension(8,myCanvas.getSize().height*3),new Point(myCanvas.getSize().width,0),this);
        BottomWall bottom = new BottomWall("src/images/brick6.gif", new 
                Dimension(8,myCanvas.getSize().width*3), new Point(myCanvas.getSize().width,0),this);
        
        myActorList.add(top);
        myActorList.add(left);
        myActorList.add(right);
        myActorList.add(bottom);
        
        for(int i = 1; i < 8; i++)
        {
            myActorList.add(new Brick("src/images/brick"+i+".gif",new Dimension(80,30),
                    new Point(100*i ,100),this));
            
        }
    }

    public void remove(Actor actor) 
    {
        myActorList.remove(actor);

    }

    public List<Actor> getActors()
    {
        return myActorList;
    }

    public void updateScore(int i)
    {
        myCanvas.updateScore(i);
        
    }

}
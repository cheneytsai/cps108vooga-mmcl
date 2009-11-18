package model;

import view.Canvas;
import view.LevelViewer;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;

import javax.swing.JOptionPane;

import util.reflection.*;
import util.resources.ResourceManager;

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
        
        boolean proceed = true;
        for (int k = 0; k < myActorList.size(); k++)
        {
//            System.out.println(myActorList.size() +" ");
//            if(myActorList.get(k) instanceof Brick)
//            {
//                proceed = true;
//            }
            if(myActorList.size() == 6)
            {
                proceed = false;
            }
            myActorList.get(k).act(myLastKeyPressed);
        }
        
        CollisionChecker.checkCollisions(myActorList);
        if(proceed == false) 
        {
            myActorList.clear();
//            myCanvas.setActive(new LevelViewer(myCanvas.getGameName(), myCanvas.getGameName() + "win",0, myCanvas));
            JOptionPane.showMessageDialog(myCanvas, "You win", "Game Over", 0);
        }
        
    }

    public void resetBall()
    {
        //TODO: Generalize this into something that can be called when any game ends
        System.out.println("ball reset");
//        myActorList.add(new Ball("src/images/ball.gif", new Dimension(16, 16), new Point(
//                myCanvas.getSize().width / 2 +100 , myCanvas.getSize().height * 5 / 6 - 132),this));
        
        myActorList.set(0,new Ball("src/images/ball.gif", new Dimension(16, 16), new Point(
                myCanvas.getSize().width / 2 , myCanvas.getSize().height /2 + 100),this));
    }
    
    private void initializeActors() 
    {
        //TODO: Make this read in through a file -> add new levels
        try {
            Scanner input = new Scanner(new File("src/resources/ArkanoidLevel1.level"));
            while(input.hasNextLine()){
                myActorList.add((Actor) Reflection.createInstance(input.next(),
                        input.next(),
                        new Dimension(input.nextInt(),input.nextInt()),
                        new Point(input.nextInt(), input.nextInt()),
                        this));
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
        
//        Ball b = new Ball("src/images/ball.gif", new Dimension(16, 16), new Point(
//                myCanvas.getSize().width / 2 +100 , myCanvas.getSize().height * 5 / 6 - 32),this);

//        myActorList.add(b);

//        Paddle p = new Paddle("src/images/paddle.gif", new Dimension(80, 16),
//                new Point(myCanvas.getSize().width / 2 + 100,
//                        myCanvas.getSize().height * 5 / 6 ), this);
//        myActorList.add(p);
        
//        Wall top = new Wall("src/images/brick3.gif",new 
//                Dimension(myCanvas.getSize().width,16),new Point(myCanvas.getSize().width/2,0),this);
//        Wall left = new Wall("src/images/brick3.gif",new 
//                Dimension(16,myCanvas.getSize().height),new Point(0,myCanvas.getSize().height/2),this);
//        Wall right  = new Wall("src/images/brick3.gif",new 
//                Dimension(16,myCanvas.getSize().height),new Point(myCanvas.getSize().width, myCanvas.getSize().height/2),this);
//        BottomWall bottom = new BottomWall("src/images/brick6.gif", new 
//                Dimension(myCanvas.getSize().width, 16), new Point(myCanvas.getSize().width/2,myCanvas.getSize().height),this);
        
//        myActorList.add(top);
//        myActorList.add(left);
//        myActorList.add(right);
//        myActorList.add(bottom);
        
//        for(int i = 1; i < 8; i++)
//        {
//            myActorList.add(new Brick("src/images/brick"+i+".gif",new Dimension(80,30),
//                    new Point(100*i ,100),this));
//            
//        }
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
        //TODO: Generalize this into something that can update any game state
        myCanvas.updateScore(i);
        
    }

}
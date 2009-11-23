package model;

import java.awt.Dimension;
import java.awt.Point;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.swing.JOptionPane;
import util.reflection.Reflection;
import utilities.CollisionChecker;
import view.Canvas;
import actors.Actor;
import actors.Ball;
import actors.BottomWall;
import actors.Brick;
import actors.Paddle;
import actors.Wall;

public class EditorModel extends GameModel
{
    private List<Actor> myActorList;
    private Canvas myCanvas;
    
    public EditorModel(Canvas canvas) 
    {
        myCanvas = canvas;
        myActorList = new ArrayList<Actor>();
        initializeActors();
    }

    public void update(String myLastKeyPressed) 
    {
        
    }
    
    private void initializeActors() 
    {        
        //TODO: Make this read in through a file -> add new levels
        try 
        {
            Scanner input = new Scanner(new File("src/resources/" + myCanvas.getGameName()+ "Level1.level"));
            while(input.hasNextLine()){
                myActorList.add((Actor) Reflection.createInstance(input.next(),
                        input.next(),
                        new Dimension(input.nextInt(),input.nextInt()),
                        new Point(input.nextInt(), input.nextInt()),
                        this));
            }
        } 
        catch (FileNotFoundException e) 
        {
            System.out.println("File not found");
        }
    }

    public void remove(Actor actor) 
    {
        myActorList.remove(actor);

    }
    
    public void add(Actor actor)
    {
        myActorList.add(actor);
    }

    public List<Actor> getActors()
    {
        return myActorList;
    }
}

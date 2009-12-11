package actors;

import gameengine.GameModel;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;

import tetris.TetrisConditions;

import actions.Direction;
import actions.UpdateScore;

/**
 * 
 * @author Megan Heysham
 * 
 */
public class Grid extends Actor
{
    // TODO: This should not be a subclass of Actor. Generalize the position ->
    // grid translation so it can be used
    // for other games with grids. Make a new GameModel subclass (GridModel) to
    // model games with grids. Move the row-checking
    // to the TetrisConditions class. Make removing a row a action.

    private static Marker[][] myPositions;
    private static Block[][] myBlocks;
    private static GameModel myModel;
    private static Dimension gridSize;
    private static int myNumRowsCleared = 0;

    
    public Grid(String image, Dimension size, Point position, GameModel model, PhysicsVector velocity) {
        this(image,size,position,model);
        setVelocity(velocity);
    }
    public Grid(String image, Dimension size, Point position, GameModel model)
    {
        super(image, size, position, model, new PhysicsVector(new Direction(0,0),0));
        myPositions = new Marker[size.width][size.height];
        myBlocks = new Block[size.width][size.height];
        myModel = model;
        for (int i = 0; i < myPositions.length; i++)
        {
            for (int j = 0; j < myPositions[i].length; j++)
            {
                myPositions[i][j] = new Marker(image, new Dimension(0,0),
                        new Point(position.x + i * 26 + 13, position.y + j * 26
                                + 13), model);
            }
        }
        gridSize = size;
    }

    @Override
    protected void loadBehavior()
    {
        // Nothing
    }

    @Override
    public void paint(Graphics pen)
    {
        for (int i = 0; i < myPositions.length; i++)
        {
            for (int j = 0; j < myPositions[i].length; j++)
            {
                myPositions[i][j].paint(pen);
            }
        }
    }

    public static Marker getMarker(int i, int j)
    {
        return myPositions[i][j];
    }

    public static boolean getState(int i, int j)
    {
        return myBlocks[i][j] != null;
    }

    public static Dimension getGridSize()
    {
        return gridSize;
    }

    public static void addBlock(int i, int j, String image)
    {
        if (myBlocks[i][j] == null)
        {
            Block newBlock = new Block(image, new Dimension(26, 26), Grid
                    .getMarker(i, j).getPosition(), myModel, new PhysicsVector(new Direction(0,0),0));
            myBlocks[i][j] = newBlock;
            myModel.addActor(newBlock);
        }
    }

    public void remove()
    {
        super.remove();
    }

    public static void removeFullRowsAndDrop()
    {

        for (int i = 0; i < gridSize.height; i++)
        {

            if (TetrisConditions.isRowFull(i))
            {
                new UpdateScore(40, myModel).execute();
                myNumRowsCleared++;
                for (int j = 0; j < gridSize.width; j++)
                {
                    myBlocks[j][i].remove();
                    myBlocks[j][i] = null;
                    for (int k = i; k > 0; k--)
                    {

                        myBlocks[j][k] = myBlocks[j][k - 1];
                        if (myBlocks[j][k] != null)
                            myBlocks[j][k].setPosition(Grid.getMarker(j, k)
                                    .getPosition());
                    }
                }

            }
        }
    }

    public static int getNumRowsCleared()
    {
        return myNumRowsCleared;
    }

    public static void resetGrid()
    {
        myNumRowsCleared = 0;
    }

    
    

}

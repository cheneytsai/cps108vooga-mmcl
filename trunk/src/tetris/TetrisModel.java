package tetris;

import gameengine.GameModel;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.KeyEvent;

import actions.AddPiece;
import actors.*;
import view.Canvas;

public class TetrisModel extends GameModel
{
    private final int DEFAULT_KEY = KeyEvent.VK_0;
    private int[] myPreviousKeys;

    public TetrisModel(String gameName,Canvas canvas)
    {
        super(gameName,canvas);
    }

    public void update(KeyEvent myLastKeyPressed)
    {
        if (gameOver())
        {
            loadEnd("Lose");
        } else if (myLastKeyPressed == null)
        {
            myPreviousKeys[0] = DEFAULT_KEY;
            myPreviousKeys[1] = DEFAULT_KEY;
            super.update(null);
        } else if (myPreviousKeys[0] == KeyEvent.VK_UP
                && myLastKeyPressed.getKeyCode() == KeyEvent.VK_UP)
        {
            super.update(null);
        } else if (myLastKeyPressed.getKeyCode() == myPreviousKeys[0])
        {
            if (myPreviousKeys[0] == myPreviousKeys[1])
            {
                super.update(myLastKeyPressed);
            } else
            {
                myPreviousKeys[1] = myPreviousKeys[0];
                super.update(null);

            }
        } else
        {
            myPreviousKeys[1] = myPreviousKeys[0];
            myPreviousKeys[0] = myLastKeyPressed.getKeyCode();
            super.update(myLastKeyPressed);
        }
        Grid.removeFullRowsAndDrop();
    }

    public boolean gameOver()
    {
        for (int i = 0; i < Grid.getGridSize().width; i++)
        {
            if (Grid.getState(i, 0))
            {
                return true;
            }
        }
        return false;
    }

    public void initializeActors()
    {
        super.initializeActors();
        addActor(new Wall("src/images/wall.jpg", new Dimension(100, 650),
                new Point(699, 325), this));
        addActor(new LeftWall("src/images/wall.jpg", new Dimension(100, 650),
                new Point(287, 325), this));
        addActor(new BottomWall("src/images/brick3.gif",
                new Dimension(960, 36), new Point(480, 642), this));
        addActor(new Grid("src/images/brick6.gif", new Dimension(12, 25),
                new Point(337, 0), this));
        AddPiece.clearNext();
        new AddPiece(getCanvas().getGameName(), this).execute();

    }

    public void addActor(Actor actor)
    {
        super.addActor(actor);
        myPreviousKeys = new int[2];
        myPreviousKeys[0] = DEFAULT_KEY;
        myPreviousKeys[1] = DEFAULT_KEY;
//         new ChangeSpeed(5 + (getCanvas().getLevelNum()-1)).execute(actor);
    }

}

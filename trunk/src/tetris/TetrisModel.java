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

    private int defaultKeyPressed = KeyEvent.VK_0;
    private int[] myPreviousKeys =
    { defaultKeyPressed, defaultKeyPressed };

    public TetrisModel(Canvas canvas)
    {
        super(canvas);
        myConditions = new TetrisConditions(this);

    }

    public void update(KeyEvent myLastKeyPressed)
    {
        if (gameOver())
        {
            lose();
        } else if (myLastKeyPressed == null)
        {
            myPreviousKeys[0] = defaultKeyPressed;
            myPreviousKeys[1] = defaultKeyPressed;
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

    protected void initializeActors()
    {
        super.initializeActors();
        addActor(new Wall("src/images/wall.jpg", new Dimension(100, 720),
                new Point(699, 360), this));
        addActor(new LeftWall("src/images/wall.jpg", new Dimension(100, 720),
                new Point(287, 360), this));
        addActor(new BottomWall("src/images/brick3.gif",
                new Dimension(960, 36), new Point(480, 668), this));
        addActor(new Grid("src/images/brick6.gif", new Dimension(12, 25),
                new Point(337, 0), this));
        AddPiece.clearNext();
        new AddPiece(getCanvas().getGameName(), this).execute();

    }

    public void addActor(Actor actor)
    {
        super.addActor(actor);
        myPreviousKeys[0] = defaultKeyPressed;
        myPreviousKeys[1] = defaultKeyPressed;
        // new ChangeSpeed(myCanvas.getLevelNum()).execute(actor);
    }

}

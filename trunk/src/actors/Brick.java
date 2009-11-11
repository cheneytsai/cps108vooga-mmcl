package actors;

import java.awt.Dimension;
import java.awt.Point;

import actions.Remove;
import model.GameModel;

public class Brick extends Actor
{

    public Brick(String string, Dimension dimension, Point point,
            GameModel gameModel)
    {
        super(string,dimension,point,gameModel);
    }

    @Override
    protected void loadBehavior()
    {
        myInteractions.put(Ball.class.getCanonicalName(), new Remove());
    }

}

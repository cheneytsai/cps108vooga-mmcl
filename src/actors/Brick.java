package actors;

import java.awt.Dimension;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import actions.Action;
import actions.Remove;
import actions.Update;
import model.GameModel;

public class Brick extends Actor
{
    private static int numberOfBricks = 0;
    public Brick(String string, Dimension dimension, Point point,
            GameModel gameModel)
    {
        super(string,dimension,point,gameModel);
        numberOfBricks++;
    }

    @Override
    protected void loadBehavior()
    {
        List<Action> ball = new ArrayList<Action>();
        ball.add(new Remove());
        ball.add(new Update(myModel, 10));
        myInteractions.put(Ball.class.getCanonicalName(), ball);
    }

    public void remove()
    {
     numberOfBricks--;
     super.remove();
    }
    
    public static int getNumberOfBricks()
    {
        return numberOfBricks;
    }
}

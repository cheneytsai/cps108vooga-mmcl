package actors;

import java.awt.Dimension;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import util.resources.ResourceManager;
import actions.Action;
import actions.Add;
import actions.Direction;
import actions.Move;
import actions.Remove;
import model.GameModel;

public class BallPowerup extends Actor
{
    public BallPowerup(Point position, GameModel model)
    {
        super(ResourceManager.getString("BallPowerupImage"),new Dimension(16,16),position,model);
    }
    
    public BallPowerup(String image, Dimension size, Point position, GameModel model)
    {
        super(image, size, position, model);
    }

    @Override
    protected void loadBehavior() {
        myDefaultBehavior = new Move(new PhysicsVector(new Direction(0, 1), 10));
        List<Action> hitPaddle = new ArrayList<Action>();
        hitPaddle.add(new Remove());
        hitPaddle.add(new Add(myModel, Ball.class.getCanonicalName()));
        myInteractions.put(Paddle.class.getCanonicalName(), hitPaddle);
        List<Action> bottomWall = new ArrayList<Action>();
        bottomWall.add(new Remove());
        myInteractions.put(BottomWall.class.getCanonicalName(), bottomWall);
    }
    
    public void remove()
    {
        super.remove();
    }

}

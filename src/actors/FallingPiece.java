package actors;

import gameengine.GameModel;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import physics.Direction;
import physics.PhysicsVector;
import actions.Action;
import actions.AddPiece;
import actions.ChangeSpeed;
import actions.Move;
import actions.NaturalMove;
import actions.Remove;
import actions.Replace;
import actions.Rotate;

/**
 * 
 * @author Megan Heysham
 * 
 */
public class FallingPiece extends Actor
{

    
    public FallingPiece(String image, Dimension size, Point position, GameModel model, PhysicsVector velocity) {
        super(image,size,position,model,velocity);
        
    }
    public FallingPiece(String image, Dimension size, Point position,
            GameModel gameModel)
    {
        super(image, size, position, gameModel, new PhysicsVector(new Direction(0, 1), 5));
    }

    @Override
    protected void loadBehavior()
    {
        myDefaultBehavior = new NaturalMove();

        List<Action> up = new ArrayList<Action>();
        up.add(new Rotate(90));
        myKeyEvents.put(KeyEvent.VK_UP, up);

        List<Action> down = new ArrayList<Action>();
        down.add(new NaturalMove());
        myKeyEvents.put(KeyEvent.VK_DOWN, down);

        List<Action> left = new ArrayList<Action>();
        left.add(new Move(new PhysicsVector(new Direction(-1, 0), 26)));
        myKeyEvents.put(KeyEvent.VK_LEFT, left);

        List<Action> right = new ArrayList<Action>();
        right.add(new Move(new PhysicsVector(new Direction(1, 0), 26)));
        myKeyEvents.put(KeyEvent.VK_RIGHT, right);

        List<Action> stop = new ArrayList<Action>();
        stop.add(new ChangeSpeed(0));
        stop.add(new Replace(getModel().getCanvas().getGameName()));
        stop.add(new Remove());
        stop
                .add(new AddPiece(getModel().getCanvas().getGameName(),
                        getModel()));
        myInteractions.put(BottomWall.class.getCanonicalName(), stop);
        myInteractions.put(Block.class.getCanonicalName(), stop);

        List<Action> rightWall = new ArrayList<Action>();
        rightWall.add(new Move(new PhysicsVector(new Direction(-1, 0), 26)));
        myInteractions.put(Wall.class.getCanonicalName(), rightWall);

        List<Action> leftWall = new ArrayList<Action>();
        leftWall.add(new Move(new PhysicsVector(new Direction(1, 0), 26)));
        myInteractions.put(LeftWall.class.getCanonicalName(), leftWall);

    }

}

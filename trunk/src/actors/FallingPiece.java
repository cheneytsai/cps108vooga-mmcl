package actors;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import actions.Action;
import actions.AddPiece;
import actions.ChangeSpeed;
import actions.Direction;
import actions.Move;
import actions.NaturalMove;
import actions.Remove;
import actions.Replace;
import actions.Rotate;

import model.GameModel;

/**
 * 
 * @author Megan Heysham
 * 
 */
public class FallingPiece extends Actor {

    public FallingPiece(String image, Dimension size, Point position,
            GameModel gameModel) {
        super(image, size, position, gameModel);
        setVelocity(new PhysicsVector(new Direction(0, 1), 5));
        loadBehavior();
    }

    @Override
    protected void loadBehavior() {
        myDefaultBehavior = new NaturalMove();
        List<Action> pause = new ArrayList<Action>();
        pause.add(new ChangeSpeed(0));
        myKeyEvents.put(KeyEvent.VK_P,pause);
        List<Action> unpause = new ArrayList<Action>();
        unpause.add(new ChangeSpeed(5));
        myKeyEvents.put(KeyEvent.VK_L,unpause);
        List<Action> up = new ArrayList<Action>();
        up.add(new Rotate(myModel.myCanvas.getGameName()));
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
        stop.add(new Replace(myModel.myCanvas.getGameName()));
        stop.add(new Remove());
        stop.add(new AddPiece(myModel.myCanvas.getGameName(), myModel));
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

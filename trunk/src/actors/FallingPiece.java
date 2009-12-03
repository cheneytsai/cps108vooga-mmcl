package actors;

import java.awt.Dimension;
import java.awt.Point;
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

public class FallingPiece extends Actor {
    
    private String myCurrentImageName;
    private static int numberOfFallingPieces = 0;
    public FallingPiece(String image, Dimension size, Point position, GameModel gameModel) {
        super(image, size, position, gameModel);
        setVelocity(new PhysicsVector(new Direction(0,1), 5));
        myCurrentImageName = image;
        loadBehavior();

        numberOfFallingPieces++;
//        System.out.println("CREATED!");

        
    }

    @Override
    protected void loadBehavior() {
        myDefaultBehavior = new NaturalMove();
        List<Action> up = new ArrayList<Action>();
        up.add(new Rotate(myModel.myCanvas.getGameName()));
        myKeyEvents.put("w", up);
        List<Action> down = new ArrayList<Action>();
        down.add(new NaturalMove());
        myKeyEvents.put("s", down);
        List<Action> left = new ArrayList<Action>();
        left.add(new Move(new PhysicsVector(new Direction(-1, 0), 26)));
        myKeyEvents.put("a", left);
        List<Action> right = new ArrayList<Action>();
        right.add(new Move(new PhysicsVector(new Direction(1, 0), 26)));
        myKeyEvents.put("d", right);
        List<Action> stop = new ArrayList<Action>();
        stop.add(new ChangeSpeed(0));
        stop.add(new Replace());
        stop.add(new Remove());
        stop.add(new AddPiece(myModel.myCanvas.getGameName(),myModel));
        myInteractions.put(BottomWall.class.getCanonicalName(), stop);
        myInteractions.put(Block.class.getCanonicalName(), stop);
        List<Action> rightWall = new ArrayList<Action>();
        rightWall.add(new Move(new PhysicsVector(new Direction(-1,0),26)));
        myInteractions.put(Wall.class.getCanonicalName(), rightWall);
        List<Action> leftWall = new ArrayList<Action>();
        leftWall.add(new Move(new PhysicsVector(new Direction(1,0),26)));
        myInteractions.put(LeftWall.class.getCanonicalName(), leftWall);
        
    }
    
    public String getCurrentImageName(){
        return myCurrentImageName;
    }
    public void setCurrentImageName(String image){
        myCurrentImageName = image;
    }
    public void remove()
    {
     numberOfFallingPieces--;
     super.remove();
    }

}

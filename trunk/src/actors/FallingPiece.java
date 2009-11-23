package actors;

import java.awt.Dimension;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import actions.Action;
import actions.AddPiece;
import actions.Bounce;
import actions.ChangeSpeed;
import actions.Direction;
import actions.Move;
import actions.NaturalMove;
import actions.Remove;
import actions.Rotate;

import model.GameModel;

public class FallingPiece extends Actor {
    
    private String myCurrentImageName;

    public FallingPiece(String image, Dimension size, Point position, GameModel gameModel) {
        super(image, size, position, gameModel);
        setVelocity(new PhysicsVector(new Direction(0,1), 5));
        myCurrentImageName = image;
        loadBehavior();
        
        
    }

    @Override
    protected void loadBehavior() {
        myDefaultBehavior = new NaturalMove();
        List<Action> up = new ArrayList<Action>();
//        up.add(new Rotate(myResources.getString(myCurrentImageName)));
        up.add(new Rotate());
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
        stop.add(new AddPiece("Tetris",myModel));
        stop.add(new Remove());
        myInteractions.put(BottomWall.class.getCanonicalName(), stop);
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

}

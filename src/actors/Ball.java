package actors;

import java.awt.Dimension;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import actions.Action;
import actions.Bounce;
import actions.Direction;
import actions.Lose;
import actions.Move;
import actions.NaturalMove;
import actions.Remove;

import model.GameModel;

public class Ball extends Actor {

    public Ball(String image, Dimension size, Point position, GameModel model) {
        super(image, size, position, model);
    }

    @Override
    protected void loadBehavior() {
        myDefaultBehavior = new NaturalMove();
        List<Action> bounce = new ArrayList<Action>();
        bounce.add(new Bounce());
        bounce.add(myDefaultBehavior);
        myInteractions.put(Paddle.class.getCanonicalName(), bounce);
        myInteractions.put(Brick.class.getCanonicalName(), bounce);
        myInteractions.put(Wall.class.getCanonicalName(), bounce);
        myInteractions.put(LeftWall.class.getCanonicalName(), bounce);
        List<Action> bottomWall = new ArrayList<Action>();
        bottomWall.add(new Lose(myModel));
        bottomWall.add(new Remove());
        myInteractions.put(BottomWall.class.getCanonicalName(), bottomWall);

    }

}

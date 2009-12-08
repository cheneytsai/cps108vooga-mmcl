package actors;

import java.awt.Dimension;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import actions.Action;
import actions.UpdateHealth;
import actions.UpdateScore;
import model.GameModel;

/**
 * 
 * @author Michael Yu
 * 
 */
public class Brick extends Actor {

    public Brick(String string, Dimension dimension, Point point,
            GameModel gameModel) {
        super(string, dimension, point, gameModel);
        setHealth(myModel.getRandom().nextInt(2)+1);
    }

    @Override
    protected void loadBehavior() {
        List<Action> ball = new ArrayList<Action>();
        ball.add(new UpdateHealth(myModel,-1));
//        ball.add(new RandomAdd(myModel));
        ball.add(new UpdateScore(myModel, 10));
        myInteractions.put(Ball.class.getCanonicalName(), ball);
    }
}

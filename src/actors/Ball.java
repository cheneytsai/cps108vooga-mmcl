package actors;

import java.awt.Point;

import model.GameModel;

public class Ball extends Actor {

    public Ball(String image, Point position, GameModel model) {
        super(image, position, model);
    }

    @Override
    protected void loadBehavior() {
        // TODO Auto-generated method stub

    }

}

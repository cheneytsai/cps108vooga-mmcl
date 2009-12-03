package actions;

import java.awt.Point;

import actors.Actor;

public class NaturalMove implements Action {

    public void execute(Actor... actors) {
        Point original = actors[0].getPosition();
        Direction myDirection = actors[0].getVelocity().getDirection();
        double myMagnitude = actors[0].getVelocity().getMagnitude();
        actors[0].setPosition(new Point((int)(original.x + myDirection.xShift()*myMagnitude), (int)(original.y + myDirection.yShift()*myMagnitude)));

    }

}

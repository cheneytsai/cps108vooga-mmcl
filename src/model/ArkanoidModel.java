package model;

import java.awt.Dimension;
import java.awt.Point;

import actors.Ball;
import view.Canvas;
import conditions.ArkanoidConditions;

public class ArkanoidModel extends GameModel {

    public ArkanoidModel(Canvas canvas)
    {
        super(canvas);
        myConditions = new ArkanoidConditions(this);
    }
    
    public void lose()
    {
        myActorList.add(new Ball("src/images/ball.gif",
                new Dimension(16, 16), new Point(myCanvas.getSize().width / 2,
                        myCanvas.getSize().height / 2 + 100), this));
    }
}

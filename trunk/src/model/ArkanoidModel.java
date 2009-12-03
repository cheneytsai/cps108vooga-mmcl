package model;

import view.Canvas;
import conditions.ArkanoidConditions;

public class ArkanoidModel extends GameModel {

    public ArkanoidModel(Canvas canvas)
    {
        super(canvas);
        myConditions = new ArkanoidConditions(this);
    }
}

package model;

import conditions.TetrisConditions;
import view.Canvas;

public class TetrisModel extends GameModel {

    public TetrisModel(Canvas canvas) {
        super(canvas);
        myConditions = new TetrisConditions(this);

    }

}

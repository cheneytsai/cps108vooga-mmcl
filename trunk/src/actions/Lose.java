package actions;

import java.awt.Dimension;
import java.awt.Point;

import model.GameModel;
import actors.Actor;
import actors.Ball;

public class Lose implements Action
{
    private GameModel myModel;
    public Lose(GameModel model)
    {
        myModel = model;
    }
    public void execute(Actor... actors) 
    {
        myModel.resetBall();
    }
}

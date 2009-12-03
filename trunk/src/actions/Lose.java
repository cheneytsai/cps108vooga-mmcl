package actions;

import model.GameModel;
import actors.Actor;

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

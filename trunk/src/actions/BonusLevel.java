package actions;

import gameengine.GameModel;
import actors.Actor;

public class BonusLevel implements Action
{
    private GameModel myModel;
    private int myLevel;

    public BonusLevel(GameModel model, int level)
    {
        myModel = model;
        myLevel = level;
    }

    public void execute(Actor... actors)
    {
        myModel.loadBonusLevel(myLevel);

    }

}

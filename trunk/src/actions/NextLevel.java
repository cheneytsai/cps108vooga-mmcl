package actions;

import gameengine.GameModel;
import actors.Actor;

/**
 * 
 * @author Micheal Yu
 * 
 */
public class NextLevel extends Lose implements Action
{

    public NextLevel(GameModel model)
    {
        super(model);
    }

    @Override
    public void execute(Actor... actors)
    {
        myModel.loadNextLevel();

    }

}

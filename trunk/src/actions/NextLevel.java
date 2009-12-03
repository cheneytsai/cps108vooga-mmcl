package actions;

import model.GameModel;
import actors.Actor;

public class NextLevel extends Lose implements Action {
    

    public NextLevel(GameModel model) {
        super(model);
    }

    @Override
    public void execute(Actor... actors) {
        myModel.loadNextLevel();

    }

}

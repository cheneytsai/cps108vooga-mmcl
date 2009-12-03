package actions;

import actors.Actor;
import model.GameModel;

public class Win extends Lose implements Action {

    public Win(GameModel model) {
        super(model);
    }
    
    
    public void execute(Actor...actors)
    {
        myModel.win();
    }

}

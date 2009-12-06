package actions;

import actors.Actor;
import model.GameModel;

/**
 * 
 * @author Michael Yu
 * 
 */
public class Win extends Lose implements Action {
//TODO: Why does this extend Lose???
    public Win(GameModel model) {
        super(model);
    }

    public void execute(Actor... actors) {
        myModel.getCanvas().loadEnd("Win");
    }

}

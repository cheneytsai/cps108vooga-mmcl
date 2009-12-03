package actions;

import actors.Actor;
import model.GameModel;
/**
 * 
 * @author Michael Yu
 *
 */
public class Win extends Lose implements Action {

    public Win(GameModel model) {
        super(model);
    }
    
    public void execute(Actor...actors)
    {
        myModel.win();
    }

}

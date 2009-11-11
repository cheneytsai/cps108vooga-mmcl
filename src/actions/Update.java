
package actions;

import model.GameModel;
import actors.Actor;

public class Update implements Action {

    private GameModel myModel;
    private int myIncrement;
    
    public Update(GameModel model, int increment)
    {
        myModel = model;
        myIncrement = increment;
    }
    
    @Override
    public void execute(Actor... actors) {
        myModel.updateScore(myIncrement);

    }

}

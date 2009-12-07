package dukeopalypse;

import conditions.ConditionChecker;
import conditions.NumberOf;
import actions.Lose;
import actions.NextLevel;
import actors.Ball;
import actors.Brick;
import model.GameModel;

public class DukeopalypseConditions extends ConditionChecker {

    public DukeopalypseConditions(GameModel model) {
        super(model);
    }

    @Override
    protected void loadConditions() {
        
    }

}

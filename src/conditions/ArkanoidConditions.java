package conditions;

import actions.NextLevel;
import actors.Brick;
import model.GameModel;

public class ArkanoidConditions extends ConditionChecker {

    public ArkanoidConditions(GameModel model) {
        super(model);
        // TODO Auto-generated constructor stub
    }

    @Override
    protected void loadConditions() {
        myConditions.put(new NumberOf(myModel, Brick.class.getName(), 0), new NextLevel(myModel));

    }

}

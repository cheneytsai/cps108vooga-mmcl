package actions;

import actors.*;

public interface Action {

    /**
     * Execute associated action on given actor
     * 
     * @param a
     */
    public void execute(AbstractActor a);

}

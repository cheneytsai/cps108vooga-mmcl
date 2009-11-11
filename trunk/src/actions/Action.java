//TODO: Generalize Action classes to make them into some sort of heirarchy to make adding new ones easier
package actions;

import actors.*;

public interface Action
{

    /**
     * Execute associated action on given actor
     * The actor calling the Action should be the first argument
     * @param a
     */
    public void execute(Actor...actors);

}

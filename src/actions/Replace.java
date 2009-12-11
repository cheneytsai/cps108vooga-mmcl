package actions;

import java.util.ResourceBundle;

import utilities.CollisionChecker;
import actors.Actor;
import actors.Grid;

/**
 * Replaces an actor with blocks based on its position in a grid.
 * 
 * @author Megan Heysham
 * 
 */
public class Replace implements Action
{
    private ResourceBundle myReplacements;
    private String myGameName;

    public Replace(String gameName)
    {
        myGameName = gameName;
        myReplacements = ResourceBundle.getBundle("resources." + myGameName
                + "Pieces");
    }

    public void execute(Actor... actors)
    {
        for (int i = 0; i < Grid.getGridSize().width; i++)
        {
            for (int j = 0; j < Grid.getGridSize().height; j++)
            {
                if (CollisionChecker.intersects(actors[0], Grid.getMarker(i, j).getPosition()))
                {
                    Grid.addBlock(i, j, myReplacements.getString(actors[0]
                            .getImageString()));

                }
            }
        }

    }

}

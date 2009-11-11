package actions;

import java.awt.Dimension;
import java.awt.Point;
import actors.Actor;
import actors.Ball;

public class Lose implements Action
{
    public void execute(Actor... actors) 
    {
        actors[0].remove();
    }
}

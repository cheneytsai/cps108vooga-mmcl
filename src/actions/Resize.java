package actions;

import actors.Actor;

public class Resize implements Action
{

    double myFactor;

    public Resize(double factor)
    {
        myFactor = factor;
    }

    public void execute(Actor... actors)
    {
        if ((actors[1].getSize().getWidth() >= 40 && myFactor < 1)
                || (actors[1].getSize().getWidth() <= 120 && myFactor > 1))
        {

            actors[1].setSize(
                    (int) (actors[1].getSize().getWidth() * myFactor),
                    (int) actors[1].getSize().getHeight());
        }

    }
}

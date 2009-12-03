package actors;

import java.awt.Dimension;
import java.awt.Point;
import model.GameModel;

public class Powerup extends Actor
{

    public Powerup(String image, Dimension size, Point position, GameModel model)
    {
        super(image, size, position, model);
    }

    @Override
    protected void loadBehavior()
    {
        // TODO Auto-generated method stub

    }

}

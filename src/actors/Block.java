package actors;

import java.awt.Dimension;
import java.awt.Point;

import model.GameModel;

public class Block extends Actor {

    private static int numberOfBlocks = 0;
    public Block(String image, Dimension size, Point position, GameModel model) {
        super(image, size, position, model);
        numberOfBlocks++;
        // TODO Auto-generated constructor stub
    }

    @Override
    protected void loadBehavior() {
        // Blocks sit and do nothing

    }
    public void remove()
    {
    numberOfBlocks--;
     super.remove();
    }

}

package actors;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;

import model.GameModel;

public class Grid extends Actor {

    public Grid(String image, Dimension size, Point position, GameModel model) {
        super(image, size, position, model);
        // TODO Auto-generated constructor stub
    }

    @Override
    protected void loadBehavior() {
        // TODO Auto-generated method stub

    }
    
    @Override
    public void paint(Graphics pen){
        //Grid should not be painted
    }

}

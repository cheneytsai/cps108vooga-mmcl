package actors;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Collections;

import model.GameModel;

public class Grid extends Actor {
    
    private static boolean[][] myStates;
    private static Marker[][] myPositions;
    private GameModel myModel;
    

    public Grid(String image, Dimension size, Point position, GameModel model) {
        super(image, size, position, model);
        myStates = new boolean[size.width][size.height];
        myPositions = new Marker[size.width][size.height];
        for(int i = 0; i<myPositions.length; i++){
            for(int j = 0; j < myPositions[i].length; j++){
                myPositions[i][j] = new Marker(image, new Dimension(1,1), new Point(position.x+i*26+13, position.y+j*26+13), model);
            }
        }
    }

    @Override
    protected void loadBehavior() {
        // TODO Auto-generated method stub

    }
    
    @Override
    public void paint(Graphics pen){
        for(int i = 0; i<myPositions.length; i++){
            for(Marker mark: myPositions[i]){
                mark.paint(pen);
            }
        }
    }
    
    public static Marker[][] getMarkers(){
        return myPositions;
    }

}

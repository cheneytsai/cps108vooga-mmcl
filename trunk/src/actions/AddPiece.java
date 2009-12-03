package actions;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Point;
import java.util.Random;
import java.util.ResourceBundle;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import util.resources.ResourceManager;

import model.GameModel;

import actors.Actor;
import actors.FallingPiece;
import actors.Grid;

public class AddPiece implements Action {
    
    private static Random myRandom;
    private static ResourceBundle myResources;
    private GameModel myModel;
    
    public AddPiece (String gameName, GameModel model){
        if(myRandom == null)
            myRandom = new Random();
        if(myResources == null)
            myResources = ResourceBundle.getBundle("resources."+gameName+"Pieces");
        myModel = model;
    }

    public void execute(Actor... actors) {
        boolean addOne = true;
        boolean gameOver = false;
        for(Actor a: myModel.getActors()){
            if(a instanceof FallingPiece){
                addOne = false;
            }
            for(int i = 6; i < 10; i++){
                if(Grid.getState(i, 0)){
                    addOne = false;
                    gameOver = true;
                }
            }
        }
        if(gameOver){
            for(int i = 0;i<Grid.getGridSize().width;i++){
                for(int j = 0;j<Grid.getGridSize().height;j++){
                    if(!Grid.getState(i, j)){
                        Grid.addBlock(i, j);
                    }
                }
            }
            
        }
        
        if(addOne){
        String number = myResources.getString("NumberOfPieces");
        int random = myRandom.nextInt(Integer.parseInt(number));
        String image = myResources.getString(""+random);
        Image newImage = new ImageIcon(image).getImage();
        Dimension size = new Dimension(newImage.getWidth(null),newImage.getHeight(null));
        if(size.width%52==0)
            myModel.addActor(new FallingPiece(image, size, new Point(493,size.height/2), myModel));
        else
            myModel.addActor(new FallingPiece(image, size, new Point(480,size.height/2), myModel));
        }
    }

}
